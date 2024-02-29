import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BreedsListComponent } from './components/breeds/breeds-list/breeds-list.component';
import { MessagesComponent } from './components/messages/messages.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { BreedsDetailComponent } from './components/breeds/breeds-detail/breeds-detail.component';
import { BreedsAddComponent } from './components/breeds/breeds-add/breeds-add.component';
import { BreedsDeleteComponent } from './components/breeds/breeds-delete/breeds-delete.component';


const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'breeds', component: BreedsListComponent },
  { path: 'messages', component: MessagesComponent },
  { path: 'breeds/detail/:id', component: BreedsDetailComponent },
  { path: 'breeds/delete/:id', component: BreedsDeleteComponent },
  { path: 'breeds/add', component: BreedsAddComponent },
  { path: 'dashboard', component: DashboardComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
