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
let nftImages;
let frameImages;
let backgroundImages;

const dontclick = (list) => {
    list.forEach((item, index)=>{
        if(Number(item.childNodes[3].innerText) === currentNum || item.childNodes[3].innerText === '') {
            item.classList.remove('dontClick');
        }
        else {
            item.classList.add('dontClick');
        }
    })
}

const contentListLoad = () => {
    let nftNum = 0;
    let frameNum = 0;
    let backgroundNum = 0;
    let nftArray = [];
    let frameArray = [];
    let backgroundArray = [];
    let choiceOne = true;
    const totalNft = document.querySelectorAll('.edit-nft').length;
    const totalFrame = document.querySelectorAll('.edit-frame').length;
    const totalBackground = document.querySelectorAll('.edit-background').length;
    const selectList = document.querySelectorAll('.gallery-edit-select');
    const saveBtn = document.querySelectorAll('.gallery-edit-save');
    const plusBtn = document.querySelectorAll('.gallery-edit-plus-section');

    nftImages = document.querySelectorAll(".gallery-edit-slide-item.edit-slice-item-nft");
    frameImages = document.querySelectorAll(".gallery-edit-slide-item.edit-slice-item-frame");
    backgroundImages = document.querySelectorAll('.gallery-edit-slide-item.edit-slice-item-background');
    const closeBtn = document.querySelector('#closeEditBtn');

    window.addEventListener('beforeunload',(e)=>{
        let nowList3 = document.querySelectorAll('.gallery-slide-list')[currentNum];
        e.preventDefault();
        if(choiceOne) {
            if(nowList3.getAttribute('save') === 'false' && (nftNum !==0|| frameNum !==0 || backgroundNum !==0)) {
                e.returnValue = '지금 나가면 저장이 되지 않습니다. 정말로 나가시겠습니까?';
            }
            return;
        }
        else {
            choiceOne = true;
        }
    })

    plusBtn.forEach((item, index)=>{
        item.addEventListener('click',()=>{
            if(nowLocation === '/gallery/edit') {
                let nowList2 = document.querySelectorAll('.gallery-slide-list')[currentNum];
                if(nowList2.getAttribute('save') === 'true' && nowList2.getAttribute('moreValue') === '0') {
                    nowList2.setAttribute('moreValue','1');
                    MoreEdit();
                }
                else if(nowList2.getAttribute('moreValue') !== '0') {

                }
                else {
                    alert(`현재 섹션(${currentNum})에 대해서 저장되지 않았습니다.`);
                }
            }
        })
    })

    closeBtn.addEventListener('click',()=>{
        let nowList1 = document.querySelectorAll('.gallery-slide-list')[currentNum];
        if(choiceOne) {
            if(nowList1.getAttribute('save') === 'false' && (nftNum !==0|| frameNum !==0 || backgroundNum !==0)) {
                if(!window.confirm('지금 나가면 저장이 되지 않습니다. 정말로 나가시겠습니까?')){
                    return;
                }
            }
            choiceOne = false;
            window.location.href = '/gallery/mycollection';
        }
    })

    saveBtn.forEach((item, index) => {
        item.addEventListener('click',() => {
            let imageContainers = document.querySelectorAll('.inner-picture');
            let nowList = document.querySelectorAll('.gallery-slide-list')[currentNum];
            let countNft=0;
            imageContainers.forEach((item, index)=>{
                if(index >= 3 * (currentNum + 1) - 3 && index <= 3*(currentNum + 1) - 1) {
                    if(item.getAttribute('src').includes("http")){
                        countNft++;
                    }
                }
            })
            if(countNft === 3 && nftTotalOK && nowList.getAttribute('save') === "false") {
                nowList.setAttribute('save', 'true');
                alert('저장되었습니다.');
            }
            else {
                if(!nftTotalOK) {
                    alert('모든 nft를 넣으셔야 저장 할수 있습니다.');
                }
                else if(countNft !== 3) {
                    alert(`3개를 모두 채우셔야 저장 할수 있습니다!`);
                }
                else if(nowList.getAttribute('save') === 'true') {
                    alert(`현재 섹션(${currentNum})에 대해서 이미 저장 되었습니다.`);
                }
            }
        })
    })

    selectList.forEach((item, index)=>{
        item.addEventListener('click', ()=>{
            if(nowSelect !== item.innerText) {
                nowSelect = item.innerText;
                frameMaxLength = nftMaxLength;
                console.log(backgroundMaxLength);
            }
        })
    })

    nftImages.forEach((item, index) => {
        item.addEventListener("click",(e)=>{
            const nowNft = document.querySelectorAll('.gallery-slide-list')[currentNum];
            const nowNftNum = Number(nowNft.getAttribute('nftNum'));
            if(item.childNodes[3].innerText !== '') {
                if(Number(item.childNodes[3].innerText) === currentNum) {
                    item.childNodes[3].innerText = '';
                    nowNft.querySelectorAll('.inner-picture').forEach((items, index)=>{
                        if(items.getAttribute('data-nftid') === item.childNodes[1].getAttribute('data-nftid')) {
                            items.setAttribute('src', '../../img/etc/no-image.png');
                            items.setAttribute('data-nftid','null');
                            items.parentNode.querySelector('.inner-picture-delete-btn').remove();
                            nowNft.setAttribute('nftNum', (nowNftNum -1).toString());
                            nowNft.setAttribute('save', 'false');
                            nftNum--;
                        }
                    })
                }
            }

           else  {
               if(nftNum < nftMaxLength) {
                   if(nowNftNum < 3) {
                       const nftDelete = document.createElement('div');
                       nftDelete.classList.add('inner-picture-delete-btn');
                       nftDelete.innerText = 'X';
                       item.childNodes[3].innerText = currentNum;
                       nowNft.setAttribute('nftNum', (nowNftNum + 1).toString());
                       let count=0;
                       nowNft.querySelectorAll('.inner-picture').forEach((items, index) =>{
                           if(items.getAttribute('src').includes('http')) {

                           }
                           else {
                               if(count === 0) {
                                   items.setAttribute('src',item.childNodes[1].getAttribute('src'));
                                   items.setAttribute('data-nftid',item.childNodes[1].getAttribute('data-nftid'));
                                   count++;
                                   items.parentNode.appendChild(nftDelete);
                                   nftDelete.addEventListener('click',(e)=>{
                                       const nowNftNum = Number(nowNft.getAttribute('nftNum'));
                                       const searchNumNft = e.target.parentNode.childNodes[3].getAttribute('data-nftid');
                                       nftImages.forEach((items, index)=>{
                                           if(items.childNodes[1].getAttribute('data-nftid') === searchNumNft) {
                                               items.childNodes[3].innerText = '';
                                           }
                                       })
                                       e.target.parentNode.childNodes[3].setAttribute('src','../../img/etc/no-image.png');
                                       e.target.parentNode.childNodes[3].setAttribute('data-nftid','null');
                                       nowNft.setAttribute('nftNum',  (nowNftNum - 1).toString());
                                       nowNft.setAttribute('save','false');
                                       nftNum--;
                                       e.target.remove();
                                   })
                               }
                           }
                       })
                       nftNum++;
                       nowNft.setAttribute('save','false');
                   }
               }
           }
        });
    })

    frameImages.forEach((item, index) => {
        item.addEventListener('click', ()=>{
            const nowframe = document.querySelectorAll('.gallery-slide-list')[currentNum];
            const nowFrameNum = Number(nowframe.getAttribute('frameNum'));
            if(item.childNodes[3].innerText !== '') {
                if(Number(item.childNodes[3].innerText) === currentNum) {
                    item.childNodes[3].innerText = '';
                    nowframe.querySelectorAll('.outer-frame').forEach((items, index)=>{
                        if(items.getAttribute('data-nftid') === item.childNodes[1].getAttribute('data-nftid')) {
                            items.setAttribute('src', '../../img/etc/no-image.png');
                            items.setAttribute('data-nftid', 'null');
                            items.parentNode.querySelector('.outer-frame-delete-btn').remove();
                            nowframe.setAttribute('frameNum',  (nowFrameNum - 1).toString());
                            nowframe.setAttribute('save','false');
                            frameNum--;
                        }
                    })
                }
            }
            else {
                if(frameNum < frameMaxLength) {
                    if(nowFrameNum < 3) {
                        const frameDelete = document.createElement('div');
                        frameDelete.classList.add('outer-frame-delete-btn');
                        frameDelete.innerText = 'X';
                        item.childNodes[3].innerText = currentNum;
                        nowframe.setAttribute('frameNum',  (nowFrameNum + 1).toString());
                        let count = 0;
                        nowframe.querySelectorAll('.outer-frame').forEach((items, index)=>{
                            if(items.getAttribute('src').includes('http')) {

                            }
                            else {
                                if(count === 0) {
                                    items.setAttribute('src', item.childNodes[1].getAttribute('src'));
                                    items.setAttribute('data-nftid', item.childNodes[1].getAttribute('data-nftid'))
                                    count++;
                                    items.parentNode.appendChild(frameDelete);
                                    frameDelete.addEventListener('click',(e)=>{
                                        const nowFrameNum = Number(nowframe.getAttribute('frameNum'));
                                        const searchNumFrame = e.target.parentNode.childNodes[1].getAttribute('data-nftid');
                                        frameImages.forEach((items, index)=>{
                                            if(items.childNodes[1].getAttribute('data-nftid') === searchNumFrame) {
                                                items.childNodes[3].innerText = '';
                                            }
                                        })
                                        e.target.parentNode.childNodes[1].setAttribute('src','../../img/etc/no-image.png');
                                        e.target.parentNode.childNodes[1].setAttribute('data-nftid','null');
                                        nowframe.setAttribute('frameNum',  (nowFrameNum - 1).toString());
                                        nowframe.setAttribute('save','false');
                                        frameNum--;
                                        e.target.remove();
                                    })
                                }
                            }
                        })
                        frameNum++;
                        nowframe.setAttribute('save','false');
                    }
                }

            }
        })
    })

    backgroundImages.forEach((item, index) => {
        item.addEventListener('click', () => {
            const nowBackground = document.querySelectorAll('.gallery-slide-list')[currentNum];
            const nowBackgroundNum = Number(nowBackground.getAttribute('backgroundNum'));
            if(item.childNodes[3].innerText !=='') {
                if(Number(item.childNodes[3].innerText) === currentNum) {
                    item.childNodes[3].innerText = '';
                    nowBackground.querySelector('.back-frame').setAttribute('src', '../../img/etc/no-image.png');
                    nowBackground.querySelector('.back-frame').setAttribute('data-nftid', 'null');
                    nowBackground.querySelector('.background-frame-delete-btn').remove();
                    nowBackground.setAttribute('backgroundNum', (nowBackgroundNum - 1).toString());
                    nowBackground.setAttribute('save','false');
                    backgroundNum--;
                }
            }
            else {
                if(backgroundNum < backgroundMaxLength) {
                    if(nowBackgroundNum < 1) {
                        const backgroundDelete = document.createElement('div');
                        backgroundDelete.classList.add('background-frame-delete-btn');
                        backgroundDelete.innerText = 'X';
                        item.childNodes[3].innerText = currentNum;
                        nowBackground.setAttribute('backgroundNum', (nowBackgroundNum + 1).toString());
                        nowBackground.querySelector('.back-frame').setAttribute('src', item.childNodes[1].getAttribute('src'));
                        nowBackground.querySelector('.back-frame').setAttribute('data-nftid',item.childNodes[1].getAttribute('data-nftid'));
                        nowBackground.appendChild(backgroundDelete);
                        backgroundNum++;
                        nowBackground.setAttribute('save','false');
                        backgroundDelete.addEventListener('click',(e)=>{
                            const nowBackgroundNum = Number(nowBackground.getAttribute('backgroundNum'));
                            const searchNumBackground = e.target.previousSibling.previousSibling.previousSibling.previousSibling.getAttribute('data-nftid');
                            backgroundImages.forEach((items, index)=>{
                                if(items.childNodes[1].getAttribute('data-nftid') === searchNumBackground) {
                                    items.childNodes[3].innerText='';
                                    nowBackground.querySelector('.back-frame').setAttribute('src', '../../img/etc/no-image.png');
                                    nowBackground.querySelector('.back-frame').setAttribute('data-nftid', 'null');
                                    nowBackground.querySelector('.background-frame-delete-btn').remove();
                                    nowBackground.setAttribute('backgroundNum', (nowBackgroundNum - 1).toString());
                                    nowBackground.setAttribute('save','false');
                                    backgroundNum--;
                                }
                            })
                        })
                    }
                }
            }
        })
    })
}

