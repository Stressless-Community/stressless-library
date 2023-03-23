import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, NgForm } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.scss']
})
export class ContentComponent implements OnInit {

  form: FormGroup;
  searchValue!: string;

  constructor() {
    this.form = new FormGroup({
      search: new FormControl(),
    });
  }

  ngOnInit(): void {}

  submitForm() {
    this.searchValue = this.form.value.search;
  }
}
