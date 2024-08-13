import { bootstrapApplication } from '@angular/platform-browser';
import { provideHttpClient } from '@angular/common/http';
import { PlayersTableComponent } from './app/players-table/players-table.component';

bootstrapApplication(PlayersTableComponent, {
  providers: [provideHttpClient()]
})
.catch(err => console.error(err));
