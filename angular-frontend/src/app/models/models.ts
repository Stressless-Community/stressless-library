export interface Book{
    isbn: string;
	title: string;
	subtitle: string;
	authors : Array<Author>;
	publishedDate : string;
	language: string;
	coverURL: string;
	bookStatus: string;
	isRefence: string;
	description: string;
	branch: string;
	category: string;
	bookcase: string;
	pdfavailable: string;
	publisher: string;
	pages: number;
}

export interface APIResponse<T>{
    results : Array<T>;
}

export interface Author{
    name:string;
}


