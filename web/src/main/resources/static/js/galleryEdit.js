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

const editInfo = {
    maxNft : 0,
    maxFrameBg : 0,
    lastNft : 0
}

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
            if(nowList3.getAttribute('save') === 'false' || (nftNum !==0&& frameNum !==0 && backgroundNum !==0)) {
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
                    constEditContent.addSection();
                }
                else if(nowList2.getAttribute('moreValue') !== '0') {

                }
                else {
                    swal(`Currently not saved for section ${currentNum}`,'','error');
                }
            }
        })
    })

    closeBtn.addEventListener('click',async ()=>{
        let nowList1 = document.querySelectorAll('.gallery-slide-list')[currentNum];
        if(choiceOne) {
            if(nowList1.getAttribute('save') === 'false' && (nftNum !==0|| frameNum !==0 || backgroundNum !==0)) {
                // if(!window.confirm('지금 나가면 저장이 되지 않습니다. 정말로 나가시겠습니까?')){
                //     return;
                // }
                await swal({
                    title : 'Are you sure?',
                    text : "You Won't be able to revert this!",
                    icon : 'warning',
                    buttons : {
                        cancel: {
                            text : 'Cancel',
                            value : false,
                            visible : true,
                            className : "",
                            closeModal: true,
                        },
                        confirm : {
                            text : 'Leave',
                            value : true,
                            visible : true,
                            className : "",
                            closeModal: true,
                        }
                    },
                }).then((result) => {
                    if(!result) {
                        return;
                    }
                    else {
                        choiceOne = false;
                        window.location.href = '/gallery/mycollection';
                    }
                })
            }
            else {
                choiceOne = false;
                window.location.href = '/gallery/mycollection';
            }
        }
    })

    saveBtn.forEach((item, index) => {
        item.addEventListener('click',() => {
            console.log(item);
            let slideCount = document.querySelectorAll('.gallery-slide-list').length;
            let nowList = document.querySelectorAll('.gallery-slide-list')[currentNum];
            let nowListNftNum = Number(nowList.getAttribute('nftNum'));
            let sectionSeq = $('.gallery-slide-list.on').data('sectionseq');

            if(nowListNftNum === 3 && nftTotalOK && nowList.getAttribute('save') === "false") {
                const saveData = {
                    sectionSeq : 0,
                    nft : new Array(),
                    frame : new Array(),
                    background : 0,
                    orderSeq : new Array()
                }
                nowList.setAttribute('save', 'true');
                swal(`Section ${currentNum} is now saved`,'','info');

                $.each($('.gallery-slide-list.on'), function(key, nft) {
                    let backgroundNum = $(this).find('.back-frame').data('nftid');
                    let image_container = $(this).find('.image-container');
                    saveData.sectionSeq = sectionSeq;
                    $.each(image_container, function(key2, container){
                        let nftnum = $(this).find('.gallery-edit-img.edit-nft').data('nftid');
                        let framenum = $(this).find('.outer-frame').data('nftid');
                        let orderseq = $(this).data('orderseq');
                        if(nftnum != null || nftnum != undefined) {
                            saveData.nft.push(nftnum);
                            if(orderseq != null || orderseq != undefined) {
                                saveData.orderSeq.push(nftnum+":"+orderseq);
                            }
                        }
                        if(framenum != null || framenum != undefined) {
                            saveData.frame.push(framenum);
                        }
                    });
                    if(backgroundNum != null || backgroundNum != undefined) {
                        saveData.background = backgroundNum;
                    }
                    console.log(saveData);
                })

            }
            else {
                if(!nftTotalOK) {
                    swal('모든 nft를 넣으셔야 저장 할수 있습니다.','','error');
                }
                else if(nowListNftNum !== 3) {
                    swal(`You must fill 3 nfts to be able to save`,'','error');
                }
                else if(nowList.getAttribute('save') === 'true') {
                    swal(`Current section ${sectionSeq} is already saved`,'','error');
                }
            }
        })
    })

    selectList.forEach((item, index)=>{
        item.addEventListener('click', ()=>{
            if(nowSelect !== item.innerText) {
                nowSelect = item.innerText;
                frameMaxLength = nftMaxLength;
            }
        })
    })

    nftImages.forEach((item, index) => {
        let data_nftid = item.childNodes[1].getAttribute('data-nftid');
        item.addEventListener("click",(e)=>{
            const nowNft = document.querySelectorAll('.gallery-slide-list')[currentNum];
            const nowNftNum = Number(nowNft.getAttribute('nftNum'));
            if(item.childNodes[3].innerText !== '') {
                if(Number(item.childNodes[3].innerText) === currentNum) {
                    item.childNodes[3].innerText = '';
                    nowNft.querySelectorAll('.inner-picture').forEach((items, index)=>{
                        if(items.childNodes.length > 0) {
                            let clsGalleryEditImg = items.childNodes[0];
                            if(clsGalleryEditImg.getAttribute('data-nftid') === data_nftid) {
                                clsGalleryEditImg.remove();
                                items.classList.remove('ip'+data_nftid);
                                items.parentNode.querySelector('.inner-picture-delete-btn').remove();
                                nowNft.setAttribute('nftNum', (nowNftNum -1).toString());
                                nowNft.setAttribute('save', 'false');
                                nftNum--;
                            }
                        }
                    })
                }
            }

           else  {
               if(nftNum < nftMaxLength) {
                   if(nowNftNum < 3) {
                       let delBtnId = `ip-del-btn-${data_nftid}`;
                       const nftDelete = document.createElement('div');
                       nftDelete.classList.add('inner-picture-delete-btn');
                       nftDelete.innerText = 'X';
                       nftDelete.setAttribute('data-nftid',data_nftid);
                       nftDelete.setAttribute("id", delBtnId);
                       item.childNodes[3].innerText = currentNum;
                       nowNft.setAttribute('nftNum', (nowNftNum + 1).toString());
                       let count=0;
                       nowNft.querySelectorAll('.inner-picture').forEach((items, index) =>{

                           if(items.childNodes.length == 0) {
                               if(count === 0) {
                                   if(item.childNodes[1].tagName === 'IMG') {
                                       const imageNft = document.createElement('img');
                                       imageNft.classList.add('gallery-edit-img', 'edit-nft');
                                       imageNft.setAttribute('data-nftid',data_nftid);
                                       imageNft.setAttribute('src',item.childNodes[1].getAttribute('src'));
                                       items.appendChild(imageNft);
                                       count++;
                                   }
                                   else if(item.childNodes[1].tagName === 'VIDEO') {
                                       const videoNft = document.createElement('video');
                                       videoNft.classList.add('gallery-edit-img', 'edit-nft');
                                       videoNft.setAttribute('data-nftid',data_nftid);
                                       videoNft.setAttribute('src',item.childNodes[1].getAttribute('src'));
                                       videoNft.setAttribute('controls', true);
                                       items.appendChild(videoNft);
                                       count++;
                                   }
                                   items.parentNode.appendChild(nftDelete);
                                   items.classList.add('ip'+data_nftid);
                                   constEditContent.deleteBtnEvent(delBtnId);
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
                            items.classList.remove('of'+item.childNodes[1].getAttribute('data-nftid'));
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
                        let delBtnId = `of-del-btn-${item.childNodes[1].getAttribute('data-nftid')}`;
                        const frameDelete = document.createElement('div');
                        frameDelete.classList.add('outer-frame-delete-btn');
                        frameDelete.innerText = 'X';
                        frameDelete.setAttribute('data-nftid',item.childNodes[1].getAttribute('data-nftid'));
                        frameDelete.setAttribute("id", delBtnId);
                        item.childNodes[3].innerText = currentNum;
                        nowframe.setAttribute('frameNum',  (nowFrameNum + 1).toString());
                        let count = 0;
                        nowframe.querySelectorAll('.outer-frame').forEach((items, index)=>{
                            if(!items.getAttribute('src').includes('http')) {
                                if(count === 0) {
                                    items.setAttribute('src', item.childNodes[1].getAttribute('src'));
                                    items.setAttribute('data-nftid', item.childNodes[1].getAttribute('data-nftid'))
                                    count++;
                                    items.parentNode.appendChild(frameDelete);
                                    items.classList.add('of'+item.childNodes[1].getAttribute('data-nftid'));
                                    constEditContent.deleteBtnEvent(delBtnId);
                                    // constEditContent.deleteBtnEvent();
                                    /*frameDelete.addEventListener('click',(e)=>{
                                        const nowFrameNum = Number(nowframe.getAttribute('frameNum'));
                                        const searchNumFrame = e.target.parentNode.childNodes[1].getAttribute('data-nftid');
                                        frameImages.forEach((items, index)=>{
                                            if(items.childNodes[1].getAttribute('data-nftid') === searchNumFrame) {
                                                items.childNodes[3].innerText = '';
                                            }
                                        })
                                        items.classList.remove('of'+item.childNodes[1].getAttribute('data-nftid'));
                                        e.target.parentNode.childNodes[1].setAttribute('src','../../img/etc/no-image.png');
                                        e.target.parentNode.childNodes[1].setAttribute('data-nftid','null');
                                        nowframe.setAttribute('frameNum',  (nowFrameNum - 1).toString());
                                        nowframe.setAttribute('save','false');
                                        frameNum--;
                                        e.target.remove();
                                    })*/
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
                    nowBackground.querySelector('.back-frame').classList.remove('bf'+item.childNodes[1].getAttribute('data-nftid'));
                    backgroundNum--;
                }
            }
            else {
                if(backgroundNum < backgroundMaxLength) {
                    if(nowBackgroundNum < 1) {
                        let delBtnId = `bf-del-btn-${item.childNodes[1].getAttribute('data-nftid')}`;
                        const backgroundDelete = document.createElement('div');
                        backgroundDelete.classList.add('background-frame-delete-btn');
                        backgroundDelete.innerText = 'X';
                        backgroundDelete.setAttribute('data-nftid',item.childNodes[1].getAttribute('data-nftid'));
                        backgroundDelete.setAttribute("id", delBtnId);
                        item.childNodes[3].innerText = currentNum;
                        nowBackground.setAttribute('backgroundNum', (nowBackgroundNum + 1).toString());
                        nowBackground.querySelector('.back-frame').setAttribute('src', item.childNodes[1].getAttribute('src'));
                        nowBackground.querySelector('.back-frame').setAttribute('data-nftid',item.childNodes[1].getAttribute('data-nftid'));
                        nowBackground.appendChild(backgroundDelete);
                        backgroundNum++;
                        nowBackground.setAttribute('save','false');
                        nowBackground.querySelector('.back-frame').classList.add('bf'+item.childNodes[1].getAttribute('data-nftid'));
                        constEditContent.deleteBtnEvent(delBtnId);
                        /*backgroundDelete.addEventListener('click',(e)=>{
                            const nowBackgroundNum = Number(nowBackground.getAttribute('backgroundNum'));
                            const searchNumBackground = e.target.previousSibling.previousSibling.previousSibling.previousSibling.getAttribute('data-nftid');
                            nowBackground.querySelector('.back-frame').classList.remove('bf'+item.childNodes[1].getAttribute('data-nftid'));
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
                        })*/
                    }
                }
            }
        })
    })
}

const pageData =  {
    sPage : 0,  // sliderList
    nPage : 0,  // nft
    fPage : 0,  // frame
    bPage : 0,  // background
    size : 36
}
const constEditContent = {
    getBeforeNftData() {
        let nftList = this.getEditNftList();
        let nftNotVideothis = this.getEditNftNotVideoList();

        this.containerHtml("nft", nftList);
        this.containerHtml("frame", nftNotVideothis);
        this.containerHtml("background", nftNotVideothis);

        editInfo.maxNft = nftList.total;
        editInfo.lastNft = nftList.total%3;
        editInfo.maxFrameBg = nftNotVideothis.total;

        // window.addEventListener("DOMContentLoaded", contentListLoad);
        contentListLoad();
    },
    containerHtml(editType, data) {
        let html = "";
        let edit_list = $('#edit-'+editType+'-list');
        $.each(data.nftList, function(k, nft){
            let imageUrlHtml = "<img class=\"gallery-edit-img edit-"+editType+" nft_"+nft.nftId+"\" alt=\""+nft.name+"\" data-nftid=\""+nft.nftId+"\" src=\""+nft.nftImageUrl+"\"/>";
            if(nft.tagType == 'video') {
                imageUrlHtml = "<video class=\"gallery-edit-img edit-"+editType+" nft_"+nft.nftId+"\" controls controlsList=\"nodownload\" alt=\""+nft.name+"\" data-nftid=\""+nft.nftId+"\" src=\""+nft.nftImageUrl+"\"></video>";
            }

            html += "<div class=\"gallery-edit-slide-item edit-slice-item-"+editType+" nft_item_"+nft.nftId+"\"\">\n" +
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
    // slider, nft 리스트 조회
    getEditNftList(page) {
        let parentContainer = $('#gallery-slide-container');
        let itemTotal = parentContainer.find('.image-container').length;
        let nextPage = parseInt(itemTotal / pageData.size);
        if(page == "nft") {
            itemTotal = $('#edit-nft-list .gallery-edit-slide-item.edit-slice-item-nft').length;
            nextPage = parseInt(itemTotal / pageData.size);
            pageData.nPage = nextPage;
        }

        return commonAjaxUrl("GET", "/api/gallery/page?page="+nextPage+"&size="+pageData.size+"&pageType=edit", {});
    },
    // frame, background 리스트 조회
    getEditNftNotVideoList(editType) {
        let nextPage = 0;
        let itemTotal = 0;
        if(editType) {
            itemTotal = $('#edit-'+editType+'-list .gallery-edit-slide-item.edit-slice-item-'+editType).length;
            nextPage = parseInt(itemTotal / pageData.size);
            if(editType == "frame") {
                pageData.fPage = nextPage;
            } else {
                pageData.bPage = nextPage;
            }
        }

        return commonAjaxUrl("GET", "/api/gallery/page?page="+nextPage+"&size="+pageData.size+"&pageType=editNotVideo", {});
    },

    MoreEdit(type) {
        const parentNode = $('.gallery-slide-list-container');
        let nftList = this.getEditNftList();
        let editContent = ``;
        let ipDelBtnArr = new Array();
        $.each(nftList.nftSliderList, function(key, section){
            let tIdx = ((key*3)+1);
            let nftNum = parseInt(0);
            let frameNum = parseInt(0);
            let backgroundNum = parseInt(0);
            let moreValue = parseInt(0);
            let select_number = key;
            let editSubContent = ``;
            if(section.length) {
                $.each(section, function(key2, slider){
                    nftNum++; // += parseInt(1);
                    $('#edit-nft-list').find('.nft_item_'+slider.nftId+' .gallery-edit-select-number').text(select_number);
                    let date = timeToElapsed(slider.localDate);
                    let imageHtmlContainer = `<img class="gallery-edit-img edit-nft" alt="${slider.name}" data-nftid="${slider.nftId}" src="${slider.nftImageUrl}" />`;
                    if(slider.tagType == "video") {
                        imageHtmlContainer = `<video class="gallery-edit-img edit-nft" controls controlsList="nodownload" alt="${slider.name}" data-nftid="${slider.nftId}" src="${slider.nftImageUrl}"/>`;
                    }
                    let ipDelBtnId = `ip-del-btn-${slider.nftId}`;
                    ipDelBtnArr.push("#"+ipDelBtnId);
                    let ipDeleteBtn = `<div class="inner-picture-delete-btn" id="${ipDelBtnId}" data-nftid="${slider.nftId}">X</div>`;
                    editSubContent += `
                            <div class="image-container" data-orderseq="${tIdx+key2}">
                                <div class="image-container-content">
                                    <img class="outer-frame" src="../img/etc/no-image.png"/>
                                    <div class="inner-picture ip${slider.nftId}" style="border : 1px solid lightgray;">${imageHtmlContainer}</div>
                                    `+ipDeleteBtn+`
                                </div>
                                <div class="picture-explain" style="opacity: 0">
                                    <p class="picture-title">${slider.name}</p>
                                    <div class="picture-explain-bottom">
                                        <div class="user-info">
                                            <img src="/img/icon/ic-gallery-profile.svg" />
                                            <div class="nft-title-user">
                                                <div class="user-info-name">
                                                    <span>${slider.username}</span>
                                                    <span class="time">${date}</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="picture-price">
                                            <img src="/img/icon/ic-gallery-eyes.svg" />
                                            <span>${slider.viewCount}</span>
                                            <img src="/img/icon/ic-gallery-like.svg" />
                                            <span>${slider.likeCount}</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                    `;

                });
                if(nftList.nftSliderList.length - 1 !== key ) {
                    moreValue++;
                }
            }
            editContent += `
                    <div class="gallery-slide-list" save="true" moreValue="${moreValue}" nftNum="${nftNum}" frameNum="0" backgroundNum="0" data-sectionseq="${key+1}">
                        <img src="../img/etc/no-image.png" class="back-frame" alt="background" />
                        <div class="gallery-slide-list-item">
                            <div class="gallery-slide-list-item-picture">
                                <div class="gallery-slide-list-item-down"></div>
                                ${editSubContent}
                            </div>
                        </div>
                    </div>
            `;
        });
        if(type == "html") {
            parentNode.html(editContent);
        } else {
            parentNode.append(editContent);
        }
        Refresh();
        nftMaxLength = 3 * (slides.length);
        backgroundMaxLength += 1;
        nftOk = false;
        // delete 버튼 이벤트
        constEditContent.deleteBtnEvent("", ipDelBtnArr);

    },

    addSection() {
        const parentNode = $('.gallery-slide-list-container');
        let count = $('.gallery-slide-list .image-container').length
        let maxSectionSeq = $('.gallery-slide-list').length;
        if(editInfo.maxNft > count) {
            let editContent = `<div class="gallery-slide-list" save="false" moreValue="0" nftNum="0" frameNum="0" backgroundNum="0" data-sectionseq="${maxSectionSeq+1}">
                        <img src="../img/etc/no-image.png" class="back-frame" alt="background" />
                        <div class="gallery-slide-list-item">
                            <div class="gallery-slide-list-item-picture">
                                <div class="gallery-slide-list-item-down"></div>
                                <div class="image-container" data-orderseq="${count+1}">
                                    <div class="image-container-content">
                                        <img class="outer-frame" src="../img/etc/no-image.png"/>
                                        <div class="inner-picture" style="border : 1px solid lightgray;"></div>
                                    </div>
                                    <div class="picture-explain" style="opacity: 0">
                                        <p class="picture-title">NFT TITLE #0000</p>
                                        <div class="picture-explain-bottom">
                                            <div class="user-info">
                                                <img src="/img/icon/ic-gallery-profile.svg" />
                                                <div class="nft-title-user">
                                                    <div class="user-info-name">
                                                        <span></span>
                                                        <span class="time"></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="picture-price">
                                                <img src="/img/icon/ic-gallery-eyes.svg" />
                                                <span>00</span>
                                                <img src="/img/icon/ic-gallery-like.svg" />
                                                <span>00</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="image-container" data-orderseq="${count+2}">
                                    <div class="image-container-content">
                                        <img class="outer-frame" src="../img/etc/no-image.png"/>
                                        <div class="inner-picture" style="border : 1px solid lightgray;"></div>
                                    </div>
                                    <div class="picture-explain" style="opacity: 0">
                                        <p class="picture-title">NFT TITLE #0000</p>
                                        <div class="picture-explain-bottom">
                                            <div class="user-info">
                                                <img src="/img/icon/ic-gallery-profile.svg" />
                                                <div class="nft-title-user">
                                                    <div class="user-info-name">
                                                        <span></span>
                                                        <span class="time"></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="picture-price">
                                                <img src="/img/icon/ic-gallery-eyes.svg" />
                                                <span>00</span>
                                                <img src="/img/icon/ic-gallery-like.svg" />
                                                <span>00</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="image-container" data-orderseq="${count+3}">
                                    <div class="image-container-content">
                                        <img class="outer-frame" src="../img/etc/no-image.png"/>
                                        <div class="inner-picture" style="border : 1px solid lightgray;"></div>
                                    </div>
                                    <div class="picture-explain" style="opacity: 0">
                                        <p class="picture-title">NFT TITLE #0000</p>
                                        <div class="picture-explain-bottom">
                                            <div class="user-info">
                                                <img src="/img/icon/ic-gallery-profile.svg" />
                                                <div class="nft-title-user">
                                                    <div class="user-info-name">
                                                        <span></span>
                                                        <span class="time"></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="picture-price">
                                                <img src="/img/icon/ic-gallery-eyes.svg" />
                                                <span>00</span>
                                                <img src="/img/icon/ic-gallery-like.svg" />
                                                <span>00</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>`;

            parentNode.append(editContent);
            Refresh();
            nftMaxLength = 3 * (slides.length);
            backgroundMaxLength += 1;
            nftOk = false;
        }
    },
    deleteBtnEvent(btnId, btnIdArr) {
        // $('div[class$="delete-btn"]').on('click', function() {
        let delBtnNode = "";
        if(btnId == "") {
            let strBtnId = btnIdArr.join(",");
            delBtnNode = $(strBtnId);
        } else {
            delBtnNode = $('#'+btnId);
        }
        this.deleteClickEvent(delBtnNode);
    },
    deleteClickEvent(delBtnNode) {
        delBtnNode.on('click', function() {
            let nftId = $(this).data('nftid');
            let className = $(this).attr('class');
            let parentSlideList = $(this).parents('.gallery-slide-list');
            let numName = "";
            let containerPrevCls = "";
            let currContainer = "";
            let editSelectItem = "";
            let noImage = "../../img/etc/no-image.png";
            switch (className) {
                case "inner-picture-delete-btn" :
                    numName = "nftnum";
                    containerPrevCls = "ip";
                    editSelectItem = $('#edit-nft-list');
                    currContainer = $('.ip'+nftId);
                    currContainer.html('');
                    break;
                case "outer-frame-delete-btn" :
                    numName = "framenum";
                    containerPrevCls = "of";
                    editSelectItem = $('#edit-frame-list');
                    currContainer = $('.of'+nftId);
                    currContainer.attr('src', noImage);
                    currContainer.data('nftid','null');
                    break;
                case "background-frame-delete-btn" :
                    numName = "backgroundnum";
                    containerPrevCls = "bf";
                    editSelectItem = $('#edit-background-list');
                    currContainer = $('.bf'+nftId);
                    currContainer.attr('src', noImage);
                    currContainer.data('nftid','null');
                    saveData.background = 0;
                    break;
            }
            let number = parseInt(parentSlideList.attr(numName)) - 1;
            parentSlideList.attr(numName , number)
            parentSlideList.attr('save','false');
            editSelectItem.find('.nft_item_'+nftId+' .gallery-edit-select-number').text('');
            currContainer.removeClass(containerPrevCls + nftId);
            $(this).remove();
        });
    }
}

$(document).ready(function(){
    constEditContent.getBeforeNftData();
    // constEditContent.addSection();
    constEditContent.MoreEdit("html");

    dontclick(nftImages);
    dontclick(frameImages);
    dontclick(backgroundImages);
});