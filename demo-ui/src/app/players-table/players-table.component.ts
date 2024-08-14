import { Component, OnInit } from '@angular/core';
import { PlayerService, PlayerDto } from '../player.service';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-players-table',
  templateUrl: './players-table.component.html',
  styleUrls: ['./players-table.component.css'],
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule]
})
export class PlayersTableComponent implements OnInit {
  players: PlayerDto[] = [];
  filterForm: FormGroup;
  totalPages: number = 0;
  currentPage: number = 0;

  constructor(private playerService: PlayerService) {
    this.filterForm = new FormGroup({
      lastName: new FormControl(''),
      age: new FormControl(null),
      sortBy: new FormControl('Name') // Default sorting by Name
    });
  }

  ngOnInit(): void {
    this.getPlayers();
  }

  getPlayers(): void {
    const { lastName, age, sortBy } = this.filterForm.value;
    this.playerService.getPlayers(lastName || undefined, age || undefined, sortBy, this.currentPage).subscribe(data => {
      this.players = data.content;
      this.totalPages = data.totalPages;
      this.currentPage = data.number;
    });
  }

  onFilter(): void {
    this.currentPage = 0; // Reset to first page when filtering
    this.getPlayers();
  }

  onPageChange(page: number): void {
    this.currentPage = page;
    this.getPlayers();
  }
}
