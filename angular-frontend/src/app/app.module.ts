import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LayoutComponent } from './layout/layout.component';
import { ContentComponent } from './layout/content/content.component';
import { FooterComponent } from './layout/footer/footer.component';
import { HeaderComponent } from './layout/header/header.component';
import { BookComponent } from './layout/content/book/book.component';
import { HomeComponent } from './layout/content/home/home.component';
import { ListComponent } from './layout/content/list/list.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HttpHeaders, HTTP_INTERCEPTORS } from '@angular/common/http';
import { HttpHeadersInterceptor } from './interceptors/http-headers.interceptor';
import { HttpErrorsInterceptor } from './interceptors/http-errors.interceptor';
import { NgxPaginationModule } from 'ngx-pagination';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { AttendanceComponent } from './layout/content/attendance/attendance.component';
import { SafePipe } from './safe.pipe';


@NgModule({
  declarations: [
    AppComponent,
    LayoutComponent,
    ContentComponent,
    FooterComponent,
    HeaderComponent,
    BookComponent,
    HomeComponent,
    ListComponent,
    AttendanceComponent,
    SafePipe,


  ],
  imports: [
    BrowserModule,
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    BrowserModule,
    HttpClientModule,
    NgxPaginationModule,
    Ng2SearchPipeModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass : HttpHeadersInterceptor,
      multi:true,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass : HttpErrorsInterceptor,
      multi:true,
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {

 }
