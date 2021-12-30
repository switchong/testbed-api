$(document).ready(function(){

    // layout popup button
    $('#nft-layer-pop .nft-btn').find('.nft-btn-form').on('click', function(){
        let nftId = $('input[name="nft_id"]').val();
        let nft_form = $('.nft-form');    // layer-popup nft-form

        let btnName = $(this).data('btn');      // layer-popup button data-btn
        let btnForm = $('#nft-btn-'+btnName);   // layer-popup button id
        let btnArr = new Array("home", "info", "comment");
        if($.inArray(btnName, btnArr) >= 0) {
            // nft-btn .on 처리
            $('.nft-btn-form#nft-btn-home, .nft-btn-form#nft-btn-info, .nft-btn-form#nft-btn-comment').removeClass("on");
            $(this).addClass("on");

            // nft-form .on 처리
            nft_form.removeClass("on");
            $('#nft-'+btnName).addClass("on");

            nft_form.css({"display":"none","height":"100%"});
            $('#nft-'+btnName).css("display","block");
            if(btnName == "info") {
                $('.info-attribute-text').removeClass("on");
                $('.info-attribute-text').hide();
                $('.info-attribute').eq(0).trigger('click');
            } else if(btnName == "comment") {
                getCommentList(nftId);
            }
        } else if( btnName == "like") {
            let likeFlag = (btnForm.hasClass('on'))?"Y":"N";
            let likeResult = updateLike(likeFlag, nftId);

            if(likeResult.loginFlag == "Y") {
                if(likeResult.likeFlag == "Y") {
                    btnForm.addClass('on');
                } else {
                    btnForm.removeClass('on');
                }
            } else {
                alert("Please Login to use");
                return false;
            }
        } else if( btnName == "market") {
            let link = btnForm.data('link');
            if(link == "") {
                alert("Comming Soon.");
            } else{
                window.open(link);
            }
        }
    });
    // nft-info
    $('.info-attribute').on('click',function(){
        let attr = $(this).data('attr');
        let attr_row = $('.attr-'+attr+'-row');
        // $('.info-attribute-text').removeClass("on");
        // $('.info-attribute-text').hide();
        if($(this).hasClass("on")) {
            $(this).removeClass("on");
            attr_row.hide();
        } else {
            $(this).addClass("on");
            attr_row.show();
        }
    });
    // nft-comment
    $('#nft-comment .comment_btn').on('click',function() {
        let result;
        let nftId = $('input[name="nft_id"]').val();
        let comment = $('textarea[name="comment"]').val();
        if(nftId > 0) {
            let method = "POST";
            let url = "/api/comment/save";
            let param = {"nftId":nftId, "comment":comment};

            result = commonAjaxUrl(method, url, param);
            if(result == 1) {
                getCommentList(nftId);
                alert("Success");
                $('textarea[name="comment"]').val('');
                return false;
            } else if(result == 2) {
                alert("Please Login to use");
                return false;
            } else {
                alert("Error");
                return false;
            }
        }
    })
});
/* 레이어 팝업 - 사용 권장 */
function layerPopId(layerId, boxPosition){
    if ( !boxPosition ) boxPosition = 'absolute';
    // set size of popup box
    let winHeight = $(window).height();
    let popHeight = $('.wrap_layer_popup#'+layerId).height() + parseInt( $('.wrap_layer_popup#'+layerId).css('padding-top') );

    // top position of popup box
    let positionTop = null;
    if ( winHeight > popHeight ){ //컨텐츠 height가 window보다 작은 경우
        if (boxPosition == 'absolute') 	positionTop = (winHeight-popHeight)/2 + $(window).scrollTop() +'px';
        else positionTop =  (winHeight-popHeight)/2 +'px';
    } else { //컨텐츠 height가 window보다 큰 경우
        if (boxPosition == 'absolute') positionTop = $(window).scrollTop() + 20 +'px';
        else positionTop = 20 +'px';
    }
    // left position of popup box
    let popWidth = $('.wrap_layer_popup#'+layerId).width();
    let positionLeft = -popWidth/2+'px';
    $('.wrap_layer_popup#'+layerId).css({
        'position' : boxPosition,
        'top' : positionTop,
        'margin-left' : positionLeft
    });

    // 팝업창 띄우기
    if(!$('div.layer_popup_bg').length){
        $('<div class="layer_popup_bg"></div>').appendTo('#nftgram_wrap');
    }
    $('div.layer_popup_bg').fadeIn();
    $('.wrap_layer_popup#'+layerId).fadeIn(function(){
        $('.wrap_layer_popup#'+layerId).css('display','block');
    });
}

