import { Component, ViewChild } from '@angular/core';
import { BolnicaService } from 'src/app/services/bolnica.service';
import { Bolnica } from 'src/app/models/bolnica';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { BolnicaDialogComponent } from '../dialogs/bolnica-dialog/bolnica-dialog.component';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-bolnica',
  templateUrl: './bolnica.component.html',
  styleUrls: ['./bolnica.component.css']
})
export class BolnicaComponent {

  subscription!: Subscription;
  displayedColumns = ['id', 'naziv', 'adresa', 'budzet', 'actions'];
  dataSource!: MatTableDataSource<Bolnica>;
  selektovanaBolnica1!: Bolnica;
  @ViewChild(MatSort, { static: false }) sort!: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator!: MatPaginator;

  constructor(private bolnicaService: BolnicaService, private dialog : MatDialog){}

  ngOnInit(): void{
    this.loadData();
  }

  ngOnChanges(): void {
       this.loadData(); }
  
  loadData(): void{
    this.bolnicaService.getAllBolnica().subscribe(
      data => {
      //console.log(data)
      this.dataSource = new MatTableDataSource(data);

      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
      
    },
    
    error => {
      console.log(error.name + ' ' + error.message);
    }
  );
}

public openDialog(flag: number, bolnica?:Bolnica): void {
  const dialogRef = this.dialog.open(BolnicaDialogComponent, {data: (bolnica ? bolnica : new Bolnica())});
  dialogRef.componentInstance.flagArtDialog = flag;
  dialogRef.afterClosed().subscribe(res => {if(res == 1) this.loadData()});
}

selectRow(row: any) {
  this.selektovanaBolnica1 = row;  }

  applyFilter(filterValue: any) {
    filterValue = filterValue.target.value
    filterValue = filterValue.trim();
    filterValue = filterValue.toLocaleLowerCase();
    this.dataSource.filter = filterValue; //    JaBuKa    --> JaBuKa --> jabuka
  }


}
