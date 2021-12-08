$(document).ready(function(){
    // layout popup button
    $('#nft-layer-pop .nft-btn').find('.nft-btn-form').on('click', function(){
        var nft_form = $('.nft-form');    // layer-popup nft-form

        var btnName = $(this).data('btn');      // layer-popup button data-btn
        var btnForm = $('#nft-btn-'+btnName);   // layer-popup button id
        var btnArr = new Array("home", "info", "comment");
        if($.inArray(btnName, btnArr) >= 0) {
            // nft-btn .on 처리
            $('.nft-btn-form').removeClass("on");
            $(this).addClass("on");

            // nft-form .on 처리
            nft_form.removeClass("on");
            $('#nft-'+btnName).addClass("on");

            nft_form.css({"display":"none","height":"100%"});
            $('#nft-'+btnName).css("display","block");
            if(btnName == "info") {
                $('.attribute-text').removeClass("on");
                $('.attribute-text').hide();
                $('.info-attribute').eq(0).trigger('click');
            }
        } else if( btnName == "like") {
            alert('LIKE');
        } else if( btnName == "market") {
            var link = btnForm.data('link');
            if(link == "") {
                alert("Comming Soon.");
            } else{
                window.popup(link);
            }
        }
    });
    // nft-info
    $('.info-attribute').on('click',function(){
        var attr = $(this).data('attr');
        var attr_row = $('.attr-'+attr+'-row');
        // $('.attribute-text').removeClass("on");
        // $('.attribute-text').hide();
        if($(this).hasClass("on")) {
            $(this).removeClass("on");
            attr_row.hide();
        } else {
            $(this).addClass("on");
            attr_row.show();
        }
    });
});
/* 레이어 팝업 - 사용 권장 */
function layerPopId(layerId, boxPosition){
    if ( !boxPosition ) boxPosition = 'absolute';
    // set size of popup box
    var winHeight = $(window).height();
    var popHeight = $('.wrap_layer_popup#'+layerId).height() + parseInt( $('.wrap_layer_popup#'+layerId).css('padding-top') );

    // top position of popup box
    if ( winHeight > popHeight ){ //컨텐츠 height가 window보다 작은 경우
        if (boxPosition == 'absolute') 	var positionTop = (winHeight-popHeight)/2 + $(window).scrollTop() +'px';
        else var positionTop =  (winHeight-popHeight)/2 +'px';
    } else { //컨텐츠 height가 window보다 큰 경우
        if (boxPosition == 'absolute') var positionTop = $(window).scrollTop() + 20 +'px';
        else var positionTop = 20 +'px';
    }
    // left position of popup box
    var popWidth = $('.wrap_layer_popup#'+layerId).width();
    var positionLeft = -popWidth/2+'px';
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
    layerPopNftSet(nftId);
    $('.nft-form').css({"display":"none","height":"100%"});
    $('#nft-home').css("display","block");
    $('#nft-home .nft-image').css("height","100%");
    $('#nft-home .nft-image img').css("height","100%");

    var figure = $("#gr-gallery #nft_"+nftId);

    var imgSrc = figure.find('.card-img-top').attr('src');
    $('#nft-home .nft-image').css({"background":"url('"+imgSrc+"') no-repeat center"});
    return false;
}
function layerPopNftSet(nftId) {
    $('input[name="nft_id"]').val(nftId);
    $('input[name="nft_image_url"]').val(nftId);
    $('input[name="nft_name"]').val(nftId);
    $('input[name="user_name"]').val(nftId);
}

function layerPopHome() {

}

// 레이어 팝업창 닫기
function layerPopClose(){
    $('.wrap_layer_popup').fadeOut();
    $('.layer_popup_bg').fadeOut();
}