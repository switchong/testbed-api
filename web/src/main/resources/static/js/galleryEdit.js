let isSave = true;
let nftOk = false;
let frameOk = false;
let backgroundOk = false;
let nftTotalOK = true;
let frameTotalOK = true;
let backgroundTotalOK = true;
let nftMaxLength = 3;
let frameMaxLength = 3;
let backgroundMaxLength = 1;
let nowSelect = 'NFT';

window.addEventListener("DOMContentLoaded", ()=>{
    let nftNum = 0;
    let frameNum = 0;
    let backgroundNum = 0;
    let nftArray = [];
    let frameArray = [];
    let backgroundArray = [];
    const totalNft = document.querySelectorAll('.edit-nft').length;
    const totalFrame = document.querySelectorAll('.edit-frame').length;
    const totalBackground = document.querySelectorAll('.edit-background').length;
    const selectList = document.querySelectorAll('.gallery-edit-select');

    const nftImages = document.querySelectorAll(".gallery-edit-slide-item.edit-slice-item-nft");
    const frameImages = document.querySelectorAll(".gallery-edit-slide-item.edit-slice-item-frame");
    const backgroundImages = document.querySelectorAll('.gallery-edit-slide-item.edit-slice-item-background');

    selectList.forEach((item, index)=>{
        item.addEventListener('click', ()=>{
            if(nowSelect !== item.innerText) {
                nowSelect = item.innerText;
                goFirst();
                frameMaxLength = nftMaxLength;
                console.log(backgroundMaxLength);
            }
        })
    })

    function changeImage(item, str) {
        let nowSlideItems = document.querySelectorAll(".image-container");
        if(str === 'nft') {
            if(item !== '') {
                nftArray.push(item.childNodes[1].getAttribute('src'));
            }
            else {
                nowSlideItems.forEach((item, index)=>{
                    item.getElementsByClassName('inner-picture')[0].setAttribute('src','../../img/etc/no-image.png');
                })
            }
            nftArray.forEach((item, index)=>{
                nowSlideItems[index].getElementsByClassName('inner-picture')[0].setAttribute('src',item);
            })
        }
        else if(str === 'frame') {
            if(item !== '') {
                frameArray.push(item.childNodes[1].getAttribute('src'));
            }
            else {
                nowSlideItems.forEach((item, index) => {
                    item.getElementsByClassName('outer-frame')[0].setAttribute('src', '../../img/etc/no-image.png');
                })
            }
            frameArray.forEach((item, index) => {
                nowSlideItems[index].getElementsByClassName('outer-frame')[0].setAttribute('src',item);
            })
        }
        else if(str === 'background') {
            nowSlideItems = document.querySelectorAll('.gallery-slide-list');
            if(item !== '') {
                backgroundArray.push(item.childNodes[1].getAttribute('src'));
            }
            else {
                nowSlideItems.forEach((item, index) => {
                    item.getElementsByClassName('back-frame')[0].setAttribute('src','../../img/etc/no-image.png');
                })
            }
            backgroundArray.forEach((item, index) => {
               nowSlideItems[index].getElementsByClassName('back-frame')[0].setAttribute('src', item);
            });
        }
    }

    function numberRerange(num, str) {
        let numberList;
        if(str === 'nft') {
            numberList = document.querySelectorAll('.gallery-edit-select-number.number-nft');
            nftNum -= 1;
            nftOk = false;
        }
        else if(str === 'frame') {
            numberList = document.querySelectorAll('.gallery-edit-select-number.number-frame');
            frameNum -= 1;
            frameOk = false;
        }
        else if(str === 'background') {
            numberList = document.querySelectorAll('.gallery-edit-select-number.number-background');
            backgroundNum -= 1;
            backgroundOk = false;
        }


        numberList.forEach((item, index) => {
            if(item.innerText) {
                if(item.innerText > num) {
                    item.innerText = item.innerText - 1;
                }
            }
        })
    }

    function deleteImage(num, str) {
        if(str === 'nft') {
            nftArray.splice(num-1,1);
            console.log(nftArray);
            changeImage('', 'nft');
        }
        else if(str === 'frame') {
            frameArray.splice(num-1, 1);
            console.log(frameArray);
            changeImage('', 'frame');
        }
        else if(str === 'background') {
            backgroundArray.splice(num-1,1);
            console.log(backgroundArray);
            changeImage('', 'background');
        }
    }

    nftImages.forEach((item, index) => {
        item.addEventListener("click",()=>{

            if(item.childNodes[5].innerText !== '') {
                const containNum = item.childNodes[5].innerText;
                item.childNodes[5].innerText = '';
                numberRerange(containNum, 'nft');
                deleteImage(containNum, 'nft');
                if(!nftTotalOK) {
                    nftTotalOK = true;
                }
            }

           else  {
               if(nftNum < nftMaxLength) {
                   console.log(item.childNodes[1].getAttribute('src'));
                   item.childNodes[5].innerText = nftNum + 1;
                   changeImage(item, 'nft')
                   nftNum++;
                   if(nftNum === nftMaxLength) {
                       nftOk = true;
                   }
                   if(nftNum === totalNft) {
                       nftTotalOK = false;
                   }
               }
           }
        });
    })

    frameImages.forEach((item, index) => {
        item.addEventListener('click', ()=>{

            if(item.childNodes[5].innerText !== '') {
                const containNum = item.childNodes[5].innerText;
                item.childNodes[5].innerText = '';
                numberRerange(containNum, 'frame');
                deleteImage(containNum, 'frame');
                if(!frameTotalOK) {
                    frameTotalOK = true;
                }
            }

            else  {
                if(frameNum < frameMaxLength) {
                    console.log(item.childNodes[1].getAttribute('src'));
                    item.childNodes[5].innerText = frameNum + 1;
                    changeImage(item, 'frame')
                    frameNum++;
                    if(frameNum === frameMaxLength) {
                        frameOk = true;
                    }
                    if(frameNum === totalFrame) {
                        frameTotalOK = false;
                    }
                }
            }
        })
    })

    backgroundImages.forEach((item, index) => {
        item.addEventListener('click', () => {
            if(item.childNodes[5].innerText !== '') {
                const containNum = item.childNodes[5].innerText;
                item.childNodes[5].innerText = '';
                numberRerange(containNum, 'background');
                deleteImage(containNum, 'background');
                if(!backgroundTotalOK) {
                    backgroundTotalOK = true;
                }
            }

            else  {
                if(backgroundNum < backgroundMaxLength) {
                    console.log(item.childNodes[1].getAttribute('src'));
                    item.childNodes[5].innerText = backgroundNum + 1;
                    changeImage(item, 'background');
                    backgroundNum++;
                    if(backgroundNum === backgroundMaxLength) {
                        backgroundOk = true;
                    }
                    if(backgroundNum === totalBackground) {
                        backgroundTotalOK = false;
                    }
                }
            }
        })
    })
})