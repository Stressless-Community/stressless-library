

    var Swipes = new Swiper('.swiper-container', {
        loop: true,
        centeredSlides: true,
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
        pagination: {
            el: '.swiper-pagination',
            clickable: true,
        },
        autoplay:{
            delay:3000,
            disableOnInteraction: false,
        }
    });

// dealing with the copyright date
document.getElementById("date").innerHTML = new Date().getFullYear()

// Hide or show sections

function sectionNavigation(sectionName){
    Array.prototype.forEach.call(document.getElementsByClassName("section"), function(element) {
        if(!element.classList.contains('hidden')){
            element.classList.add('hidden')
        }
    });
    if(sectionName=='dashbord'){
        document.getElementById('bookCasesBar').classList.add('hidden')
    }else{
        if(document.getElementById('bookCasesBar').classList.contains('hidden')){
            document.getElementById('bookCasesBar').classList.remove('hidden')
        }
    }
    document.getElementById(sectionName).classList.remove('hidden')
}

//dealing with the menu
document.getElementById('menu').addEventListener('click',()=>{
    console.log('hello heloo')
    if(document.getElementById('dropdownDots').classList.contains('hidden')){
        document.getElementById('dropdownDots').classList.remove('hidden')
    } 
})

document.getElementById('dropdownDots').addEventListener('mouseout',()=>{
    document.getElementById('dropdownDots').classList.add('hidden')
})

//dealing with the bookcases truncate style and other styles
function manageTruncate(el,elover){
    el.addEventListener("mouseover",function(){
        elover.classList.remove('hidden')
    })
    el.addEventListener("mouseout",function(){
        el.classList.remove('hidden')
        el.classList.add('truncate')
        elover.classList.add('hidden')
    })
}
for(let i=1;i<=10;i++){
    manageTruncate(document.getElementById('bookcase'+i),document.getElementById('bookcaseover'+i))
    if(i<10){
        document.getElementById('bookcase'+i).classList.add('border-r')
        document.getElementById('bookcase'+i).classList.add('border-yellow-600')
        document.getElementById('bookcaseover'+i).classList.add('border-r')
        document.getElementById('bookcaseover'+i).classList.add('border-yellow-600')
    }
}

// dealing with the search input
document.getElementById('search').addEventListener("input",function(){

    if(document.getElementById('search').value){
        sectionNavigation('findbook')
    }else{
        sectionNavigation('home')
        location.reload()
    }
    
})

//Add book input fields

const isbn = document.getElementById('isbn')
const bookImg = document.getElementById("cover")
const bookTitle = document.getElementById("title")
const bookSubTitle =  document.getElementById('subtitle')
const bookAuthors = document.getElementById ('authors')   
const bookPrintKind = document.getElementById ('kind')
const bookPublishedDate =  document.getElementById ('publishedDate')
const bookPublisher =  document.getElementById ('publisher')
const bookDescription =  document.getElementById ("description")
const bookLanguage = document.getElementById('language')
const bookPages =  document.getElementById ("pageCount")
const bookBranch = document.getElementById ("branch")


// display book info functions
function displayInfo(data={}){

    if(data.largeCoverUrl){
        document.getElementById("infoImage").src=data.largeCoverUrl
    }

    if(data.title){
        document.getElementById("infoTitle").innerText=data.title 
    }

    if(data.subtitle){
        document.getElementById('infoSubtitle').innerText=data.subtitle
    }
    
    let authors =''
    data.authors.forEach(element => {
        authors += element.name+ ", "
        
    });

    authors = authors.substr(0, authors.length - 1)
    authors = authors.substr(0, authors.length - 1)
    document.getElementById ('infoAuthors').innerText=authors
    
    document.getElementById ('infoShortDescription').innerText=data.description
    document.getElementById ('infoPrintKind').innerText=data.kind
    document.getElementById ('infoPublishedDate').innerText=data.publishedDate
    document.getElementById ('infoPublisher').innerText=data.publisher
    document.getElementById ("infoDescription").innerText=data.description
    document.getElementById ("infoPages").innerText=data.pageCount

    authorsData = ''
    data.authors.forEach(author=>{
        authorsData += '<div class="flex flex-col space-y-4"><p class="text-2xl font-bold">'+author.name+'</p><hr><p class="text-slate-700 text-lg font-extralight tracking-wide [word-spacing:3px]">'+author.description+'</p></div>'
    })
    
    document.getElementById('infoAuthorsDetails').innerHTML = authorsData

    sectionNavigation('bookinfo')
}

// show book info

function showBookInfo(isbn){
    
    fetch('/books/'+isbn).then(data => data.json())
    .then (json =>{
        console.log(json)
        displayInfo(json)
    
    })
    
}

// New book modal

