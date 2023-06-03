import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OdeljenjeComponent } from './components/odeljenje/odeljenje.component';
import { BolnicaComponent } from './components/bolnica/bolnica.component';
import { PacijentComponent } from './components/pacijent/pacijent.component';
import { DijagnozaComponent } from './components/dijagnoza/dijagnoza.component';
import { PocetnaComponent } from './components/pocetna/pocetna.component';
import { ONamaComponent } from './components/o-nama/o-nama.component';

const routes: Routes = [ { path: 'odeljenje', component: OdeljenjeComponent },   
{ path: 'bolnica', component: BolnicaComponent },
{ path: 'pacijent', component: PacijentComponent },
{ path: 'dijagnoza', component: DijagnozaComponent},
{ path: 'odeljenje', component: OdeljenjeComponent},
{ path: 'pocetna', component: PocetnaComponent},
{ path: 'oNama', component: ONamaComponent},
{ path: '', redirectTo: '/bolnica', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }