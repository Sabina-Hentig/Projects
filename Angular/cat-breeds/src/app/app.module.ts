import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { BreedsDetailComponent } from './components/breeds/breeds-detail/breeds-detail.component';
import { BreedsListComponent } from './components/breeds/breeds-list/breeds-list.component';
import { BreedsAddComponent } from './components/breeds/breeds-add/breeds-add.component';
import { MessagesComponent } from './components/messages/messages.component';
import { BreedsDeleteComponent } from './components/breeds/breeds-delete/breeds-delete.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SliderComponent } from './components/slider/slider.component';

@NgModule({
    declarations: [AppComponent, BreedsListComponent, DashboardComponent, BreedsDetailComponent, BreedsAddComponent, MessagesComponent, BreedsDeleteComponent, SliderComponent],
    imports: [BrowserModule, AppRoutingModule, FormsModule, NgbModule, RouterModule ],
    providers: [],
    bootstrap: [AppComponent],
})
export class AppModule { }