function newBookModal(){ 

    document.getElementById('newbookInfo1').classList.remove('hidden')
    document.getElementById('modal').classList.toggle('hidden')
}

function newAuthorModal(){
    document.getElementById('authormodal').classList.toggle('hidden')
}

function newPublisherModal(){
    document.getElementById('publishermodal').classList.toggle('hidden')
}

// Previous - Next functions in new book registration

let currentDivNum = 1
let previousBtn = document.getElementById("previous")
let nextBtn = document.getElementById("next")
let previousNextBox = document.getElementById('previousNextBox')
let submitbook = document.getElementById('submitbook')

//Data validation function
const errmsg = document.getElementById('errmsg')
let isbnErr = false
let isbnExistsErr = false

function fieldsEmpty(currentSecion){
    switch (currentSecion) {
        case 1:
            if(isbn.value=='' || bookTitle.value=='' || isbnErr || isbnExistsErr){
                errmsg.innerText = 'ISBN and title can\'t be empty !'
                if(isbnExistsErr)errmsg.innerText = 'The book with this ISBN is already registered !'
                if(isbnErr)errmsg.innerText = 'ISBN must be of 10 or 13 digits!'

                errmsg.classList.remove('hidden')
                setTimeout(() => {
                    errmsg.classList.add('hidden')   
                },2000);
                return true
            }else{
                errmsg.innerText = ''
                return false
            }
            break;

            case 2:
                if(bookDescription.value=='' || bookLanguage.value==''){
                    errmsg.innerText = 'All the fields must be completed'
                    errmsg.classList.remove('hidden')
                    setTimeout(() => {
                        errmsg.classList.add('hidden')   
                    },2000);
                    return true
                }else{
                    errmsg.innerText = ''
                    return false
                }
            break;

            case 3:
                if(bookAuthors.value=='' || bookPublisher.value=='' || bookPublishedDate.value==''){
                    errmsg.innerText = 'All the fields must be completed'
                    errmsg.classList.remove('hidden')
                    setTimeout(() => {
                        errmsg.classList.add('hidden')   
                    },2000);
                    return true
                }else{
                    errmsg.innerText = ''
                    return false
                }
            break;

            case 4:
                if(bookPages.value<10 || bookPrintKind.value=='' || bookBranch.value==''){
                    if(bookPages.value<10){
                        errmsg.innerText = 'The number of pages can\'t be less than 10.'
                        errmsg.classList.remove('hidden')
                        setTimeout(() => {
                            errmsg.classList.add('hidden')   
                        },2000); 
                    }

                    errmsg.innerText = 'All the fields must be completed'
                    errmsg.classList.remove('hidden')
                    setTimeout(() => {
                        errmsg.classList.add('hidden')   
                    },2000);
                    return true
                }else{
                    errmsg.innerText = ''
                    return false
                }
            break;
    
        default:
            break;
    }
}

isbn.addEventListener('input',function(){
    if(!(isbn.value.length==10 || isbn.value.length==13)){
            isbnErr = true
            errmsg.innerText = 'ISBN must be of 10 or 13 digits'
            errmsg.classList.remove('hidden')
            setTimeout(() => {
                        errmsg.classList.add('hidden')   
            },2000);
    }else{
        verifyBook(isbn.value)
        isbnErr = false
    }
})

//Previous - Next add book modal functions
function previous(){
    currentDivNum--
    document.getElementById('newbookInfo'+(currentDivNum+1)).classList.add('hidden')
    document.getElementById('newbookInfo'+currentDivNum).classList.remove('hidden')
    if(!submitbook.classList.contains('hidden')){
        submitbook.classList.add('hidden')
    }
    if(currentDivNum==1){
        previousBtn.classList.add('hidden')
        previousNextBox.classList.remove('flex-row')
        previousNextBox.classList.remove('justify-between')
        previousNextBox.classList.add('justify-end')
    }else{
        if(previousBtn.classList.contains('hidden')){
            previousBtn.classList.remove('hidden')
        }
        if(nextBtn.classList.contains('hidden')){
            nextBtn.classList.remove('hidden')
        }
    }
}

function next(){
    if(!fieldsEmpty(currentDivNum)){
        currentDivNum++
        document.getElementById('newbookInfo'+(currentDivNum-1)).classList.add('hidden')
        document.getElementById('newbookInfo'+currentDivNum).classList.remove('hidden')
        if(currentDivNum==5){
            nextBtn.classList.add('hidden')
            submitbook.classList.remove('hidden')
        }else{

            if(previousNextBox.classList.contains('justify-end')){
                previousNextBox.classList.remove('justify-end')
                previousNextBox.classList.add('flex-row')
                previousNextBox.classList.add('justify-between')
            }
            if(previousBtn.classList.contains('hidden')){
                previousBtn.classList.remove('hidden')
            }
            if(nextBtn.classList.contains('hidden')){
                nextBtn.classList.remove('hidden')
            }
        }

    }
}
// End Previous-Next add-book functions

