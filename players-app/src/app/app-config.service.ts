// src/app/app-config.service.ts
import { Injectable } from '@angular/core';

interface AppConfig {
  apiUrl: string;
  pageSize: number;
}

@Injectable({
  providedIn: 'root'
})
export class AppConfigService {
  private config: AppConfig | undefined;

  constructor() {
    this.loadConfig();
  }

  public loadConfig(): void {
    this.config = (window as any).config;
  }

  get apiUrl(): string {
    return this.config?.apiUrl || '';
  }

  get pageSize(): number {
    return this.config?.pageSize || 20;
  }
}
