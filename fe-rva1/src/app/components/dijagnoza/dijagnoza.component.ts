import { Component } from '@angular/core';
import { DijagnozaService } from 'src/app/services/dijagnoza.service';
import { Dijagnoza } from 'src/app/models/dijagnoza';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { DijagnozaDialogComponent } from '../dialogs/dijagnoza-dialog/dijagnoza-dialog.component';

@Component({
  selector: 'app-dijagnoza',
  templateUrl: './dijagnoza.component.html',
  styleUrls: ['./dijagnoza.component.css']
})
export class DijagnozaComponent {

  subscription!: Subscription;
  displayedColumns = ['id', 'naziv', 'opis', 'oznaka', 'actions'];
  dataSource!: MatTableDataSource<Dijagnoza>;

  constructor(private dijagnozaService: DijagnozaService, private dialog : MatDialog){}

  ngOnInit(): void{
    this.loadData();
  }

  loadData(): void{
    this.dijagnozaService.getAllDijagnoza().subscribe(data => {
      //console.log(data)
      this.dataSource = new MatTableDataSource(data);
    
    },
    error => {
      console.log(error.name + ' ' + error.message);
    }
  );
}

public openDialog(flag: number, dijagnoza?:Dijagnoza): void {
  const dialogRef = this.dialog.open(DijagnozaDialogComponent, {data: dijagnoza});
  dialogRef.componentInstance.flagArtDialog = flag;
  dialogRef.afterClosed().subscribe(res => {if(res == 1) this.loadData()});
}


}