// verify book from google apis     
function getBookInfo(){

fetch("https://www.googleapis.com/books/v1/volumes?q=isbn:"+isbn.value)
    .then(response => response.json())
    .then(json => {
        console.log(json.items[0].volumeInfo)

        if(json.items[0].volumeInfo){

            if(json.items[0].volumeInfo.title){
                document.getElementById("title").value=json.items[0].volumeInfo.title
            }

            if(json.items[0].volumeInfo.subtitle){
                document.getElementById("subtitle").value=json.items[0].volumeInfo.subtitle
            }

            if(json.items[0].volumeInfo.description){
                document.getElementById("description").value=json.items[0].volumeInfo.description
            }

            if(json.items[0].volumeInfo.pageCount){
                document.getElementById("pageCount").value=json.items[0].volumeInfo.pageCount
            }

            if(json.items[0].volumeInfo.publishedDate){
                document.getElementById("publishedDate").value=json.items[0].volumeInfo.publishedDate
            }

            if(json.items[0].volumeInfo.printType){
                document.getElementById("kind").value=json.items[0].volumeInfo.printType
            }

            if(json.items[0].volumeInfo.language){
                if(json.items[0].volumeInfo.language=="fr"){
                    document.getElementById("language").value="FRANÇAIS"
                }
                if(json.items[0].volumeInfo.language=="en"){
                    document.getElementById("language").value="ENGLISH"
                }
            } 
            
        }else{
            errmsg.innerText = 'The book with this ISBN couldn\'t be found on google apis.'
            errmsg.classList.remove('hidden')
            setTimeout(() => {
                        errmsg.classList.add('hidden')   
            },2000);
        }

        
        
}).catch((err)=>{
    isbnExistsErr = false
    errmsg.innerText = 'Couldn\'t fetch from google apis, please verify your internet connexion.'
    errmsg.classList.remove('hidden')
    setTimeout(() => {
                errmsg.classList.add('hidden')   
    },2000);
    console.log(err)
});

}

// Verify if book exists in database

function verifyBook(isbn){
    if(isbn){  
        fetch("/books/"+isbn)
        
    .then(res=>{
        if(res.status===200){
            isbnExistsErr = true
            errmsg.innerText='The book with this isbn is already registered !'
            if(errmsg.classList.contains('hidden')){
                errmsg.classList.remove('hidden')
                setTimeout(() => {
                        errmsg.classList.add('hidden')   
                },2000);
            }
        }else{
            getBookInfo()
        }
    }) 

    }
    
}

// Author autocomplete 

$(document).ready(function() {
    $('#authors').select2({
        ajax: {
        url: '/authors/search',
        data: function (params) {
        var query = {
            keyword: params.term
        }

        // Query parameters will be ?search=[term]
        return query;
        },
        processResults: function (data) {
            let autoauthors = []
            data.forEach(element => {
                autoauthors.push({
                    id:element.id,
                    text:element.name
                })
            });
        return {
            results: autoauthors
        };

        }
    }
    });

    $('#branch').select2({
        ajax: {
        url: '/branches/search',
        data: function (params) {
            var query = {
                keyword: params.term
            }
    
            // Query parameters will be ?keyword=[term]
            return query;
        },
        processResults: function (data) {
            let branchesList = []
            data.forEach(element => {
                branchesList.push({
                    id:element.id,
                    text:element.id
                })
            });
        return {
            results: branchesList
        };

        }
    }
    });
    
    


    $('#publisher').select2({
        ajax: {
        url: '/publishers/search',
        data: function (params) {
            var query = {
                keyword: params.term
            }
    
            // Query parameters will be ?keyword=[term]
            return query;
        },
        processResults: function (data) {
            let publishersList = []
            data.forEach(element => {
                publishersList.push({
                    id:element.id,
                    text:element.name
                })
            });
        return {
            results: publishersList
        };

        }
    }
    }
        
    )
});


//
// POST methods implementation:
//

async function postData(url = "", data = {}) {
// Default options are marked with *
const response = await fetch(url, {
    method: "POST", // *GET, POST, PUT, DELETE, etc.
    mode: "cors", // no-cors, *cors, same-origin
    cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
    credentials: "same-origin", // include, *same-origin, omit
    headers: {
    "Content-Type": "application/json",
    // 'Content-Type': 'application/x-www-form-urlencoded',
    },
    pagination: {
        el: '.swiper-pagination',
        clickable: true,
    },
    autoplay:{
        delay:3000,
        disableOnInteraction: false,
    }
});

}

