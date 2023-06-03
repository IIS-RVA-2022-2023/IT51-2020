import { Component, OnInit } from '@angular/core';
import {
  trigger,
  state,
  style,
  animate,
  transition
} from '@angular/animations';

@Component({
  selector: 'app-pocetna',
  templateUrl: './pocetna.component.html',
  styleUrls: ['./pocetna.component.css']
})
export class PocetnaComponent {
  
  title1: string = 'Angular Project';
  title2: string = 'Enjoy your stay';
  isFirstTitleVisible: boolean = true;
  isSecondTitleVisible: boolean = false;

  ngOnInit(): void {
    setInterval(() => {
      this.isFirstTitleVisible = !this.isFirstTitleVisible;
      this.isSecondTitleVisible = !this.isSecondTitleVisible;
    }, 3000);
  }


}