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
    console.log($(window).width());
    if($(window).width() <= 900) {
        if($(window).width() < $(window).height()) {
            newHeight = $(window).width() - $('#navigation-top').height() + $('#navigation-top > nav:last-child').height() + 15;
            console.log(window.location.href.split('/')[4])
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
        console.log(newHeight, $('#navigation-top > nav:last-child').width());
        console.log($(window).width());
    }
    $('#nftgram_wrap').css({
        'height' : newHeight,
    });

    goSlide();
}

const MoreEdit = () => {
    const parentNode = $('.gallery-slide-list-container');
    const editContent = `
        <div class="gallery-slide-list">
                        <img src="../img/etc/no-image.png" class="back-frame" alt="background" />
                        <div class="gallery-slide-list-item">
                            <div class="gallery-slide-list-item-picture">
                                <div class="gallery-slide-list-item-down"></div>
                                <div class="image-container">
                                    <div class="image-container-content">
                                        <img class="outer-frame" src="../img/etc/no-image.png"/>
                                        <img class="inner-picture" src="../img/etc/no-image.png" style="border : 1px solid lightgray;"/>
                                    </div>
                                    <div class="picture-explain" style="opacity: 0">
                                        <p class="picture-title">NFT TITLE #0000</p>
                                        <div class="picture-explain-bottom">
                                            <div class="user-info">
                                                <img src="/img/icon/Profile_icon.png" />
                                                <div class="nft-title-user">
                                                    <div class="user-info-name">
                                                        <span></span>
                                                        <span class="time"></span>
                                                        <!--                                                    <span>1 MINUTE AGO</span>-->
                                                        <!--                                                    <div class="nft-title-user-info" th:text="">USERNAME<br><span class="time">1 MINUTE AGO</span></div>-->
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="picture-price">
                                                <img src="/img/icon/Price_icon.png" />
                                                <span>00</span>
                                                <img src="/img/icon/Like_icon.png" />
                                                <span>00</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="image-container">
                                    <div class="image-container-content">
                                        <img class="outer-frame" src="../img/etc/no-image.png"/>
                                        <img class="inner-picture" src="../img/etc/no-image.png" style="border : 1px solid lightgray;"/>
                                    </div>
                                    <div class="picture-explain" style="opacity: 0">
                                        <p class="picture-title">NFT TITLE #0000</p>
                                        <div class="picture-explain-bottom">
                                            <div class="user-info">
                                                <img src="/img/icon/Profile_icon.png" />
                                                <div class="nft-title-user">
                                                    <div class="user-info-name">
                                                        <span></span>
                                                        <span class="time"></span>
                                                        <!--                                                    <span>1 MINUTE AGO</span>-->
                                                        <!--                                                    <div class="nft-title-user-info" th:text="">USERNAME<br><span class="time">1 MINUTE AGO</span></div>-->
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="picture-price">
                                                <img src="/img/icon/Price_icon.png" />
                                                <span>00</span>
                                                <img src="/img/icon/Like_icon.png" />
                                                <span>00</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="image-container">
                                    <div class="image-container-content">
                                        <img class="outer-frame" src="../img/etc/no-image.png"/>
                                        <img class="inner-picture" src="../img/etc/no-image.png" style="border : 1px solid lightgray;"/>
                                    </div>
                                    <div class="picture-explain" style="opacity: 0">
                                        <p class="picture-title">NFT TITLE #0000</p>
                                        <div class="picture-explain-bottom">
                                            <div class="user-info">
                                                <img src="/img/icon/Profile_icon.png" />
                                                <div class="nft-title-user">
                                                    <div class="user-info-name">
                                                        <span></span>
                                                        <span class="time"></span>
                                                        <!--                                                    <span>1 MINUTE AGO</span>-->
                                                        <!--                                                    <div class="nft-title-user-info" th:text="">USERNAME<br><span class="time">1 MINUTE AGO</span></div>-->
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="picture-price">
                                                <img src="/img/icon/Price_icon.png" />
                                                <span>00</span>
                                                <img src="/img/icon/Like_icon.png" />
                                                <span>00</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
    `;
    parentNode.append(editContent);
    Refresh();
    nftMaxLength = 3 * (slides.length);
    backgroundMaxLength += 1;
    nftOk = false;
}

