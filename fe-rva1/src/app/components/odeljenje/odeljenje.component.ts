import { Component, Input, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Odeljenje } from 'src/app/models/odeljenje';
import { OdeljenjeService } from 'src/app/services/odeljenje.service';
import { OdeljenjeDialogComponent } from '../dialogs/odeljenje-dialog/odeljenje-dialog.component';
import { Bolnica } from 'src/app/models/bolnica';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-odeljenje',
  templateUrl: './odeljenje.component.html',
  styleUrls: ['./odeljenje.component.css']
})
export class OdeljenjeComponent implements OnInit, OnDestroy {
  subscription!: Subscription;
  displayedColumns = ['id', 'naziv', 'lokacija', 'bolnica', 'actions'];
  dataSource!: MatTableDataSource<Odeljenje>;
  @Input() selektovanaBolnica!: Bolnica;
  @ViewChild(MatSort, { static: false }) sort!: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator!: MatPaginator;

  constructor(private odeljenjeService: OdeljenjeService,
    private dialog: MatDialog,
    private snackBar: MatSnackBar) { }

  ngOnDestroy(): void { this.subscription.unsubscribe(); }
  ngOnInit(): void { this.loadData(); }
  ngOnChanges(): void {
    if(this.selektovanaBolnica.id || this.dataSource.filter) {
       this.loadData();    }  }

  public loadData() {
    this.subscription = this.odeljenjeService.getAllOdeljenja(this.selektovanaBolnica.id).subscribe({
      next: (data) => {
        this.dataSource = new MatTableDataSource(data);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      },
        error: (error) =>  {this.snackBar.open('Bolnica nema odeljenja', 'Zatvori', {
          duration: 2500
        }); this.dataSource =  new MatTableDataSource<Odeljenje>},
        complete: () => console.info('complete') 
      });
  }

  //iz htmla prosledjujemo ove podatke dijalogu
  openDialog(flag: number, odeljenje?: Odeljenje): void {
    const dialogRef = this.dialog.open(OdeljenjeDialogComponent, { data: (odeljenje ? odeljenje : new Odeljenje()) });
    //otvara modalni dijalog odgovarajuće komponente
    //vracamo instancu keirane komponente dialoga
    dialogRef.componentInstance.flagArtDialog = flag;
    dialogRef.afterClosed().subscribe(res => {
      if (res === 1) //uspesno 
      {
        //ponovo učitaj podatke
        this.loadData();
      }
    })
  }

  applyFilter(filterValue: any) {
    filterValue = filterValue.target.value
    filterValue = filterValue.trim();
    filterValue = filterValue.toLocaleLowerCase();
    this.dataSource.filter = filterValue; //    JaBuKa    --> JaBuKa --> jabuka
  }
}