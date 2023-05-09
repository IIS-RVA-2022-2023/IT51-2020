import { Component } from '@angular/core';
import { BolnicaService } from 'src/app/services/bolnica.service';
import { Bolnica } from 'src/app/models/bolnica';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-bolnica',
  templateUrl: './bolnica.component.html',
  styleUrls: ['./bolnica.component.css']
})
export class BolnicaComponent {

  subscription!: Subscription;
  displayedColumns = ['id', 'naziv', 'adresa', 'budzet'];
  dataSource!: MatTableDataSource<Bolnica>;

  constructor(private bolnicaService: BolnicaService){}

  ngOnInit(): void{
    this.loadData();
  }
  loadData(): void{
    this.bolnicaService.getAllBolnica().subscribe(data => {
      //console.log(data)
      this.dataSource = new MatTableDataSource(data);
    
    },
    error => {
      console.log(error.name + ' ' + error.message);
    }
  );
}
}
