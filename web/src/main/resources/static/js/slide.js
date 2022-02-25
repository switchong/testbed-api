let currentNum = 0;
let prevBtn;
let nextBtn;
let slideContainer;
let slides;
let currentPage = 0;
let nowLocation;
let prevSort="0";
let prevKeyword = '';

const setBackHeight = () => {
    let newHeight = $(window).height() - $('#navigation-top').height() + 8;
    if($(window).width() <= 900) {
        if($(window).width() < $(window).height()) {
            newHeight = $(window).width() - $('#navigation-top').height() + $('#navigation-top > nav:last-child').height() + 15;
            if(window.location.href.split('/')[4] === 'edit') {
                newHeight = 100 + "vw";
            }
        }
        else {
            newHeight = $(window).height() - $('#navigation-top').height() + $('#navigation-top > nav:last-child').height() + 15;
            if(window.location.href.split('/')[4] === 'edit') {
                newHeight = 100 + "vh";
            }
        }
    }
    $('#nftgram_wrap').css({
        'height' : newHeight,
    });
    currentPage = 0;
    goSlide();
}

const MoreSlide = (uri, type, sort1, userno, cid, address, likeFlag, username) => {

    let keyword = $('#searchKeyword').val();
    let insTag = "" +
        '<div class=\"search-box\">' +
        '<div class=\"position-box\">' +
        ''+keyword+'' +
        "<span onclick=\"searchFormClose()\" id=\"close\" class=\"close\">X</span>" +
        '</div>'
    "</div>";
    let sort = sort1;
    let size = 18;
    let url = `/api/gallery/page?pageType=${uri}&page=${currentPage++}&size=` + size + `&keyword=` + keyword;
    if(sort !== "0") {
        url += "&sort=" + sort;
    }
    if(userno !== 0) {
        url += "&userno=" + userno;
    }
    if(cid !== 0) {
        url += "&cid=" + cid;
    }
    if(address) {
        url += "&address=" + address;
    }
    if(likeFlag) {
        url += "&likeFlag=" + likeFlag;
    }
    if(username) {
        url += "&username=" + username;
    }
    $.ajax({
        url: url,
        type: "GET",
        dataType: "json",
        data: {total: this.value},
        async: false,
        success : function (data) {
            if(data.nftListCount <= 0) {
                if(keyword) {
                    swal('No Search Data', '', 'error');
                    return;
                }
                else {
                    swal('Display your Unique Digital Creation',"",'error');
                }
            }
            else {
                if (type === 'html') {
                    if(prevSort !== sort || prevSort === "0" || prevKeyword !== keyword) {
                        goFirst();
                        $('.gallery-slide-list-container').empty();
                        prevSort = sort;
                        prevKeyword = keyword;
                    }
                }
                if(keyword) {
                    if($('.search-box')) {
                        $('.search-box').remove();
                    }
                    $(".gallery-container").prepend(insTag);
                }
                if(currentPage === 1) {
                    $('.gallery-slide-list-container').empty();
                }
                deleteEventPopUp();
                $(".gallery-slide-list-container").append(makeGalleryList(data));
                if(currentPage !== 1) {
                    Refresh();
                }
                if(type === 'html') {
                    goSlide();
                }
                giveClickEvent();
            }
        },
        error : function () {
            console.log('error!');
        }
    })
    DontLongNumber();
}

const makeGalleryList = (data) => {
    const newList = data.nftSliderList.map((item)=> {
        let innerNewList = ''
        item.forEach((inner)=>{
            let date = timeToElapsed(inner.localDate);
            innerNewList = innerNewList + `
                            <div class="image-container" id="nft-content-${inner.nftId}" >
                                <div class="image-container-content" data-nftid="${inner.nftId}">
                                    <img class="outer-frame" src="/img/etc/no-image.png"/>`;
            if(inner.tagType == "video") {
                innerNewList += `<video class="inner-picture gimage${inner.nftId}" controls controlsList="nodownload"  data-layer-btn="nft-layer-pop" alt="${inner.name}" data-nftid="${inner.nftId}" src="${inner.nftImageUrl}"/>`;
            } else {
                innerNewList += `<img class="inner-picture gimage${inner.nftId}" data-layer-btn="nft-layer-pop" alt="${inner.name}" data-nftid="${inner.nftId}" src="${inner.nftImageUrl}" />`;
            }
            innerNewList += `</div>
                                <div class="picture-explain">
                                    <div class="picture-title-container">
                                        <p class="picture-title">${inner.name}</p>
                                    </div>
                                    <div class="picture-explain-bottom">
                                        <div class="user-info">
                                            <img src="/img/icon/ic-gallery-profile.svg" />
                                            <div class="nft-title-user">
                                                <div class="user-info-name">
                                                    <span>${inner.username}</span>
                                                    <span class="time">${date}</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="picture-price">
                                            <img src="/img/icon/ic-gallery-eyes.svg" class="hIs24 wIs20" />
                                            <span class="viewCount count-text">${inner.viewCount}</span>
                                            <img src="/img/icon/ic-gallery-like.svg" class="hIs24 wIs20" />
                                            <span class="likeCount count-text">${inner.likeCount}</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        `;
        });
        return `
                        <div class="gallery-slide-list">
                            <img src="/img/etc/no-image.png" alt="background" />
                            <div class="gallery-slide-list-item">
                                <div class="gallery-slide-list-item-picture">
                                    <div class="gallery-slide-list-item-down"></div>
                                    ${innerNewList}
                                </div>
                            </div>
                        </div>
                    `;
    });

    return newList;
}

