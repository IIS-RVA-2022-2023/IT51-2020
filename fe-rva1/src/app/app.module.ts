import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { DijagnozaComponent } from './components/dijagnoza/dijagnoza.component';
import { PacijentComponent } from './components/pacijent/pacijent.component';
import { OdeljenjeComponent } from './components/odeljenje/odeljenje.component';
import { BolnicaComponent } from './components/bolnica/bolnica.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatIconModule} from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatExpansionModule } from '@angular/material/expansion';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { BolnicaDialogComponent } from './components/dialogs/bolnica-dialog/bolnica-dialog.component';
import { OdeljenjeDialogComponent } from './components/dialogs/odeljenje-dialog/odeljenje-dialog.component';
import { PacijentDialogComponent } from './components/dialogs/pacijent-dialog/pacijent-dialog.component';
import { DijagnozaDialogComponent } from './components/dialogs/dijagnoza-dialog/dijagnoza-dialog.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatSelectModule } from '@angular/material/select';
import { MatNativeDateModule } from '@angular/material/core';
import { MatSortModule } from '@angular/material/sort';
import { MatPaginatorModule } from '@angular/material/paginator';

@NgModule({
  declarations: [
    AppComponent,
    DijagnozaComponent,
    PacijentComponent,
    OdeljenjeComponent,
    BolnicaComponent,
    BolnicaDialogComponent,
    OdeljenjeDialogComponent,
    PacijentDialogComponent,
    DijagnozaDialogComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    BrowserAnimationsModule,
    MatIconModule,
    MatSidenavModule, 
    MatListModule,
    MatGridListModule,
    MatExpansionModule,
    HttpClientModule,
    MatTableModule,
    MatToolbarModule,
    MatDialogModule,
    MatSnackBarModule,
    MatFormFieldModule,
    FormsModule,
    MatInputModule,
    MatDatepickerModule,
    MatCheckboxModule,
    MatSelectModule,
    MatNativeDateModule,
    MatSortModule,
    MatPaginatorModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
