import { Component} from '@angular/core';
import { Breeds } from '../../../models/breeds';
import { ActivatedRoute } from '@angular/router';
import { BreedsService } from 'src/app/services/breeds.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-breeds-detail',
  templateUrl: './breeds-detail.component.html',
  styleUrls: ['./breeds-detail.component.css'],
})
export class BreedsDetailComponent {
  breeds?: Breeds;
  constructor(
    private route: ActivatedRoute,
    private breedsService: BreedsService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.getBreeds();
  }

  getBreeds(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.breedsService.getBreed(id).subscribe((breeds) => (this.breeds = breeds));
    
  }

  onBack(): void {
    this.location.back();
  }
}
