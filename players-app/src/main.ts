import { bootstrapApplication } from '@angular/platform-browser';
import { importProvidersFrom, APP_INITIALIZER } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatNativeDateModule } from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';

import { PlayerTableComponent } from './app/player-table/player-table.component';
import { AppConfigService } from './app/app-config.service'; // Import the service

// Since loadConfig no longer involves an HTTP request, you can simplify this function
export function initializeApp(configService: AppConfigService) {
  return () => new Promise<void>((resolve) => {
    configService.loadConfig();
    resolve(); // Immediately resolve since config is now loaded synchronously
  });
}

bootstrapApplication(PlayerTableComponent, {
  providers: [
    importProvidersFrom(
      BrowserAnimationsModule,
      HttpClientModule,
      MatNativeDateModule,
      MatFormFieldModule,
      MatInputModule,
      MatTableModule,
      MatPaginatorModule,
      MatSortModule
    ),
    AppConfigService,
    {
      provide: APP_INITIALIZER,
      useFactory: initializeApp,
      deps: [AppConfigService],
      multi: true,
    }
  ]
});
