import { Component, ViewChild } from '@angular/core';
import { DijagnozaService } from 'src/app/services/dijagnoza.service';
import { Dijagnoza } from 'src/app/models/dijagnoza';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { DijagnozaDialogComponent } from '../dialogs/dijagnoza-dialog/dijagnoza-dialog.component';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-dijagnoza',
  templateUrl: './dijagnoza.component.html',
  styleUrls: ['./dijagnoza.component.css']
})
export class DijagnozaComponent {

  subscription!: Subscription;
  displayedColumns = ['id', 'naziv', 'opis', 'oznaka', 'actions'];
  dataSource!: MatTableDataSource<Dijagnoza>;
  @ViewChild(MatSort, { static: false }) sort!: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator!: MatPaginator;

  constructor(private dijagnozaService: DijagnozaService, private dialog : MatDialog){}

  ngOnInit(): void{
    this.loadData();
  }

  loadData(): void{
    this.dijagnozaService.getAllDijagnoza().subscribe(data => {
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

public openDialog(flag: number, dijagnoza?:Dijagnoza): void {
  const dialogRef = this.dialog.open(DijagnozaDialogComponent, {data: (dijagnoza ? dijagnoza : new Dijagnoza())});
  dialogRef.componentInstance.flagArtDialog = flag;
  dialogRef.afterClosed().subscribe(res => {if(res == 1) this.loadData()});
}

applyFilter(filterValue: any) {
  filterValue = filterValue.target.value
  filterValue = filterValue.trim();
  filterValue = filterValue.toLocaleLowerCase();
  this.dataSource.filter = filterValue; //    JaBuKa    --> JaBuKa --> jabuka
}

}
