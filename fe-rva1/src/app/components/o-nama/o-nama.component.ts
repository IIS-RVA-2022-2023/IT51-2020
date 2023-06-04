import { ViewportScroller } from '@angular/common';
import { Component, HostListener, ElementRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-o-nama',
  templateUrl: './o-nama.component.html',
  styleUrls: ['./o-nama.component.css']
})
export class ONamaComponent {

  constructor(private scroller: ViewportScroller, private router: Router) {}


  OnInit(){
    
    this.ScrollToSecond();

  }

  ScrollToSecond(){
    this.scroller.scrollToAnchor('secondSlide');
  }

}