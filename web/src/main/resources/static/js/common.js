let currentPagePath = window.location.pathname;
let url_this_page = location.href;
let title_this_page = document.title;
let url_user_page = window.location.origin;
let sort1 = 0;

$(document).ready(function(){

    if(currentPagePath === '/') {
        $('#navigation-top').css({
            "position" : "sticky",
            "top" : "0",
            "left" : "0",
            "z-index" : "99999999999"
        })
    }

    $('input[type="text"] , textarea[name="comment"]').css('ime-mode','disabled');

    $('input[type="text"] , textarea[name="comment"]').on('keyup', function(e){
        console.log($(this).val());
        if(!(e.keyCode >= 37 && e.keyCode <= 40)) {
            let inputVal = $(this).val();
            var check = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/g;
            $(this).val(inputVal.replace(check,''));
        }
    })

    //nav country
    $('#navigation-country .nav-country').on('click',function(){
        let country = $(this).data('country');
        if(country != "en") {
            alert("Comming soon!!");
            return false;
        }
    });
    // main nft image click
    $('#nftgram-list .card-img-top').on('click',function(){
        let collectionId = $(this).data('collectionid');
        location.href="/gallery/"+collectionId;
    });
    // scroll auto load
    /*if($(window).scrollTop() + $(window).height() == $(document).height()) {
        moreView();

    };*/
    // scroll auto load
    $('body').scroll(function(){
        if($('body').scrollTop() + $('body').height() == $(document).height()) {
            moreView();
        }
    });

    $('select[name="selSort"]').change(function () {
        let sort = $(this).val();
        currentPage = 0;
        if(currentPagePath.split('/')[1] === 'gallery' && currentPagePath.split('/')[2] === undefined) {
            MoreSlide( 'main/page/gallery','html');
        }
        moreView("html");
    })

    $('.dropdown-item').on('click',function (){
        $('#dropdownMenuButton1').text($(this).text());
        $('input[name="dropdownsort"]').val($(this).attr("value"));
        sort1 = $(this).attr("value");
        currentPage = 0;
        $('#dropdownsearch').prop('checked',false);
        if(currentPagePath === '/gallery') {
            MoreSlide( 'all','html', sort1, 0,0);
        }
        moreView("html");
    })
    // view-type click event
    $('.gallery-view-type').on('click',function(){
        let view_type = $(this).data('view');
        if(view_type == "list") {
            location.href="/";
        } else {
            location.href="/gallery";

        }
    });

    $('#btnSearch').on('click',function(){
        let searchVal = $('#searchKeyword').val();
        if(searchVal == '' || searchVal == null) {
            alert("Search the Value");
            return false;
        }
        currentPage = 0;
        if(currentPagePath === '/gallery') {
            MoreSlide('all','html', sort1, 0,0);
        }
        moreView("html");
    });

    if($('#nftgram_wrap #nftgram-list').length > 0) {
        moreView();
    }

    $('#editProfileBtn').on('click',function(){
        let form = $('#editProfileForm');
        if(!checkId()) { // 중복값 없을때
            form.submit();
        } else {    // 중복값 존재
            alert("Is username Conflict");
        }
        return false;
    })
});

function DontLongNumber() {
    const countText = document.querySelectorAll('.count-text');

    countText.forEach((item, index) => {
        if(Number(item.innerHTML) >= 1000) {
            if(Math.floor(Number(item.innerHTML)/1000 > 99)) {
                item.innerHTML = `99k+`;
            }
            else {
                item.innerHTML = `${Math.floor(Number(item.innerHTML)/1000)}k+`;
            }
        }
    })
}

/*
*  main auto-loading
*  main search
*  obj nftgram-list
*
*/
const size = 20;

function moreView(type) {
    if(currentPagePath !== "/") {
        $(window).unbind();
        return;
    }

    let htmlBool = false;
    let total = (type=="html")?0:$('#nftgram-list .card').length;
    let nextPage = parseInt(total / size);
    let keyword = $('#searchKeyword').val();
    let sort = $('input[name="dropdownsort"]').val();
    let url = "/api/main/page?page=" + nextPage + "&size=" + size + "&keyword=" + keyword;
    let insTag = "" +
        '<div class=\"search-box\">' +
        '<div class=\"position-box\">' +
        ''+keyword+'' +
        "<span onclick=\"searchFormClose()\" id=\"close\" class=\"close\">X</span>" +
        '</div>'
    "</div>"

    if (sort != null) {
        url += "&sort=" + sort;
    }

    $.ajax({
        url: url,
        type: "GET",
        dataType: "json",
        data: {total: this.value},
        async: false,
        success: function (data) {
            let html = toList(data.nftList);

            if(keyword && data.total <= 0) {
                swal("Search No Data!!", '', 'error');
                return;
            } else {
                if(type == "html") {
                    $("#nftgram-list").html(html);
                }else {
                    $("#nftgram-list").append(html);
                }

                if (data.total < size) {
                    $(window).unbind();
                }

                if (keyword ){
                    $("#nftgram-tag").html(insTag);
                }
            }


        },
        error: function (data) {
            alert("error");
        }
    });

    $('input[name="page"]').val(nextPage);
    $('#nftgram-list .card-img-top').on('click', function () {
        let collectionId = $(this).data('collectionid');
        location.href = "/gallery/" + collectionId;
    })
    DontLongNumber();
}

