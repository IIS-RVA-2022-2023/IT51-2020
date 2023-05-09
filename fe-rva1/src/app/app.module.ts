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

@NgModule({
  declarations: [
    AppComponent,
    DijagnozaComponent,
    PacijentComponent,
    OdeljenjeComponent,
    BolnicaComponent
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
    MatToolbarModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
