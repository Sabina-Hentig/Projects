import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BreedsAddComponent } from './breeds-add.component';

describe('BreedsAddComponent', () => {
  let component: BreedsAddComponent;
  let fixture: ComponentFixture<BreedsAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BreedsAddComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BreedsAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