function toList(list) {
    let html = '';
    $.each(list, function(key, nft){
        let pattern = "https://.*mp4";
        let date = timeToElapsed(nft.localDate);
        let imageUrlHtml = '<img class="card-img-top" src="'+nft.nftImageUrl+'" alt="'+nft.name+'" data-collectionid="'+nft.nftCollectionId+'" data-nid="'+nft.nftId+'" width="301px"  height="301px"/>';
        if(nft.nftImageUrl.match(/^https?:\/\/(.+\/)+.+(\.(swf|avi|flv|mpg|rm|mov|wav|asf|3gp|mkv|rmvb|mp4))$/i)) {
            imageUrlHtml = '<video class="card-img-top" controlslist="nodownload" loop="" playsinline="" preload="metadata" data-collectionid="'+nft.nftCollectionId+'" data-nid="'+nft.nftId+'"  style="border-radius: 0px;"><source src="'+nft.nftImageUrl+'" type="video/mp4"></video>';
        }

        html += '<div class="card" >\n' +
            '                '+imageUrlHtml+'\n' +
            '                <div class="card-body">\n' +
            '                     <div class="row">' +
            '                        <div class="row-data-left" style="overflow:hidden; text-overflow:ellipsis; white-space:nowrap;">\n' +
            '                            <img src="/img/icon/ic-gallery-profile.svg" class="card-img-user" > <a href="'+nft.userUrl+'" class="home-nft-username"><span class="text-muted left-nft-title">'+nft.username+'</span></a>\n' +
            '                        </div>\n'+'' +
            '                        <div class="row-data-right">\n' +
            '                            <img src="/img/icon/ic-gallery-eyes.svg" class="card-img-icon view-icon whIs19 ml20"> <span class="text-muted count-text">'+nft.viewCount+'</span>\n' +
            '                            <img src="/img/icon/ic-gallery-like.svg" class="card-img-icon whIs19 ml20 like-icon"> <span class="text-muted count-text">'+nft.likeCount+'</span>\n' +
            '                         </div>\n'+'' +
            '                      </div>\n' +
            '                    <p class="card-text-data"><small class="text-muted" >'+date+'</small></p>\n' +
            '                </div>\n' +
            '            </div>';

    });

    return html;
}
/* main auto-loading */

function searchFormClose() {
    $('#searchKeyword').val('');
    $("#nftgram-tag").html('');
    if(currentPagePath === '/gallery') {
        $('.search-box').remove();
        currentPage = 0;
        MoreSlide('all','html', sort1, 0,0);
    }
    moreView("html");
}

function getNftOne(nftId) {
    $('input[name="nft_id"]').val(nftId);

    let nftResult;
    let method = "POST";
    let url = "/api/nft";
    let param = {"nftId":nftId};
    if(nftId > 0) {
        // getNftPropertiesCount();
        nftResult = commonAjaxUrl(method, url, param);
    }
    return nftResult;
}

function getNftPropertiesCount() {
    let method = "POST";
    let url = "/api/nft/property";
    let param = {};
    propResult = commonAjaxUrl(method, url, param);

    return propResult;
}

function updateViewCount(nftId) {
    let method = "POST";
    let url = "/api/upview";
    let param = {"nftId":nftId};
    if(nftId > 0) {
        commonAjaxUrl(method, url, param);
    }
}

function updateLike(likeFlag, nftId) {
    let result;
    let method = "POST";
    let url = "/api/uplike";
    let param = {"nftId":nftId, "likeFlag":likeFlag};
    let nft_content = $('#nft-content-'+nftId)
    let likeCount = parseInt(nft_content.find('.likeCount').text());

    if(nftId > 0) {
        result = commonAjaxUrl(method, url, param);
        likeCount = result.likeTotalCount;

        $('#likeCount').text(likeCount);

        nft_content.find('.likeCount').text(likeCount);
    }
    return result;
}


function getCommentList(nftId) {
    let commSize = 10;
    let total = $('#nft-comment .comment-list .comment-list-row').length;
    let nextPage = parseInt(total/commSize);
    let currPage = parseInt($('input[name="nft_comment_page"]').val());

    let result;
    let row_html = '';
    let method = "POST";
    let url = "/api/comment/list";
    let param = {"nftId":nftId,"page":nextPage,"size":commSize};
    if(nftId > 0) {
        result = commonAjaxUrl(method, url, param);
        if(result.total > 0) {
            $.each(result.commentResponseList, function(k, comm) {
                let row_chk = $('#comment-row-'+comm.commId).length;
                if(row_chk == 0) {
                    row_html += '<div class="comment-list-row" id="comment-row-'+comm.commId+'">\n' +
                        '                            <div class="user-image"><img src="/img/icon/ic-gallery-profile.svg" class="whIs30"/></div>\n' +
                        '                            <div class="user-info">'+comm.user+'<br><span class="time">'+comm.createdDate+'</span></div>\n' +
                        '                            <div class="user-comment">'+$.nl2br(comm.comment)+'</div>\n' +
                        '                        </div>';
                }
            });
            $('#nft-comment .comment-list').append(row_html);
            $('input[name="nft_comment_page"]').val(nextPage);
        }

    }
}

