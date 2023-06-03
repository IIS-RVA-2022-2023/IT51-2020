import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs';
import { Dijagnoza } from 'src/app/models/dijagnoza';
import { Odeljenje } from 'src/app/models/odeljenje';
import { Pacijent } from 'src/app/models/pacijent';
import { DijagnozaService } from 'src/app/services/dijagnoza.service';
import { OdeljenjeService } from 'src/app/services/odeljenje.service';
import { PacijentService } from 'src/app/services/pacijent.service';

@Component({
  selector: 'app-pacijent-dialog',
  templateUrl: './pacijent-dialog.component.html',
  styleUrls: ['./pacijent-dialog.component.css']
})
export class PacijentDialogComponent {

  public flagArtDialog!: number;
  public odeljenja!: Odeljenje[];
  public dijagnoze!: Dijagnoza[];
  private subscription!: Subscription;

  constructor(public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<PacijentDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public dataPacijent: Pacijent,
    public pacijentService: PacijentService,
    public odeljenjeService: OdeljenjeService,
    public dijagnozaService: DijagnozaService) { }

  ngOnInit() {
    this.subscription = this.odeljenjeService.getAllOdeljenje().subscribe(data => { this.odeljenja = data });

    this.subscription = this.dijagnozaService.getAllDijagnoza().subscribe(data => { this.dijagnoze = data });
  }

  compareTo(a: any, b: any) {
    return a.id == b.id;
  }

  public add(): void {

    if(this.dataPacijent.datumRodjenja > new Date()) {

      this.snackBar.open('Nije moguce da je dete rodjeno nakon danasnjeg datuma!', 'OK', {
        duration: 2500
      })

      return;
    }

    this.pacijentService.addPacijent(this.dataPacijent).subscribe(() => {
      this.snackBar.open('Uspesno dodat pacijent: ' + this.dataPacijent.ime, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom dodavanja novog pacijenta. ', 'Zatvori', {
          duration: 2500
        })
      };
  }


  public update(): void {

    if(this.dataPacijent.datumRodjenja > new Date()) {

      this.snackBar.open('Nije moguce da je dete rodjeno nakon danasnjeg datuma!', 'OK', {
        duration: 2500
      })

      return;
    }

    this.pacijentService.updatePacijent(this.dataPacijent).subscribe(() => {
      this.snackBar.open('Uspesno izmenjen pacijent: ' + this.dataPacijent.ime, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom izmene pacijenta. ', 'Zatvori', {
          duration: 2500
        })
      };

  }

  public delete(): void {
    this.pacijentService.deletePacijent(this.dataPacijent.id).subscribe(() => {
      this.snackBar.open('Uspesno obrisan pacijent: ' + this.dataPacijent.id, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom brisanja pacijenta. ', 'Zatvori', {
          duration: 2500
        })
      };
  }

  public cancel(): void {
    this.dialogRef.close();
    this.snackBar.open('Odustali ste od izmene. ', 'Zatvori', {
      duration: 1000
    })
  }
}