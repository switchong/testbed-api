let currentNum = 0;
let prevBtn;
let nextBtn;
let slideContainer;
let slides;

const goNext = () => {
    if(slides.length !== currentNum + 1) {
        currentNum++;
        slides.forEach((item)=>{
            item.style.transform = 'scale(1)';
        });
        console.log(currentNum, slides.length);
        slideContainer.style.transform = `translateX(${-(slides[currentNum].getBoundingClientRect().left - slideContainer.getBoundingClientRect().left)}px)`;
        slides.forEach((item, index)=>{
            if(index !== currentNum) {
                item.style.transform = 'scale(0.9)';
            }
        });
    }
}

const goPrev = () => {
    if(currentNum > 0) {
        currentNum--;
        slides.forEach((item)=>{
            item.style.transform = 'scale(1)';
        });
        console.log(currentNum, slides.length);
        slideContainer.style.transform = `translateX(${-(slides[currentNum].getBoundingClientRect().left - slideContainer.getBoundingClientRect().left)}px)`;
        slides.forEach((item, index)=>{
            if(index !== currentNum) {
                item.style.transform = 'scale(0.9)';
            }
        });
    }
}

const goFirst = () => {
    slides.forEach((item)=>{
        item.style.transform = 'scale(1)';
    });
    console.log(currentNum, slides.length);
    slideContainer.style.transform = 'translateX(0px)';
    currentNum = 0;
    slides.forEach((item, index)=>{
        if(index !== currentNum) {
            item.style.transform = 'scale(0.9)';
        }
    });
}

const Refresh = () => {
    window.removeEventListener('resize', goFirst);
    prevBtn.removeEventListener('click',goPrev);
    nextBtn.removeEventListener('click',goNext);
    goSlide();
}

const goSlide = () => {
    prevBtn = document.querySelector('#prevBtn');
    nextBtn = document.querySelector('#nextBtn');
    slideContainer = document.querySelector('.gallery-slide-list-container');
    slides = document.querySelectorAll('.gallery-slide-list');

    slides.forEach((item, index)=>{
        if(index !== currentNum) {
            item.style.transform = 'scale(0.9)';
        }
    });

    console.log(currentNum, slides.length);


    window.addEventListener('resize',goFirst);

    prevBtn.addEventListener('click',goPrev);

    nextBtn.addEventListener('click',goNext);
}

window.addEventListener('DOMContentLoaded',goSlide);