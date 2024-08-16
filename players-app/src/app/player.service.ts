import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppConfigService } from './app-config.service';

interface PlayerDto {
  id: number;
  firstName: string;
  lastName: string;
  age: number;
  address1: string;
  address2: string;
}

@Injectable({
    providedIn: 'root'
  })
  export class PlayerService {
    private apiUrl: string;
    private pageSize: number;
  
    constructor(private http: HttpClient, private configService: AppConfigService) {
      this.apiUrl = this.configService.apiUrl;
      this.pageSize = this.configService.pageSize;
    }
  
    getPlayers(
      lastName?: string,
      age?: number,
      sortBy?: string[],
      sortOrder?: string[],
      page: number = 0
    ): Observable<{ content: PlayerDto[], totalElements: number }> {
      let params = new HttpParams()
        .set('page', page.toString())
        .set('size', this.pageSize.toString()); 
  
      if (lastName) {
        params = params.set('lastName', lastName);
      }
      if (age) {
        params = params.set('age', age.toString());
      }
      if (sortBy && sortOrder) {
        for (let i = 0; i < sortBy.length; i++) {
          params = params.append('sortBy', sortBy[i]);
          params = params.append('sortOrder', sortOrder[i]);
        }
      }
      return this.http.get<{ content: PlayerDto[], totalElements: number }>(this.apiUrl, { params });
    }  
  }