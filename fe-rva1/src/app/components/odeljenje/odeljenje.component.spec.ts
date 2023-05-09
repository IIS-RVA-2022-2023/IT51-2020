import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OdeljenjeComponent } from './odeljenje.component';

describe('OdeljenjeComponent', () => {
  let component: OdeljenjeComponent;
  let fixture: ComponentFixture<OdeljenjeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OdeljenjeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OdeljenjeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