function layerPopGallery(nftId) {
    updateViewCount(nftId); // view update
    let nftInfo = getNftOne(nftId);
    // NFT INTO
    layerPopByNft(nftInfo);

    $('#nft-layer-pop .nft-btn').find('.nft-btn-form').eq(0).trigger('click');
    $('.nft-form').css({"display":"none","height":"100%"});
    $('#nft-home').css("display","block");
    $('#nft-home .nft-image').css("height","100%");
    $('#nft-home .nft-image img').css("height","100%");

    let figure = $("#gr-gallery #nft_"+nftId);
    let imgSrc = '';
    if(figure.length > 0) {
        imgSrc = figure.find('.card-img-top').attr('src');
    } else {
        imgSrc = $('.gimage'+nftId).attr('src');
    }
    console.log(nftId+" > "+imgSrc);
    $('#nft-home .nft-image').css({"background":"url('"+imgSrc+"') no-repeat center"});

    // 팝업 마켓 버튼 .on 처리
    $('#nft-btn-market').removeClass('on');
    if($('#nft-btn-market').data('link')) {
        $('#nft-btn-market').addClass('on');
    }

    // nft-comment 초기화
    $('input[name="nft_comment_page"]').val(0);
    $('#nft-comment .comment-list').html('');

    return false;
}

// 레이어 팝업창 닫기
function layerPopClose(){
    $('.wrap_layer_popup').fadeOut();
    $('.layer_popup_bg').fadeOut();
}

function layerPopByNft(data) {
    let popup = $('#nft-layer-pop');
    let nft_info_form = popup.find('#nft-info');
    let nft_title_area = popup.find('.nft-title-area');
    let nft_gallery_figure = $('figure#nft_'+data.nftId);

    // div nft-info
    let description, collection_desc;
    description = (data.description)?$.nl2br(data.description):'';
    collection_desc = (data.collections.description)?$.nl2br(data.collections.description):'';
    description = (description=='')?collection_desc:collection_desc;
    nft_info_form.find('.attr-description-row td.attr-description-row-text').html(description);
    nft_info_form.find('.attr-detail-row td.attr-detail-row-text').html(description);
    nft_info_form.find('.attr-about-row td.attr-about-row-text').html(description);
    nft_info_form.find('.attr-about .attr-about-span').html(data.collectionName);

    let prop_html = '';
    if(data.propList.length > 0) {
        nft_info_form.find('.attr-properties').parents('tr').css('display','table-row');
        $.each(data.propList, function(k, v){
            prop_html += '<div class="prop-info">\n' +
                '            <p class="prop-info-nft prop-type">'+v.traitType+'</p>\n' +
                '            <p class="prop-info-nft prop-value">'+v.traitValue+'</p>\n' +
                '            <p class="prop-info-nft prop-per">'+v.orderCount+'</p>\n' +
                '        </div>';
        });
        nft_info_form.find('.attr-properties-row td.attr-properties-row-text').html(prop_html);
    } else {
        nft_info_form.find('.attr-properties').parents('tr').css('display','none');
    }

    // div nft-title-area
    let user_html = data.username+'<br><span class="time">'+data.createdDate+'</span>';

    nft_title_area.find('.nft-title .nft-title-info').text(data.name);
    nft_title_area.find('.nft-title-user-info').html(user_html);
    nft_title_area.find('.ethPrice').text(data.viewCount);
    nft_title_area.find('.likeCount').text(data.likeCount);

    nft_gallery_figure.find('.viewCount').text(data.viewCount);
    nft_gallery_figure.find('.likeCount').text(data.likeCount);

    if(data.likeFlag == "Y") {
        $('#nft-btn-like').addClass("on");
    } else {
        $('#nft-btn-like').removeClass("on");
    }
    $('#nft-btn-market').data('link',data.marketLink);

}