import { Component } from '@angular/core';
import { Breeds } from 'src/app/models/breeds';
import { BreedsService } from '../../../services/breeds.service';
import { MessagesService } from 'src/app/services/messages.service';

@Component({
  selector: 'app-breeds-list',
  templateUrl: './breeds-list.component.html',
  styleUrls: ['./breeds-list.component.css'],
})
export class BreedsListComponent {
  breed: Breeds[]=[];

  constructor(
    private BreedsService: BreedsService,
    private messagesService: MessagesService
  ) {}

  ngOnInit(): void {
    this.getBreeds();
  }

  getBreeds(): void {
    this.BreedsService.getBreeds().subscribe((breeds) => {
      this.breed = breeds;
      this.messagesService.add(
        'BreedsComponent: finished fetching breedss - ' + Date.now()
      );
    });
  }
  
}
