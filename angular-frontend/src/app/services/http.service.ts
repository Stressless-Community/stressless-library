import { HttpClient, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { environment as env } from 'src/environments/environment';
import { BookInfo } from '../models/BookInfo';

@Injectable({
  providedIn: 'root'
})
export class BooksService {

  constructor(private http : HttpClient) { }

  getBookList(): Observable<Array<BookInfo>>{
    return this.http
    .get<Array<BookInfo>>(env.BASE_URL+"/books",{
    })
  }
  searchBooks(searchValue: string): Observable<Array<BookInfo>>{

    return this.http.get<Array<BookInfo>>(env.BASE_URL+"/books/search/?keyword="+searchValue,{
    })


  }
  getBookDetails(isbn:string) :Observable<BookInfo> {

    return this.http.get<BookInfo>(env.BASE_URL+"/books/"+isbn,{
    })
  }

  handleError(error: HttpErrorResponse) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Erro ocorreu no lado do client
      errorMessage = error.error.message;
    } else {
      // Erro ocorreu no lado do servidor
      errorMessage =
        `Código do erro: ${error.status}, ` + `menssagem: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(errorMessage);
  }
}
