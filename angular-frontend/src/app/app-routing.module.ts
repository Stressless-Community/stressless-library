import { PdfviewerComponent } from './layout/content/book/pdfviewer/pdfviewer.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AttendanceComponent } from './layout/content/attendance/attendance.component';
import { BookComponent } from './layout/content/book/book.component';
import { ListComponent } from './layout/content/list/list.component';
import { LayoutComponent } from './layout/layout.component';

const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
  },

  {
    path: 'catalog',
    component: LayoutComponent,
  },

  {
    path: 'details/:isbn',
    component: BookComponent,
  },

  {
    path: 'details/:isbn/ebook/:pdfurl',
    component: PdfviewerComponent,
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
