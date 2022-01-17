window.addEventListener('DOMContentLoaded',() => {
    const prevBtn = document.querySelector('#prevBtn');
    const nextBtn = document.querySelector('#nextBtn');
    const slideContainer = document.querySelector('.gallery-slide-list-container');
    const slides = document.querySelectorAll('.gallery-slide-list');
    let currentNum = 0;
    slides.forEach((item, index)=>{
        if(index !== currentNum) {
            item.style.transform = 'scale(0.9)';
        }
    });

    prevBtn.addEventListener('click',()=>{

         if(currentNum > 0) {
            currentNum--;
             slides.forEach((item)=>{
                     item.style.transform = 'scale(1)';
             });
            slideContainer.style.transform = `translateX(${-(slides[currentNum].getBoundingClientRect().left - slideContainer.getBoundingClientRect().left)}px)`;
            slides.forEach((item, index)=>{
                if(index !== currentNum) {
                    item.style.transform = 'scale(0.9)';
                }
            });
        }
    })

    nextBtn.addEventListener('click',()=>{
        if(slides.length !== currentNum + 1) {
            currentNum++;
            slides.forEach((item)=>{
                item.style.transform = 'scale(1)';
            });
            slideContainer.style.transform = `translateX(${-(slides[currentNum].getBoundingClientRect().left - slideContainer.getBoundingClientRect().left)}px)`;
            slides.forEach((item, index)=>{
                if(index !== currentNum) {
                    item.style.transform = 'scale(0.9)';
                }
            });
        }
    })
})