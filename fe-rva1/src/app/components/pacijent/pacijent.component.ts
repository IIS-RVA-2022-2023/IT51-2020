import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Pacijent } from 'src/app/models/pacijent';
import { PacijentService } from 'src/app/services/pacijent.service';
import { PacijentDialogComponent } from '../dialogs/pacijent-dialog/pacijent-dialog.component';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-pacijent',
  templateUrl: './pacijent.component.html',
  styleUrls: ['./pacijent.component.css']
})
export class PacijentComponent {
  subscription!: Subscription;
  displayedColumns = ['id', 'ime', 'prezime', 'zdrOsiguranje', 'datumRodjenja', 'odeljenje', 'dijagnoza', 'actions'];
  dataSource!: MatTableDataSource<Pacijent>;
  @ViewChild(MatSort, { static: false }) sort!: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator!: MatPaginator;

  constructor(private pacijentService: PacijentService, private dialog: MatDialog) { }

  ngOnInit(): void { this.loadData(); }
  ngOnChanges(): void { this.loadData(); }

  public loadData() {
    this.subscription = this.pacijentService.getAllPacijent().subscribe(
      data => {
        this.dataSource = new MatTableDataSource(data);

        this.dataSource.sortingDataAccessor = (row: Pacijent, columnName: string): string => {
 
          if (columnName == "dobavljac") return row.odeljenje.naziv.toLocaleLowerCase();
          var columnValue = row[columnName as keyof Pacijent] as unknown as string;
          return columnValue;

        }

        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      },
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
      }
    );
  }

  //iz htmla prosledjujemo ove podatke dijalogu
  openDialog(flag: number, pacijent?: Pacijent): void {
    const dialogRef = this.dialog.open(PacijentDialogComponent, { data: (pacijent ? pacijent : new Pacijent()) });
    dialogRef.componentInstance.flagArtDialog = flag;
    dialogRef.afterClosed().subscribe(res => {
      if (res === 1) //uspesno 
      {
        //ponovo učitaj podatke
        this.loadData();
      }
    });
  }

  applyFilter(filterValue: any) {
    filterValue = filterValue.target.value
    filterValue = filterValue.trim();
    filterValue = filterValue.toLocaleLowerCase();
    this.dataSource.filter = filterValue; //    JaBuKa    --> JaBuKa --> jabuka
  }
}