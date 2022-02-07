window.addEventListener("DOMContentLoaded", ()=>{
    let nftNum = 0;
    let frameNum = 0;
    let BackgroundNum = 0;

    const nftImages = document.querySelectorAll(".gallery-edit-slide-item");

    nftImages.forEach((item, index) => {
        item.addEventListener("click",()=>{

           if(nftNum < 3 && item.childNodes[5].innerText === '') {
               console.log(item.childNodes[5],"2");
               item.childNodes[5].innerText = nftNum + 1;
               nftNum++;
           }
        });
    })
})