function commonAjaxUrl (method, url, param) {
    let ajaxResult;
    if(method && url && param) {
        $.ajax({
            url:url,
            type:method,
            dataType: "json",
            async:false,
            data:param,
            success : function(result){
                ajaxResult = result;
            }
        });
    }

    return ajaxResult;
}
function timeToElapsed(datetaime){
    let localdate = parseInt(new Date(datetaime).getTime() / 1000)
    let parallax = parseInt((new Date()).getTime() / 1000) - localdate

    if(parallax > 86400){
        return parseInt(parallax / 86400) + " day ago"
    }else if(parallax > 3600){
        return parseInt(parallax / 3600) + " hour ago"
    }else if(parallax > 60){
        return parseInt(parallax / 60) + " minute ago"
    }else {
        return parseInt(parallax ) + " second ago"
    }
}

$.nl2br = function(tmpText){
    return tmpText.replace(/(\r\n|\n\r|\r|\n)/g, "<br>");
};

$.br2nl = function(tmpText){
    return tmpText.replace(/<br>/g, "\r");
};

//SNS 공유 URL
// const url_default_ks = "https://story.kakao.com/share?url=";
// const url_default_fb = "https://www.facebook.com/sharer/sharer.php?u=";
// const url_default_tw_txt = "https://twitter.com/intent/tweet?text=";
// const url_default_tw_url = "&url="; var url_default_band = "http://band.us/plugin/share?body=";
// const url_route_band = "&route="; var url_default_naver = "http://share.naver.com/web/shareView.nhn?url=";
//const title_default_naver = "&title=";
// const url_combine_ks = url_default_ks + url_this_page;
// const url_combine_fb = url_default_fb + url_this_page;
// const url_combine_tw = url_default_tw_txt + document.title + url_default_tw_url + url_this_page;
// const url_combine_band = url_default_band + encodeURI(url_this_page)+ '%0A' + encodeURI(title_this_page)+'%0A' + '&route=tistory.com';
// const url_combine_naver = url_default_naver + encodeURI(url_this_page) + title_default_naver + encodeURI(title_this_page);

//sns url copy // nftMemberId or nftMemberUserId
function clip(memberId){
    let path_arr = currentPagePath.split("/");
    let cate1 = path_arr[1];
    let cate2 = path_arr[2];

    let reqUrl = currentPagePath;  // 기본 현재 페이지 링크
    switch (cate1) {
        case 'gallery' :
            if(cate2 == "myfavorite") {
                reqUrl = "/user/"+memberId+"?page=favorite";
            } else if(cate2 == "mycollection") {
                reqUrl = "/user/"+memberId;
            } else {
                reqUrl = "/gallery/"+cate2;
            }
            break;
    }

    const textarea = document.createElement("textarea");
    document.body.appendChild(textarea);
    url = url_user_page+reqUrl;
    textarea.value = url;
    textarea.select();
    document.execCommand("copy");
    document.body.removeChild(textarea);
    swal("Link copied!!!!" , "", "success")

}

function urlCheck(memberId){

    let reqUrl = memberId;  // 기본 현재 페이지 링크

    if (memberId){
        window.open(reqUrl);
    }
}


// 공백체크
function noSpaceForm(obj){

    var idCheck = 0;
    var pwdCheck = 0;
    let id = $('#username').val();
    const str_space =/\s/;
    if (str_space.exec(obj.value)){
        swal("No spaces allowed" , "" , "error");
         obj.focus();
         obj.value = obj.value.replace(' ' ,'');
         return false;
    }
}


let idCheck = 0;
let pwdCheck = 0;
//아이디 체크하여 가입버튼 비활성화, 중복확인.
function checkId() {
    let inputed = $('.username').val();
    // true : 중복값 존재, false : 중복값없음
    let param = {"id" : inputed};
    let result = commonAjaxUrl("POST", "/username/check",param);
    if(inputed=="" && result == false) {
        $("#username").css("background-color", "#FFCECE");
        idCheck = 0;
    } else if ( result == false) {
        $("#username").css("background-color", "#B0F6AC");
        idCheck = 1;
        if(idCheck==1 && pwdCheck == 1) {
            signupCheck();
        }
    } else if ( result == true) {
        $("#username").css("background-color", "#FFCECE");
        idCheck = 0;
    }

    return result;
}

function getParameter(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}
