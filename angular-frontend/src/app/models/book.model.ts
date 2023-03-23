import { Author } from "./author.model";
import { Image } from "./image.model";

export class Book {
    title!: string;
    authors!: Author[];
    publisher!: string;
    publishedDate!: string;
    description!: string;
    pageCount!: string;
    averageRating!: string;
    imageLinks!: Image;
    language!: string;
    previewLink!: string;
    infoLink!: string;
}
