import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BreedsDetailComponent } from './breeds-detail.component';

describe('BreedsDetailComponent', () => {
  let component: BreedsDetailComponent;
  let fixture: ComponentFixture<BreedsDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BreedsDetailComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BreedsDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
