import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { BooksService } from 'src/app/services/http.service';
import { BookInfo } from 'src/app/models/BookInfo';


@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.scss']
})
export class BookComponent implements OnInit {
  book!:BookInfo;
  constructor(private activatedRouter:ActivatedRoute, private http : BooksService) { }

  ngOnInit() {
    //This module will help me to get the param in the URL
    this.http.getBookDetails(this.activatedRouter.snapshot.paramMap.get("isbn")+"")
    .subscribe((data) =>{
      this.book=data;
    })

  }

}
