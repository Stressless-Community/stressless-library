import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BookInfo } from 'src/app/models/BookInfo';
import { BooksService } from 'src/app/services/http.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnInit {
  p:number=1;
  books!: Array<BookInfo>
  constructor(private service : BooksService, private router:Router) { }

  ngOnInit(
  ) {
    this.getBook();
  }

  public search(value:string){
    this.service.searchBooks(value).subscribe((data)=>{
      this.books=data;
    })

  }
  public getBook(){
    this.service.getBookList().subscribe((data)=>{
      this.books=data;
      console.log(this.books)
    })
  }
  public goAtBookComponent(isbn:string){
    this.router.navigate(["book/details/"],{queryParams:{isbn}})
  }

  }
