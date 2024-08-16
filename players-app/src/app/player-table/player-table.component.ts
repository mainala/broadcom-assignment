import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { PlayerService } from '../player.service'; 
import { HttpClient, HttpClientModule, HttpParams } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

interface PlayerDto {
  id: number;
  firstName: string;
  lastName: string;
  age: number;
  address1: string;
  address2: string;
}

@Component({
  selector: 'app-player-table',
  standalone: true,
  imports: [
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  template: `
    <div>
      <mat-form-field>
        <mat-label>Last Name</mat-label>
        <input matInput [(ngModel)]="lastName">
      </mat-form-field>

      <mat-form-field>
        <mat-label>Age</mat-label>
        <input matInput type="number" [(ngModel)]="age">
      </mat-form-field>

      <button mat-raised-button (click)="loadPlayers()">Search</button>
    </div>

    <table mat-table [dataSource]="dataSource" matSort (matSortChange)="sortChanged()">
      <ng-container matColumnDef="firstName">
        <th mat-header-cell *matHeaderCellDef mat-sort-header>First Name</th>
        <td mat-cell *matCellDef="let player">{{ player.firstName }}</td>
      </ng-container>

      <ng-container matColumnDef="lastName">
        <th mat-header-cell *matHeaderCellDef mat-sort-header>Last Name</th>
        <td mat-cell *matCellDef="let player">{{ player.lastName }}</td>
      </ng-container>

      <ng-container matColumnDef="age">
        <th mat-header-cell *matHeaderCellDef mat-sort-header>Age</th>
        <td mat-cell *matCellDef="let player">{{ player.age }}</td>
      </ng-container>

      <tr mat-header-row *matHeaderRowDef="displayedColumns"></tr>
      <tr mat-row *matRowDef="let row; columns: displayedColumns;"></tr>
    </table>

    <mat-paginator [pageSize]="20" [pageSizeOptions]="[5, 10, 20]" (page)="pageChanged($event)"></mat-paginator>
  `
})
export class PlayerTableComponent implements OnInit {
  dataSource = new MatTableDataSource<PlayerDto>([]);
  displayedColumns: string[] = ['firstName', 'lastName', 'age'];

  lastName: string | undefined;
  age: number | undefined;
  sortBy: string[] = ['firstName'];
  sortOrder: string[] = ['asc'];
  page: number = 0;
  totalElements: number = 0;

  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private playerService: PlayerService) {}

  ngOnInit(): void {
    this.loadPlayers();
  }

  loadPlayers(page: number = this.page, sortBy: string[] = this.sortBy, sortOrder: string[] = this.sortOrder): void {
    this.playerService.getPlayers(this.lastName, this.age, sortBy, sortOrder, page).subscribe(response => {
      this.dataSource.data = response.content;
      this.totalElements = response.totalElements;
      if (this.paginator) {
        this.paginator.length = this.totalElements;
      }
    });
  }

  sortChanged(): void {
    const active = this.sort.active;
    const direction = this.sort.direction;

    this.sortBy = [active];
    this.sortOrder = [direction || 'asc']; // Default to 'asc' if direction is empty

    this.loadPlayers(this.page, this.sortBy, this.sortOrder);
  }

  pageChanged(event: any): void {
    this.page = event.pageIndex;
    this.loadPlayers(this.page, this.sortBy, this.sortOrder);
  }
}