const pageData =  {
    sPage : 0,
    nPage : 0,
    fPage : 0,
    bPage : 0,
    size : 36
}
const constEditContent = {
    getNftData() {
        this.getEditNftList();
        this.getEditNftNotVideoList("frame");
        this.getEditNftNotVideoList("background");

        // window.addEventListener("DOMContentLoaded", contentListLoad);
        contentListLoad();
    },
    ContainerHtml(editType, data) {
        let html = "";
        let edit_list = $('#edit-'+editType+'-list');
        $.each(data.nftList, function(k, nft){
            let imageUrlHtml = "<img class=\"gallery-edit-img edit-"+editType+" nft_"+nft.nftId+"\" alt=\""+nft.name+"\" data-nftid=\""+nft.nftId+"\" src=\""+nft.nftImageUrl+"\"/>";
            if(nft.tagType == 'video') {
                imageUrlHtml = "<video class=\"gallery-edit-img edit-"+editType+" nft_"+nft.nftId+"\" controls controlsList=\"nodownload\" alt=\""+nft.name+"\" data-nftid=\""+nft.nftId+"\" src=\""+nft.nftImageUrl+"\"/>";
            }

            html += "<div class=\"gallery-edit-slide-item edit-slice-item-"+editType+"\">\n" +
                "              "+ imageUrlHtml +
                "               <div class=\"gallery-edit-select-number number-"+editType+"\"></div>\n" +
                "               <div class=\"gallery-edit-img-hover\"></div>\n" +
                "           </div>";
        });
        if(data.total > 36) {
            html += "<div class=\"edit-image-moreBtn "+editType+"-moreBtn\">\n" +
                "               <img src=\"/img/icon/ic-popup-right.png\">\n" +
                "           </div>";
            // console.log(html);
        }

        edit_list.html(html);
    },
    getEditNftList(type) {
        let slideItemTotal = $('#edit-nft-list .gallery-edit-slide-item.edit-slice-item-nft').length;
        let editType = "nft";
        let nftList = commonAjaxUrl("GET", "/api/gallery/page?page="+pageData.sPage+"&size="+pageData.size+"&pageType=edit", {});
        this.ContainerHtml(editType, nftList);
    },
    getEditNftNotVideoList(editType) {
        let slideItemTotal = $('#edit-'+editType+'-list .gallery-edit-slide-item.edit-slice-item-'+editType).length;
        let page = (editType=="frame")? pageData.fPage :  pageData.bPage;
        let nftNotVideoList = commonAjaxUrl("GET", "/api/gallery/page?page="+page+"&size="+pageData.size+"&pageType=editNotVideo", {});

        this.ContainerHtml(editType,nftNotVideoList);
    }
}

$(document).ready(function(){
    constEditContent.getNftData();

})