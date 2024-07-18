import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BreedsService } from 'src/app/services/breeds.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-breeds-delete',
  templateUrl: './breeds-delete.component.html',
  styleUrls: ['./breeds-delete.component.css'],
})
export class BreedsDeleteComponent {
  id?: number;
  constructor(
    private route: ActivatedRoute,
    private BreedsService: BreedsService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
  }

  onConfirm(): void {
    if (this.id) {
      this.BreedsService.deleteBreed(this.id).subscribe(() => {
        console.log('BreedsDeleteComponent: deleted Breed with id ' + this.id);
        this.location.back();
      });
    }
  }

  onCancel(): void {
    this.location.back();
  }
}
