import { Author } from "./author.model";

export class BookInfo {
  isbn:string;
	title:string;
	subtitle:string;
	authors:Array<Author>;
	publishedDate:string;
	language:string;
	largeCoverUrl:string;
	bookStatus:string;
	isReference:string;
	description:string;
	branch:string;
	category:string;
	bookcase:string;
	pdfAvailable:boolean;
	publisher:string;
	pageCount:number;
  pdfurl:string;

	constructor(){
	this.isbn="";
	this.title="";
	this.subtitle=""
	this.authors= new Array;
	this.publishedDate=""
	this.language="";
	this.largeCoverUrl=""
	this.bookStatus=""
	this.isReference="false"
	this.description="";
	this.branch=""
	this.category=""
	this.bookcase="";
	this.pdfAvailable=false;
	this.publisher="";
	this.pageCount=0;
  this.pdfurl="";
	}
}