const goNext = () => {
    let nowList6 = document.querySelectorAll('.gallery-slide-list')[currentNum];
    if(slides.length !== currentNum + 1) {
        slides.forEach((item)=>{
            item.style.transform = 'scale(1)';
        });
        if(window.innerWidth > 900 || window.innerWidth > window.innerHeight) {
            if(nowLocation === '/gallery/edit') {
                if(nowList6.getAttribute('save') === 'false') {
                    swal(`Currently not saved for section ${currentNum}`,'','error');
                }
                else {
                    currentNum++;
                    setTimeout(()=>{
                        slideContainer.style.transform = `translateX(${-(slides[currentNum].getBoundingClientRect().left - slideContainer.getBoundingClientRect().left)}px)`;
                    },300);
                    dontclick(nftImages);
                    dontclick(frameImages);
                    dontclick(backgroundImages);
                }
            }
            else {
                currentNum++;
                setTimeout(()=>{
                    slideContainer.style.transform = `translateX(${-(slides[currentNum].getBoundingClientRect().left - slideContainer.getBoundingClientRect().left)}px)`;
                },300)
            }
        }
        else {
            if(nowLocation === '/gallery/edit') {
                if(nowList6.getAttribute('save') === 'false') {
                    swal(`Currently not saved for section ${currentNum}`,'','error');
                }
                else {
                    currentNum++;
                    setTimeout(()=>{
                        slideContainer.style.transform = `translateX(${(slides[currentNum].getBoundingClientRect().bottom - slideContainer.getBoundingClientRect().bottom)}px)`;
                    },300);
                    dontclick(nftImages);
                    dontclick(frameImages);
                    dontclick(backgroundImages);
                }
            }
            else {
                currentNum++;
                setTimeout(()=>{
                    slideContainer.style.transform = `translateX(${(slides[currentNum].getBoundingClientRect().bottom - slideContainer.getBoundingClientRect().bottom)}px)`;
                },300);
            }
        }

        slides.forEach((item, index)=>{
            if(index !== currentNum) {
                item.style.transform = 'scale(0.9)';
            }
        });
    }
    else {
        if(nowLocation === '/gallery') {
            MoreSlide('all', '', sort1,0, 0, '', '','');
        }
        else if(nowLocation === '/gallery/myfavorite') {
            MoreSlide('user','',sort1,userno, 0, '' , "Y", '')
        }
        else if(nowLocation === '/gallery/mycollection') {
            MoreSlide('mycollection', '', sort1, 0,0, '','', '');
        }
        else if(nowLocation === '/gallery/' + nowLocation.split('/')[2]) {
            if(nowLocation.split('/')[2] !== 'edit') {
                MoreSlide('gallery','', sort1, 0, nowLocation.split('/')[2], '','', '');
            }
        }
        else if(nowLocation.split('/')[2] === 'address') {
            MoreSlide('address', '', sort1, 0, 0, nowLocation.split('/')[3],'', '')
        }
        else if(nowLocation.split('/')[2] === 'name') {
            MoreSlide('username','',sort1,0,0,'','', nowLocation.split('/')[3])
        }
        else if(nowLocation.split('/')[2] === 'username') {
            MoreSlide('externaluname','',sort1,0,0,'','', nowLocation.split('/')[3])
        }
        else if(nowLocation === '/user/' + nowLocation.split('/')[2]) {
            if(window.location.search === '?page=favorite') {
                MoreSlide('user','',sort1,nowLocation.split('/')[2], 0, '', "Y", '');
            }
            else {
                MoreSlide('user','',sort1,nowLocation.split('/')[2],0, '',"N", '');
            }
        }
    }
}

