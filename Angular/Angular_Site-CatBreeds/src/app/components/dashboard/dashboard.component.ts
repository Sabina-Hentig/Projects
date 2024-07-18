import { Component, OnInit } from '@angular/core';
import { Breeds } from 'src/app/models/breeds';
import { BreedsService } from 'src/app/services/breeds.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  breed: Breeds[] = [];

  constructor(private breedsService: BreedsService) {}

  ngOnInit(): void {
    this.getBreeds();
  }

  getBreeds(): void {
    this.breedsService.getBreeds().subscribe((breeds) => (this.breed = breeds.slice(0, 5)));
  }
}


