


function getBookInfo(isbn){

    fetch("https://www.googleapis.com/books/v1/volumes?q=isbn:"+isbn)

        .then(response => response.json())
        .then(json => {
            console.log(json.items[0].volumeInfo)
//            document.getElementById("isbn").value=json.items[0].volumeInfo
            document.getElementById("title").value=(json.items[0].volumeInfo.title)
            document.getElementById("subtitle").value=json.items[0].volumeInfo.subtitle
            document.getElementById("description").value=json.items[0].volumeInfo.description
            document.getElementById("pageCount").value=json.items[0].volumeInfo.pageCount
            document.getElementById("publishedDate").value=json.items[0].volumeInfo.publishedDate
            document.getElementById("kind").value=json.items[0].volumeInfo.printType
            document.getElementById("publisher").value=json.items[0].volumeInfo.printType
//            document.getElementById("branch").value="000"
    });

}

getBookInfo(9782100721511)