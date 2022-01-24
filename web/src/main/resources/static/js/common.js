$(document).ready(function(){
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
    if($(window).scrollTop() + $(window).height() == $(document).height()) {
        moreView();

    };
    // scroll auto load
    $(window).scroll(function(){
        if($(window).scrollTop() + $(window).height() == $(document).height()) {
            moreView();
        }
    });

    $('select[name="selSort"]').change(function () {
        let sort = $(this).val();
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
        moreView("html");
    });

    if($('#nftgram_wrap #nftgram-list').length > 0) {
        moreView();
    }
});

/*
*  main auto-loading
*  main search
*  obj nftgram-list
*
*/
const size = 20;

function moreView(type , nft) {

    let total = (type=="html")?0:$('#nftgram-list .card').length;
    let nextPage = parseInt(total / size);
    let keyword = $('#searchKeyword').val();
    let sort = $('#selSort').val();
    let url = "/api/main/page?page=" + nextPage + "&size=" + size + "&keyword=" + keyword;
    let  insTag = "" +
        '<div class=\"search-box\">' +
        '<div class=\"position-box\">' +
        ''+keyword+'' +
        "<span onclick=\"this.parentElement.style.display='none'\" id=\"close\" class=\"close\">X</span>" +
        '</div>'
        "</div>"

    if (keyword ){
        $("#nftgram-tag").html(insTag);
    }

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

            if(type == "html") {
                $("#nftgram-list").html(html);
            }else {
                $("#nftgram-list").append(html);
            }

            if (data.total < size) {
                $(window).unbind();
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
}

$("#btnCreatDtOrder, btnPointOrder").click(function(){
    var dataNm = $(this).data("datanm"); //data() 의 이름은 소문자로 작성
    listSort($(this), dataNm);
});

function SortList(){

}



function toList(list) {
    let html = '';
    $.each(list, function(key, nft){
        let pattern = "https://.*mp4";
        let date = timeToElapsed(nft.localDate);
        let imageUrlHtml = '<img class="card-img-top" src="'+nft.nftImageUrl+'" alt="'+nft.name+'" data-collectionid="'+nft.nftCollectionId+'" data-nid="'+nft.nftId+'" width="301px"  height="301px"/>';
        if(nft.nftImageUrl.match(/^https?:\/\/(.+\/)+.+(\.(swf|avi|flv|mpg|rm|mov|wav|asf|3gp|mkv|rmvb|mp4))$/i)) {
            imageUrlHtml = '<video class="card-img-top" controlslist="nodownload" loop="" playsinline="" preload="metadata" style="border-radius: 0px;"><source src="'+nft.nftImageUrl+'" type="video/mp4"></video>';
        }

        html += '<div class="card" >\n' +
            '                '+imageUrlHtml+'\n' +
            '                <div class="card-body">\n' +
            '                     <div class="row">' +
            '                        <div class="row-data-left" style="overflow:hidden; text-overflow:ellipsis; white-space:nowrap;">\n' +
            '                            <img src="/img/icon/Profile_icon.png" class="card-img-user" > <span class="text-muted">'+nft.username+'</span>\n' +
            '                        </div>\n'+'' +
            '                        <div class="row-data-right">\n' +
            '                            <img src="/img/icon/Price_icon.png" class="card-img-icon whIs19 ml20"> <span class="text-muted">'+nft.viewCount+'</span>\n' +
            '                            <img src="/img/icon/Like_icon.png" class="card-img-icon whIs19 ml20"> <span class="text-muted">'+nft.likeCount+'</span>\n' +
            '                         </div>\n'+'' +
            '                      </div>\n' +
            '                    <p class="card-text-data"><small class="text-muted" >'+date+'</small></p>\n' +
            '                </div>\n' +
            '            </div>';

    });

    return html;
}
/* main auto-loading */

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
    let likeCount = parseInt($('#likeCount').text());
    if(nftId > 0) {
        result = commonAjaxUrl(method, url, param);
        likeCount = result.likeTotalCount;

        $('#likeCount').text(likeCount);
        $('figure#nft_'+nftId).find('.likeCount').text(likeCount);
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
                        '                            <div class="user-image"><img src="/img/icon/Profile_icon.png" class="whIs30"/></div>\n' +
                        '                            <div class="user-info">'+comm.user+'<br><span class="time">'+comm.createdDate+'</span></div>\n' +
                        '                            <div class="user-comment">'+$.nl2br(comm.comment)+'</div>\n' +
                        '                        </div>';
                }
            });
            $('#nft-comment .comment-list').append(row_html);
            $('input[name="nft_comment_page"]').val(nextPage);
        }

    }
    console.log(result);
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
        return parseInt(parallax / 86400) + " Day ago"
    }else if(parallax > 3600){
        return parseInt(parallax / 3600) + " Hour ago"
    }else if(parallax > 60){
        return parseInt(parallax / 60) + " Minute ago"
    }else {
        return parseInt(parallax ) + " Second ago"
    }
}

$.nl2br = function(tmpText){
    return tmpText.replace(/(\r\n|\n\r|\r|\n)/g, "<br>");
};

$.br2nl = function(tmpText){
    return tmpText.replace(/<br>/g, "\r");
};
