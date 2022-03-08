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
let nftNum;
let frameNum;
let backgroundNum;
let choiceOne;

const editInfo = {
    maxNft : 0,
    maxFrameBg : 0,
    lastNft : 0
}

const beforeUnloadedEvent = (e) => {
    let nowList3 = document.querySelector('.gallery-slide-list.on');
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
}

const plusBtnEvent = () => {
    if(nowLocation === '/gallery/edit') {
        let nowList2 = document.querySelector('.gallery-slide-list.on');
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
}

const closeBtnEvent = async () => {
    let nowList1 = document.querySelector('.gallery-slide-list.on');
    if(choiceOne) {
        if(nowList1.getAttribute('save') === 'false') {
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
}

const saveBtnEvent = (e) => {
    let slideCount = document.querySelectorAll('.gallery-slide-list').length;
    let nowList = document.querySelector('.gallery-slide-list.on');
    let nowListNftNum = Number(nowList.getAttribute('nftNum')); // editInfo.maxNft
    let sectionSeq = $('.gallery-slide-list.on').data('sectionseq');
    let nowListImageContainer = $('.gallery-slide-list.on').find('.image-container').length;
    console.log('hihi');

    if(nowListNftNum === nowListImageContainer && nftTotalOK && nowList.getAttribute('save') === "false") {
        const saveData = {
            sectionSeq : 0,
            nft : new Array(),
            frame : new Array(),
            background : 0,
            orderSeq : new Array()
        }

        $.each($('.gallery-slide-list.on'), function(key, nft) {
            let backgroundNum = $(this).find('.back-frame').attr('data-nftid');
            let image_container = $(this).find('.image-container');
            saveData.sectionSeq = sectionSeq;
            $.each(image_container, function(key2, container){
                let nftnum = $(this).find('.gallery-edit-img.edit-nft').attr('data-nftid');
                let fnum = $(this).find('.outer-frame').attr('data-nftid');
                let orderseq = $(this).data('orderseq');
                if(nftnum != null || nftnum != undefined) {
                    saveData.nft.push(nftnum);
                    if(orderseq != null || orderseq != undefined) {
                        saveData.orderSeq.push(orderseq+":"+nftnum);
                    }
                }
                saveData.frame.push(fnum);
            });
            if(backgroundNum != null || backgroundNum != undefined) {
                saveData.background = backgroundNum;

            }
        });

        let url = "/api/member/edit";
        let result = commonAjaxUrl("POST", url, saveData);

        if(result == true) {
            nowList.setAttribute('save', 'true');
            swal(`Section ${sectionSeq} is now saved`,'','info');
        }
    }
    else {
        if(!nftTotalOK) {
            swal('모든 nft를 넣으셔야 저장 할수 있습니다.','','error');
        }
        else if(nowListNftNum !== nowListImageContainer) {
            swal(`You must fill 3 nfts to be able to save`,'','error');
        }
        else if(nowList.getAttribute('save') === 'true') {
            swal(`Current section ${sectionSeq} is already saved`,'','error');
        }
    }
    e.stopPropagation();
}

const categoryEvent = function (item) {
    if(nowSelect !== item.innerText) {
        console.log(item.innerText);
        nowSelect = item.innerText;
        frameMaxLength = nftMaxLength;
    }
}

const nftImagesEvent = (item) => {
    if(item.classList.contains('edit-slice-item-nft')) {
        let data_nftid = item.childNodes[1].getAttribute('data-nftid');
        const nowNft = document.querySelector('.gallery-slide-list.on');
        const nowNftNum = Number(nowNft.getAttribute('nftNum'));
        if(item.childNodes[3].innerText !== '') {
            if(Number(item.childNodes[3].innerText) === currentNum) {
                item.childNodes[3].innerText = '';
                nowNft.querySelectorAll('.inner-picture').forEach((items, index)=>{
                    if(items.childNodes.length > 0) {
                        let clsGalleryEditImg = items.lastChild;
                        if(clsGalleryEditImg.getAttribute('data-nftid') === data_nftid) {
                            items.innerHTML = '';
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
                    const videoBtn  = document.createElement('div');
                    videoBtn.classList.add('playVideoBtn', 'new', 'editBtn');
                    nftDelete.classList.add('inner-picture-delete-btn');
                    nftDelete.innerText = 'X';
                    videoBtn.setAttribute('nowplay','false');
                    nftDelete.setAttribute('data-nftid',data_nftid);
                    nftDelete.setAttribute("id", delBtnId);
                    item.childNodes[3].innerText = currentNum;
                    nowNft.setAttribute('nftNum', (nowNftNum + 1).toString());
                    videoBtn.addEventListener('click',()=>{
                        videoPlayEvent(videoBtn);
                    })
                    let count=0;
                    nowNft.querySelectorAll('.inner-picture').forEach((items, index) =>{

                        if(items.childNodes.length == 0) {
                            if(count === 0) {
                                console.log(item.childNodes[1].getAttribute('tagType'))
                                if(item.childNodes[1].getAttribute('tagType') === 'image') {
                                    const imageNft = document.createElement('img');
                                    imageNft.classList.add('gallery-edit-img', 'edit-nft');
                                    imageNft.setAttribute('data-nftid',data_nftid);
                                    imageNft.setAttribute('src',item.childNodes[1].getAttribute('src'));
                                    items.appendChild(imageNft);
                                    count++;
                                }
                                else if(item.childNodes[1].getAttribute('tagType') === 'video') {
                                    const videoNft = document.createElement('video');
                                    videoNft.classList.add('gallery-edit-img', 'edit-nft');
                                    videoNft.setAttribute('data-nftid',data_nftid);
                                    videoNft.setAttribute('src',item.childNodes[1].getAttribute('src'));
                                    videoNft.setAttribute('playsinline', true);
                                    items.appendChild(videoBtn);
                                    items.appendChild(videoNft);
                                    count++;
                                }
                                else if(item.childNodes[1].getAttribute('tagType') === 'imagemp4') {
                                    const imagemp4Nft = document.createElement('video');
                                    imagemp4Nft.classList.add('gallery-edit-img', 'edit-nft');
                                    imagemp4Nft.setAttribute('data-nftid', data_nftid);
                                    imagemp4Nft.setAttribute('src', item.childNodes[1].getAttribute('data-video'));
                                    imagemp4Nft.setAttribute('playsinline', true);
                                    imagemp4Nft.setAttribute('poster', item.childNodes[1].getAttribute('src'));
                                    items.appendChild(videoBtn);
                                    items.appendChild(imagemp4Nft);
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
    }
    else if(item.classList.contains('edit-slice-item-frame')) {
        const nowframe = document.querySelectorAll('.gallery-slide-list')[currentNum];
        const nowFrameNum = Number(nowframe.getAttribute('frameNum'));
        if(item.childNodes[3].innerText !== '') {
            if(Number(item.childNodes[3].innerText) === currentNum) {
                item.childNodes[3].innerText = '';
                nowframe.querySelectorAll('.outer-frame').forEach((items, index)=>{
                    if(items.getAttribute('data-nftid') === item.childNodes[1].getAttribute('data-nftid')) {
                        items.setAttribute('src', '../../img/etc/no-image.png');
                        items.setAttribute('data-nftid', '0');
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
                            }
                        }
                    })
                    frameNum++;
                    nowframe.setAttribute('save','false');
                }
            }

        }
    }
    else if(item.classList.contains('edit-slice-item-background')) {
        const nowBackground = document.querySelectorAll('.gallery-slide-list')[currentNum];
        const nowBackgroundNum = Number(nowBackground.getAttribute('backgroundNum'));
        if(item.childNodes[3].innerText !=='') {
            if(Number(item.childNodes[3].innerText) === currentNum) {
                item.childNodes[3].innerText = '';
                nowBackground.querySelector('.back-frame').setAttribute('src', '../../img/etc/no-image.png');
                nowBackground.querySelector('.back-frame').setAttribute('data-nftid', '0');
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
                }
            }
        }
    }
}

const dontclick = (list) => {
    list.forEach((item, index)=>{
        if(Number(item.getElementsByClassName('gallery-edit-select-number')[0].innerText) === currentNum || item.childNodes[3].innerText === '') {
            item.classList.remove('dontClick');
        }
        else {
            item.classList.add('dontClick');
        }
    })
}

const contentListLoad = () => {
    nftNum = 0;
    frameNum = 0;
    backgroundNum = 0;
    let nftArray = [];
    let frameArray = [];
    let backgroundArray = [];
    choiceOne = true;
    const totalNft = document.querySelectorAll('.edit-nft').length;
    const totalFrame = document.querySelectorAll('.edit-frame').length;
    const totalBackground = document.querySelectorAll('.edit-background').length;
    const selectList = document.querySelectorAll('.gallery-edit-select');
    const saveBtn = document.querySelectorAll('.gallery-edit-save');
    const plusBtn = document.querySelectorAll('.gallery-edit-plus-section');

    nftImages = document.querySelectorAll(".gallery-edit-slide-item.edit-slice-item-nft");
    frameImages = document.querySelectorAll(".gallery-edit-slide-item.edit-slice-item-frame");
    backgroundImages = document.querySelectorAll('.gallery-edit-slide-item.edit-slice-item-background');
    let commonImages = document.querySelectorAll('.gallery-edit-slide-container > .gallery-edit-slide-item');
    const closeBtn = document.querySelector('#closeEditBtn');

    window.addEventListener('beforeunload', beforeUnloadedEvent);

    plusBtn.forEach((item, index)=>{
        item.addEventListener('click',plusBtnEvent);
    })

    closeBtn.addEventListener('click',closeBtnEvent);

    saveBtn.forEach((item, index) => {
        item.addEventListener('click',saveBtnEvent);
    })

    selectList.forEach((item, index)=>{
        item.addEventListener('click', ()=>{
            categoryEvent(item);
        } );
    })

    commonImages.forEach((item, index) =>  {
        item.addEventListener("click",()=>{
            nftImagesEvent(item);
        });
    })
}

const pageData =  {
    sPage : 0,  // sliderList
    nPage : 0,  // nft
    fPage : 0,  // frame
    bPage : 0,  // background
    size : 18
}

const constEditContent = {
    getBeforeNftData() {
        let nftList = this.getEditNftList("","edit");
        let nftNotVideothis = this.getEditNftNotVideoList();

        this.containerHtml("nft", nftList, "html");
        this.containerHtml("frame", nftNotVideothis, "html");
        this.containerHtml("background", nftNotVideothis, "html");

        editInfo.maxNft = nftList.total;
        editInfo.lastNft = nftList.total%3;
        editInfo.maxFrameBg = nftNotVideothis.total;

        // window.addEventListener("DOMContentLoaded", contentListLoad);
        // contentListLoad();
    },
    containerHtml(editType, data, htmlType) {
        let html = "";
        let edit_list = $('#edit-'+editType+'-list');
        let slideItemCnt = edit_list.find('.gallery-edit-slide-item').length
        let leftCnt = data.total - slideItemCnt;
        if(data.nftList.length > 0) {
            $.each(data.nftList, function(k, nft){
                let imageUrlHtml = "<img class=\"gallery-edit-img edit-"+editType+" nft_"+nft.nftId+"\" tagtype="+nft.tagType+" alt=\""+nft.name+"\" data-nftid=\""+nft.nftId+"\" src=\""+nft.nftImageUrl+"\"/>";
                if(nft.tagType == 'video') {
                    imageUrlHtml = "<video class=\"gallery-edit-img edit-"+editType+" nft_"+nft.nftId+"\" tagtype="+nft.tagType+" playsinline controlsList=\"nodownload\" alt=\""+nft.name+"\" data-nftid=\""+nft.nftId+"\" src=\""+nft.nftImageUrl+"\"></video>";
                }
                else if(nft.tagType === 'imagemp4') {
                    imageUrlHtml = "<img class=\"gallery-edit-img edit-"+editType+" nft_"+nft.nftId+"\" playsinline tagtype="+nft.tagType+" data-video=\""+nft.nftVideoUrl+"\" controlsList=\"nodownload\" alt=\""+nft.name+"\" data-nftid=\""+nft.nftId+"\" src=\""+nft.nftImageUrl+"\"></img>";
                }

                html += "<div class=\"news "+"gallery-edit-slide-item edit-slice-item-"+editType+" nft_item_"+nft.nftId+"\"\">\n" +
                    "              "+ imageUrlHtml +
                    "               <div class=\"gallery-edit-select-number number-"+editType+"\"></div>\n" +
                    "               <div class=\"gallery-edit-img-hover\"></div>\n" +
                    "           </div>";
                leftCnt--;
            });
            if(data.total > 36 && leftCnt > 0) {
                html += "<div class=\"edit-image-moreBtn "+editType+"-moreBtn\" id=\""+editType+"-moreBtn\" edit_type=\""+editType+"\">\n" +
                    "               <img src=\"/img/icon/ic-popup-right.png\">\n" +
                    "           </div>";
            }
        }
        if(htmlType == "html") {
            edit_list.html(html);
        } else {
            edit_list.append(html);
        }
        let moreBtnId = editType+"-moreBtn";
        this.moreBtnClickEvent(moreBtnId);
    },
    // slider, nft 리스트 조회
    getEditNftList(page, pageType) {
        let itemTotal = 0;
        let nextPage = 0;
        if(page == "more" && pageType == "edit") {
            itemTotal = $('#edit-nft-list .edit-slice-item-nft').length;
            nextPage = parseInt(itemTotal / pageData.size);

            pageData.nPage = nextPage;
        }

        return commonAjaxUrl("GET", "/api/gallery/page?page="+nextPage+"&size="+pageData.size+"&pageType="+pageType, {});
    },
    // frame, background 리스트 조회
    getEditNftNotVideoList(editType) {
        let nextPage = 0;
        let itemTotal = 0;
        if(editType) {
            // itemTotal = $('#edit-'+editType+'-list .gallery-edit-slide-item.edit-slice-item-'+editType).length;
            itemTotal = $('#edit-'+editType+'-list .edit-slice-item-'+editType).length;
            nextPage = parseInt(itemTotal / pageData.size);
            if(editType == "frame") {
                pageData.fPage = nextPage;
            } else {
                pageData.bPage = nextPage;
            }
        }

        return commonAjaxUrl("GET", "/api/gallery/page?page="+nextPage+"&size="+pageData.size+"&pageType=editNotVideo", {});
    },

    MoreEditSlider(type) {
        const parentNode = $('.gallery-slide-list-container');
        let nftList = this.getEditNftList("","editSlider");
        let editContent = ``;
        let ipDelBtnArr = new Array();
        let ofDelBtnArr = new Array();
        let bfDelBtnArr = new Array();
        if(nftList.total > 0) {
            if(nftList.member.mid > 0) {
                memberBackgroundList(nftList.member.mid);
            }
            $.each(nftList.nftSliderList, function(key, section){
                let tIdx = ((key*3)+1);
                let nftNum = parseInt(0);
                let frameNum = parseInt(0);
                let backgroundNum = parseInt(0);
                let moreValue = parseInt(0);
                let select_number = key;
                let section_seq = key+1;
                let editSubContent = ``;
                if(section.length) {
                    $.each(section, function(key2, slider){
                        nftNum++; // += parseInt(1);
                        let nftId = slider.nftId;
                        let date = timeToElapsed(slider.localDate);
                        let imageHtmlContainer = `<img class="gallery-edit-img edit-nft" alt="${slider.name}" data-nftid="${nftId}" src="${slider.nftImageUrl}" />`;
                        if(slider.tagType == "imagemp4") {
                            imageHtmlContainer = `<div class="playVideoBtn new editBtn" nowplay=${false}></div>` +
                                `<video class="gallery-edit-img edit-nft" muted poster=${slider.nftImageUrl} playsinline controlsList="nodownload" alt="${slider.name}" data-nftid="${nftId}" src="${slider.nftVideoUrl}"/>`;
                        }
                        else if(slider.tagType === 'video') {
                            imageHtmlContainer = `<div class="playVideoBtn new editBtn" nowplay=${false}></div>` +
                                `<video class="gallery-edit-img edit-nft" muted playsinline controlsList="nodownload" alt="${slider.name}" data-nftid="${nftId}" src="${slider.nftImageUrl}"/>`;
                        }
                        // Nft List .gallery-edit-select-number.number-nft
                        $('#edit-nft-list').find('.nft_item_'+nftId+' .gallery-edit-select-number').text(select_number);

                        // nft .inner-picture
                        let ipDelBtnId = `ip-del-btn-${nftId}`;
                        ipDelBtnArr.push("#"+ipDelBtnId);
                        let ipDelBtn = `<div class="inner-picture-delete-btn" id="${ipDelBtnId}" data-nftid="${nftId}">X</div>`;

                        // frame .outer-frame
                        let ofDelBtn = ``;
                        let frameNftHtml = `<img class="outer-frame" src="../img/etc/no-image.png" data-nftid="0"/>`;
                        if(slider.frameNftId > 0) {
                            let frameNftId = slider.frameNftId;
                            let frameNft = getNftOne(frameNftId);
                            let ofDelBtnId = `of-del-btn-${frameNftId}`;
                            ofDelBtn = `<div class="outer-frame-delete-btn" data-nftid="${frameNftId}" id="${ofDelBtnId}">X</div>`;
                            frameNftHtml = `<img class="outer-frame of${frameNftId}" src="${frameNft.nftImageUrl}" data-nftid="${frameNftId}" />`;
                            frameNum++;

                            // Frame List .gallery-edit-select-number.number-frame
                            $('#edit-frame-list').find('.nft_item_'+frameNftId+' .gallery-edit-select-number').text(select_number);
                            ofDelBtnArr.push("#"+ofDelBtnId);
                        }
                        editSubContent += `
                            <div class="image-container" data-orderseq="${tIdx+key2}">
                                <div class="image-container-content">
                                    `+frameNftHtml+`
                                    <div class="inner-picture ip${nftId}" style="border : 1px solid lightgray;">${imageHtmlContainer}</div>
                                    `+ipDelBtn+`
                                    `+ofDelBtn+`
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
                // Background List
                let bfDelBtn = ``;
                let bgNftHtml = `<img src="../img/etc/no-image.png" class="back-frame" alt="background" data-nftid="0" />`;
                if(bgList[section_seq] != null) {
                    let bgNft = bgList[section_seq];
                    let bgNftId = bgNft.nftId;
                    let bfDelBtnId = `bf-del-btn-${bgNftId}`;
                    bfDelBtn = `<div class="background-frame-delete-btn" data-nftid="${bgNftId}" id="${bfDelBtnId}">X</div>`;
                    bgNftHtml = `<img class="back-frame bf${bgNftId}" src="${bgNft.nftImageUrl}" data-nftid="${bgNftId}" />`;
                    backgroundNum++;

                    // Background List .gallery-edit-select-number.number-background
                    $('#edit-background-list').find('.nft_item_'+bgNftId+' .gallery-edit-select-number').text(select_number);
                    bfDelBtnArr.push("#"+bfDelBtnId);
                }

                editContent += `
                    <div class="gallery-slide-list" save="true" moreValue="${moreValue}" nftNum="${nftNum}" frameNum="${frameNum}" backgroundNum="${backgroundNum}" data-sectionseq="${section_seq}">
                        `+bgNftHtml+`
                        <div class="gallery-slide-list-item">
                            <div class="gallery-slide-list-item-picture">
                                <div class="gallery-slide-list-item-down"></div>
                                ${editSubContent}
                            </div>
                        </div>
                        `+bfDelBtn+`
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
            if(ofDelBtnArr.length > 0) {
                constEditContent.deleteBtnEvent("", ofDelBtnArr);
            }
            if(bfDelBtnArr.length > 0) {
                constEditContent.deleteBtnEvent("", bfDelBtnArr);
            }
        } else {
            this.addSection();
        }
        contentListLoad();
    },
    addSection() {
        const parentNode = $('.gallery-slide-list-container');
        let count = $('.gallery-slide-list .image-container').length
        let maxSectionSeq = $('.gallery-slide-list').length;
        if(editInfo.maxNft > count) {
            let editContent = `<div class="gallery-slide-list" save="false" moreValue="0" nftNum="0" frameNum="0" backgroundNum="0" data-sectionseq="${maxSectionSeq+1}">
                        <img src="../img/etc/no-image.png" class="back-frame" alt="background" data-nftid="0" />
                        <div class="gallery-slide-list-item">
                            <div class="gallery-slide-list-item-picture">
                                <div class="gallery-slide-list-item-down"></div>
                                <div class="image-container" data-orderseq="${count+1}">
                                    <div class="image-container-content">
                                        <img class="outer-frame" src="../img/etc/no-image.png" data-nftid="0"/>
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
                                        <img class="outer-frame" src="../img/etc/no-image.png" data-nftid="0"/>
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
                                        <img class="outer-frame" src="../img/etc/no-image.png" data-nftid="0"/>
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
            let nftId = $(this).attr('data-nftid');
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
                    currContainer.attr('data-nftid','0');
                    currContainer.attr('src', noImage);
                    break;
                case "background-frame-delete-btn" :
                    numName = "backgroundnum";
                    containerPrevCls = "bf";
                    editSelectItem = $('#edit-background-list');
                    currContainer = $('.bf'+nftId);
                    currContainer.attr('data-nftid','0');
                    currContainer.attr('src', noImage);
                    break;
            }
            let number = parseInt(parentSlideList.attr(numName)) - 1;
            parentSlideList.attr(numName , number)
            parentSlideList.attr('save','false');
            editSelectItem.find('.nft_item_'+nftId+' .gallery-edit-select-number').text('');
            currContainer.removeClass(containerPrevCls + nftId);
            $(this).remove();
        });
    },

    moreBtnClickEvent(btnId) {
        $('#'+btnId).on('click', function(){
            $(this).remove();
            let moreBtnId = $(this).attr("id");
            let editType = $(this).attr("edit_type");
            let moreList = "";
            document.querySelectorAll('.news').forEach((item)=>{
                item.classList.remove('news');
            })

            if(editType == "nft") {
                moreList = constEditContent.getEditNftList("more","edit");
            } else {
                moreList = constEditContent.getEditNftNotVideoList(editType);
            }

            constEditContent.containerHtml(editType, moreList);

            document.querySelectorAll('.news').forEach((item)=>{
               item.addEventListener('click',()=>{
                   nftImagesEvent(item);
               })
            })

            dontclick(nftImages);
            dontclick(frameImages);
            dontclick(backgroundImages);
        });
    }
}

$(document).ready(function(){
    constEditContent.getBeforeNftData();
    // constEditContent.addSection();
    constEditContent.MoreEditSlider("html");
    document.querySelectorAll('.playVideoBtn.new').forEach((item)=>{
        item.addEventListener('click',()=>{
            videoPlayEvent(item)
        })
    })


    dontclick(nftImages);
    dontclick(frameImages);
    dontclick(backgroundImages);

});