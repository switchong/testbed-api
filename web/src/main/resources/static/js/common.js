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
        moreView("nftgram-list");
    };
    // scroll auto load
    $(window).scroll(function(){
        if($(window).scrollTop() + $(window).height() == $(document).height()) {
            moreView("nftgram-list");
        }
    });
    // view-type click event
    $('.gallery-view-type').on('click',function(){
        let view_type = $(this).data('view');
        if(view_type == "list") {
            location.href="/";
        } else {
            location.href="/gallery";

        }
    })
});

/* main auto-loading */
const size = 20;

function moreView(obj) {
    let total = $('#nftgram-list .card').length;
    let nextPage = parseInt(total/size);

    $.ajax({
        url:"/api/main/page?page="+nextPage+"&size="+size,
        type:"GET",
        dataType: "json",
        async:false,
        success : function(data){
            let html = toList(data.nftList);
            // total += data.total;
            $("#"+obj).append(html);

            if(data.total < size) {
                $(window).unbind();
            }
        }
    });
    $('input[name="page"]').val(nextPage);
    $('#nftgram-list .card-img-top').on('click',function(){
        let collectionId = $(this).data('collectionid');
        location.href="/gallery/"+collectionId;
    })
}

function toList(list) {
    let html = '';
    $.each(list, function(key, nft){
        html += '<div class="card" >\n' +
            '                <img class="card-img-top" src="'+nft.nftImageUrl+'" alt="'+nft.name+'" data-collectionid="'+nft.nftCollectionId+'" data-nid="'+nft.nftId+'" width="301px"  height="301px"/>\n' +
            '                <div class="card-body">\n' +
            '                    <h5 class="card-title">\n' +
            '                        <img src="/img/icon/Profile_icon.png" class="card-img-user" > <small class="text-muted">'+nft.username+'</small>\n' +
            '                    </h5>\n' +
            '                    <p class="card-text" >'+nft.nftCollectionName+'"</p>\n' +
            '                    <p class="card-text"><small class="text-muted" >'+nft.localDate+'</small></p>\n' +
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
        likeCount++;
    }
    $('#likeCount').text(likeCount);
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

$.nl2br = function(tmpText){
    return tmpText.replace(/(\r\n|\n\r|\r|\n)/g, "<br>");
};

$.br2nl = function(tmpText){
    return tmpText.replace(/<br>/g, "\r");
};