const MoreSlide = (uri, type) => {

    let keyword = $('#searchKeyword').val();
    let insTag = "" +
        '<div class=\"search-box\">' +
        '<div class=\"position-box\">' +
        ''+keyword+'' +
        "<span onclick=\"searchFormClose()\" id=\"close\" class=\"close\">X</span>" +
        '</div>'
    "</div>";
    let sort = $('#selSort').val();
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
    let size = 18;
    let url = `/api/${uri}?page=${currentPage++}&size=` + size + `&keyword=` + keyword;
    if(sort !== "0") {
        url += "&sort=" + sort;
    }
    $.ajax({
        url: url,
        type: "GET",
        dataType: "json",
        data: {total: this.value},
        async: false,
        success : function (data) {
            if(data.total === 0) {
                alert('lastData');
            }
            else {
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
}

const makeGalleryList = (data) => {
    const newList = data.slideList.map((item)=> {
        let innerNewList = ''
        item.forEach((inner)=>{
            innerNewList = innerNewList + `
                            <div class="image-container">
                                <div class="image-container-content" data-nftid="${inner.nftId}">
                                    <img class="outer-frame" src="../img/etc/no-image.png"/>`;
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
                                                    <span class="time">1 minute ago</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="picture-price">
                                            <img src="../img/icon/ic-gallery-eyes.svg" class="hIs24 wIs20" />
                                            <span class="viewCount count-text">${inner.viewCount}</span>
                                            <img src="../img/icon/ic-gallery-like.svg" class="hIs24 wIs20" />
                                            <span class="likeCount count-text">${inner.likeCount}</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        `;
        });
        return `
                        <div class="gallery-slide-list">
                            <img src="/img/gallery/backframe.jpg" alt="background" />
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
    if(slides.length !== currentNum + 1) {
        currentNum++;
        slides.forEach((item)=>{
            item.style.transform = 'scale(1)';
        });
        console.log(currentNum, slides.length);
        if(window.innerWidth > 900 || window.innerWidth > window.innerHeight) {
            slideContainer.style.transform = `translateX(${-(slides[currentNum].getBoundingClientRect().left - slideContainer.getBoundingClientRect().left)}px)`;
        }
        else {
            slideContainer.style.transform = `translateX(${(slides[currentNum].getBoundingClientRect().bottom - slideContainer.getBoundingClientRect().bottom)}px)`;
        }
        slides.forEach((item, index)=>{
            if(index !== currentNum) {
                item.style.transform = 'scale(0.9)';
            }
        });
    }
    else {
        if(nowLocation === undefined) {
            MoreSlide('main/page/gallery');
        }
        if(nowLocation === 'edit') {
            if(nftOk && nftTotalOK) {
                MoreEdit();
            }
            else {
                if(!nftTotalOK) {
                    alert('모든 nft를 넣으셔서 추가 틀을 만들 수 없습니다.');
                }
                else if(!nftOk) {
                    alert(`${nftMaxLength}개를 모두 채우셔야 합니다!`);
                }
            }
        }
    }
}

const goPrev = () => {
    if(currentNum > 0) {
        currentNum--;
        slides.forEach((item)=>{
            item.style.transform = 'scale(1)';
        });
        console.log(currentNum, slides.length);
        if(window.innerWidth > 900 || window.innerWidth > window.innerHeight) {
            slideContainer.style.transform = `translateX(${-(slides[currentNum].getBoundingClientRect().left - slideContainer.getBoundingClientRect().left)}px)`;
        }
        else {
            slideContainer.style.transform = `translateX(${(slides[currentNum].getBoundingClientRect().bottom - slideContainer.getBoundingClientRect().bottom)}px)`;
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
    console.log(window.location.href.split('/')[4]);
    nowLocation = window.location.href.split('/')[4];
    let vh = window.innerHeight * 0.01;
    document.documentElement.style.setProperty("--vh", `${vh}px`);
    if(nowLocation === undefined && currentPage === 0) {
        MoreSlide('main/page/gallery');
    }
    prevBtn = document.querySelector('#prevBtn');
    nextBtn = document.querySelector('#nextBtn');
    slideContainer = document.querySelector('.gallery-slide-list-container');
    slides = document.querySelectorAll('.gallery-slide-list');

    slides.forEach((item, index)=>{
        if(index !== currentNum) {
            item.style.transform = 'scale(0.9)';
        }
        else {
            console.log("바보");
        }
    });

    console.log(currentNum, slides.length);


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