const goPrev = () => {
    let nowList7 = document.querySelectorAll('.gallery-slide-list')[currentNum];
    if(currentNum > 0) {
        slides.forEach((item)=>{
            item.style.transform = 'scale(1)';
        });
        if(window.innerWidth > 900 || window.innerWidth > window.innerHeight) {
            if(nowLocation === '/gallery/edit') {
                if(nowList7.getAttribute('save') === 'false') {
                    swal(`Currently not saved for section ${currentNum}`,'','error');
                }
                else {
                    currentNum--;
                    setTimeout(()=>{
                        slideContainer.style.transform = `translateX(${-(slides[currentNum].getBoundingClientRect().left - slideContainer.getBoundingClientRect().left)}px)`;
                    },300);
                    dontclick(nftImages);
                    dontclick(frameImages);
                    dontclick(backgroundImages);
                }
            }
            else {
                currentNum--;
                setTimeout(()=>{
                    slideContainer.style.transform = `translateX(${-(slides[currentNum].getBoundingClientRect().left - slideContainer.getBoundingClientRect().left)}px)`;
                },300);
            }
        }
        else {
            if(nowLocation === '/gallery/edit') {
                if(nowList7.getAttribute('save') === 'false') {
                    swal(`Currently not saved for section ${currentNum}`,'','error');
                }
                else {
                    currentNum--;
                    setTimeout(()=>{
                        slideContainer.style.transform = `translateX(${(slides[currentNum].getBoundingClientRect().bottom - slideContainer.getBoundingClientRect().bottom)}px)`;
                    },300);
                    dontclick(nftImages);
                    dontclick(frameImages);
                    dontclick(backgroundImages);
                }
            }
            else {
                currentNum--;
                setTimeout(()=>{
                    slideContainer.style.transform = `translateX(${(slides[currentNum].getBoundingClientRect().bottom - slideContainer.getBoundingClientRect().bottom)}px)`;
                },300);
            }
        }

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
    nowLocation = window.location.pathname;
    let vh = window.innerHeight * 0.01;
    document.documentElement.style.setProperty("--vh", `${vh}px`);
    if(nowLocation === '/gallery' && currentPage === 0) {
        MoreSlide('all', '', sort1,0, 0, '', '', '');
    }
    else if(nowLocation === '/gallery/myfavorite' && currentPage === 0) {
        MoreSlide('user','',sort1,userno, 0, '', 'Y', '');
    }
    else if(nowLocation === '/gallery/mycollection' && currentPage === 0) {
        MoreSlide('mycollection', '', sort1, 0,0, '', '', '');
    }
    else if(nowLocation === '/gallery/' + nowLocation.split('/')[2] && currentPage === 0) {
        if(nowLocation.split('/')[2] !== 'edit') {
            MoreSlide('gallery','', sort1, 0, nowLocation.split('/')[2], 0, '', '');
        }
    }
    else if(nowLocation.split('/')[2] === 'address' && currentPage === 0) {
        MoreSlide('address', '', sort1, 0, 0, nowLocation.split('/')[3], '', '')
    }
    else if(nowLocation.split('/')[2] === 'name' && currentPage === 0) {
        MoreSlide('username','',sort1,0,0,'','', nowLocation.split('/')[3])
    }
    else if(nowLocation.split('/')[2] === 'username' && currentPage === 0) {
        MoreSlide('externaluname','',sort1,0,0,'','', nowLocation.split('/')[3])
    }
    else if(nowLocation === '/user/' + nowLocation.split('/')[2] && currentPage === 0) {
        if(window.location.search === '?page=favorite') {
            MoreSlide('user','',sort1,nowLocation.split('/')[2], 0, '', 'Y', '');
        }
        else {
            MoreSlide('user','',sort1,nowLocation.split('/')[2],0, '', 'N', '');
        }
    }
    prevBtn = document.querySelector('#prevBtn');
    nextBtn = document.querySelector('#nextBtn');
    slideContainer = document.querySelector('.gallery-slide-list-container');
    slides = document.querySelectorAll('.gallery-slide-list');

    slides.forEach((item, index)=>{
        if(index !== currentNum) {
            item.style.transform = 'scale(0.9)';
        }
    });


    window.addEventListener('resize',goFirst);

    prevBtn.addEventListener('click',goPrev);

    nextBtn.addEventListener('click',goNext);
}

const goEditList = () => {
    let pageType = "edit";
    let url = "/api/gallery/page?pageType="+pageType;
    let data = "";
    let html = "";

    $.ajax({
        url: url,
        type: "GET",
        dataType: "json",
        data: {data},
        async: false,
        success : function (data) {
            console.log(data);
        },

        error : function () {
            console.log('error!');
        }
    })
}

window.addEventListener('DOMContentLoaded',setBackHeight);