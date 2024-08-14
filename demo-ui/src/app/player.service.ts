import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface PlayerDto {
  id: number;
  firstName: string;
  lastName: string;
  age: number;
  address1: string;
  address2: string;
}

export interface PlayerPage {
  content: PlayerDto[];
  totalPages: number;
  totalElements: number;
  size: number;
  number: number;
}

@Injectable({
  providedIn: 'root'
})
export class PlayerService {
  private apiUrl = 'http://localhost:8080/players'; // Adjust URL as needed

  constructor(private http: HttpClient) { }

  getPlayers(lastName?: string, age?: number, sortBy?: string, page: number = 0, size: number = 10): Observable<PlayerPage> {
    let params = new HttpParams().set('page', page.toString()).set('size', size.toString());

    if (lastName) {
      params = params.set('lastName', lastName);
    }
    if (age !== null && age !== undefined) {
      params = params.set('age', age.toString());
    }
    if (sortBy) {
      params = params.set('sortBy', sortBy);
    }

    return this.http.get<PlayerPage>(this.apiUrl, { params });
  }
}