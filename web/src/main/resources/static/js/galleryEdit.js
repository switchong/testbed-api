let isSave = true;
let nftOk = false;
let maxLength = 3;

window.addEventListener("DOMContentLoaded", ()=>{
    let nftNum = 0;
    let frameNum = 0;
    let BackgroundNum = 0;
    let nftArray = [];

    const nftImages = document.querySelectorAll(".gallery-edit-slide-item");

    function changeImage(item) {
        const nowSlideItems = document.querySelectorAll(".image-container");
        nftArray.push(item.childNodes[1].getAttribute('src'));
        nftArray.forEach((item, index)=>{
            nowSlideItems[index].getElementsByClassName('inner-picture')[0].setAttribute('src',item);
        })
    }

    nftImages.forEach((item, index) => {
        item.addEventListener("click",()=>{

           if(nftNum < maxLength && item.childNodes[5].innerText === '') {
               console.log(item.childNodes[1].getAttribute('src'));
               item.childNodes[5].innerText = nftNum + 1;
               changeImage(item)
               nftNum++;
               if(nftNum === maxLength) {
                   nftOk = true;
               }
           }
        });
    })
})