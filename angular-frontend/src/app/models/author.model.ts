import { BookInfo } from 'src/app/models/BookInfo';
export class Author {
  id!:number;
  name!:string;
  books!:Array<BookInfo>
}
