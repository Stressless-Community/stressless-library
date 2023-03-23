import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LayoutComponent } from './layout.component';
import { BookComponent } from './content/book/book.component';
import { HomeComponent } from './content/home/home.component';
import { ContentComponent } from './content/content.component';
import { ListComponent } from './content/list/list.component';

const routes: Routes = [
  {
    path: '',
    component: ListComponent,
  },
  {
    path: 'search',
    component: LayoutComponent,
  },
  {
    path: 'search/book/:id',
    component: BookComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class LayoutRoutingModule {}
