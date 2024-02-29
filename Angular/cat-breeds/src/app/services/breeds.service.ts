import { Injectable } from '@angular/core';
import { Breeds } from '../models/breeds';
import { Observable, of } from 'rxjs';
import { MessagesService } from './messages.service';

@Injectable({
  providedIn: 'root',
})
export class BreedsService {
private  breedKey = 'breeds';


private breeds: Breeds[] = [];

  constructor(private messagesService: MessagesService) {
    this.getBreedsFromLocalStorage();
  }

private getBreedsFromLocalStorage():void{
  const breeds = localStorage.getItem(this.breedKey);
  console.log(breeds);
  if (breeds){
    this.breeds = JSON.parse(breeds);
  }
}

private saveBreedsToLocalStorage():void{
  localStorage.setItem(this.breedKey, JSON.stringify(this.breeds))
}

  getBreeds(): Observable<Breeds[]> {
    const breeds = of(this.breeds);
    this.saveBreedsToLocalStorage();
    this.messagesService.add(
      'BreedsService: started fetching this.products - ' + Date.now()
    );
    return breeds;
  }

  getBreed(id: number): Observable<Breeds> {
    const breed = this.breeds.find((h) => h.id === id) as Breeds;
    this.messagesService.add(
      `BreedsService: fetched Breeds id=${id} - ${Date.now()}`
    );
    return of(breed);
  }

  addBreeds(breed?: Breeds): Observable<Breeds> {
    if (!breed || !breed.id) {
      this.messagesService.add(
        `BreedsService: Breeds is undefined - ${Date.now()}`
      );
      throw new Error('Breeds is undefined');
    }
    breed.id = +breed.id;
    console.log(this.breeds);
    if (this.breeds.find((h) => h.id === breed.id)) {
      this.messagesService.add(
        `BreedsService: Breeds with id=${breed.id} already exists - ${Date.now()}`
      );
      throw new Error('Breeds with this id already exists');
    }

    this.breeds.push(breed);
    this.saveBreedsToLocalStorage();
    this.messagesService.add(
      `BreedsService: added Breeds id=${breed.id} - ${Date.now()}`
    );
    return of(breed);
  }

  deleteBreed(breedId: number): Observable<void> {
    const breedsIndex = this.breeds.findIndex((h) => h.id === breedId);
    if (breedsIndex === -1) {
      this.messagesService.add(
        `BreedsService: Breeds with id=${breedId} does not exist - ${Date.now()}`
      );
      throw new Error('Breeds with this id does not exist');
    }
    this.breeds.splice(breedsIndex, 1);
    this.saveBreedsToLocalStorage();
    this.messagesService.add(
      `BreedsService: deleted Breeds id=${breedId} - ${Date.now()}`
    );
    return of(undefined);
  }
  
}
