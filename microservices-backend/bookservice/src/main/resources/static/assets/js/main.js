
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

let index_currentSlide = Swipes.realIndex;
let currentSlide = Swipes.slides[index_currentSlide]

