import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BreedsDeleteComponent } from './breeds-delete.component';

describe('BreedsDeleteComponent', () => {
  let component: BreedsDeleteComponent;
  let fixture: ComponentFixture<BreedsDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BreedsDeleteComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BreedsDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
