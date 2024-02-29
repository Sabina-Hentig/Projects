import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BreedsService } from 'src/app/services/breeds.service';
import { Location } from '@angular/common';
import { Breeds } from 'src/app/models/breeds';
import { MessagesService } from 'src/app/services/messages.service';

@Component({
  selector: 'app-breeds-add',
  templateUrl: './breeds-add.component.html',
  styleUrls: ['./breeds-add.component.css'],
})
export class BreedsAddComponent {
  breeds: Breeds = {
    id: -1,
    name: '',
    lifeSpan:-1 ,
    description: '',
  };


  constructor(
    private route: ActivatedRoute,
    private breedsService: BreedsService,
    private messageService: MessagesService,
    private location: Location
  ) {}

  onAdd(): void {
    try {
      this.breedsService.addBreeds(this.breeds).subscribe(() => {
        this.messageService.add(
          `breedsAddComponent: added ${this.breeds?.name} - ${Date.now()}`
        );
      });
    } catch (error) {
      this.messageService.add(
        `breedsAddComponent: error adding ${this.breeds?.name} - ${Date.now()}`
      );
    }
  }

  onBack(): void {
    this.location.back();
  }
}
