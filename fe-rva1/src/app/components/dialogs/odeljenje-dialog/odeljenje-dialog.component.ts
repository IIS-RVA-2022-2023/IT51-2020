import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs';
import { Bolnica } from 'src/app/models/bolnica';
import { Odeljenje } from 'src/app/models/odeljenje';
import { OdeljenjeService } from 'src/app/services/odeljenje.service';
import { BolnicaService } from 'src/app/services/bolnica.service';

@Component({
  selector: 'app-odeljenje-dialog',
  templateUrl: './odeljenje-dialog.component.html',
  styleUrls: ['./odeljenje-dialog.component.css']
})
export class OdeljenjeDialogComponent {

  public flagArtDialog!: number;
  private subscription!: Subscription;
  bolnice!: Bolnica[];

  constructor(public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<OdeljenjeDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public dataOdeljenje: Odeljenje,
    public odeljenjeService: OdeljenjeService,
    public bolnicaService: BolnicaService) { }

    ngOnInit(): void {

      this.bolnicaService.getAllBolnica().subscribe(

        data => {
          this.bolnice = data;
        }

      )

    }

  public compare(a:any, b:any) {
    return a.id = b.id;
  }

  public add(): void {
    this.odeljenjeService.addOdeljenje(this.dataOdeljenje).subscribe(() => {
      this.snackBar.open('Uspesno dodato odeljenje: ' + this.dataOdeljenje.naziv, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom dodavanja novog odeljenja. ', 'Zatvori', {
          duration: 2500
        })
      };

      console.log(this.dataOdeljenje.bolnica.naziv);
  }


  public update(): void {
    this.odeljenjeService.updateOdeljenje(this.dataOdeljenje).subscribe(() => {
      this.snackBar.open('Uspesno izmenjena odeljenje: ' + this.dataOdeljenje.naziv, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom izmene odeljenja. ', 'Zatvori', {
          duration: 2500
        })
      };

  }

  public delete(): void {
    this.odeljenjeService.deleteOdeljenje(this.dataOdeljenje.id).subscribe(() => {
      this.snackBar.open('Uspesno obrisano odeljenje: ' + this.dataOdeljenje.id, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom brisanja odeljenja. ', 'Zatvori', {
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