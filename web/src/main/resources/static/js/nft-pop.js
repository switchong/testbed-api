
$(document).ready(function($) {
    var obj		  = "listen_list";
    var pop       = $('#openListening');
    var audioFile = document.querySelector('.mp3_player');

    var popSlider = new Swiper(pop.find('.swiper-container'), {
        init: false,
        loop: true,
        slidesPerView: 1,
        allowTouchMove: false,
        navigation: {
            prevEl: pop.find('.swiper-navigation .prev'),
            nextEl: pop.find('.swiper-navigation .next'),
        },
        on: {
            slideChangeTransitionStart: function() {
                audioFile.pause();
            }
        }
    });

    $('[data-mp3]').click(function(e) {
        e.preventDefault();
        var parentName = $(this).parent()[0].name;
        var step = (parentName == 'playMp3_1') ? '1' : '2';

        if (!audioFile.paused) {
            audioFile.pause();
        } else {
            audioSrc = $(this).attr('data-mp3');
            audioFile.src = 'https://korean-ndrm.siwonschool.com/Korea/listening/' + audioSrc + '-0' + step + '.mp3';
            audioFile.play();
        }
    });

    $('.chg_list > button').on("click",function(){
        var cls = $(this).attr('class');
        var cate_num = $("input[name=cate_num]").val();
        var cate = parseInt($("input[name=cate]").val());
        var gdx = parseInt($("input[name=gdx]").val());
        var uid = $("input[name=uid]").val();

        var l_uid = "";
        var l_cate = cate;

        // 페이지 내의 마지막 정보 가져오기
        var last_eq = $('#listen_list > li').length-1;
        var first_uid = $('#listen_list > li').eq(0).attr('id');	// uid
        var last_uid = $('#listen_list > li').eq(last_eq).attr('id');	// uid
        var last_page = $('#pagearea_listen_list > a').length;
        console.log(last_uid);
        // 카테고리 내의 gdx 증감
        if(cls == "arrows prev") {
            gdx--;
        } else if(cls == "arrows next") {
            gdx++;
        }

        if(gdx < 1) {
            gdx = 4;
            cate--;
        } else if(gdx > 4) {
            gdx = 1;
            cate++;
        }

        if(cate < 1) {
            cate = 1;
            gdx = 1;
        } else if(cate > 12) {
            cate = 1;
        }

        // 카테고리로 검색시 롤링
        if(cate_num > 0) {
            l_cate = lpad(cate_num, 2, "0");	// 빈값채우기
        } else {
            l_cate = lpad(cate, 2, "0");	// 빈값채우기
        }

        l_uid = l_cate+"_"+"0"+gdx;

        var page = parseInt($("input[name=page_"+obj+"]").val());
        if(uid == first_uid && cls == "arrows prev") {
            if(page >= 1) {
                page--;
            }
            if(page > 0) {
                $("input[name=page_"+obj+"]").val(page);
                page_change(obj,page);
            } else if (page == 0) {
                alert("첫 페이지 입니다.");
                return false;
            }
        } else if(uid == last_uid && cls == "arrows next") {
            if(page < last_page) {
                page++;
                $("input[name=page_"+obj+"]").val(page);
                page_change(obj,page);
            } else {
                alert("마지막 페이지 입니다.");
                return false;
            }
        }

        openListening(l_uid);

    });

    load_list(obj);
});

function openListening(uid) {
    var uno	   = $('input[name=uno]').val();
    if(!uno) {
        alert(getStrLanguage('member','10','login'));// 로그인 후 이용하실수 있습니다.
        loginChk();
        return false;
    }

    var cate   = $('#'+uid).attr('data-cate');
    var gdx	   = $('#'+uid).attr('data-gdx');
    var pop    = $('#nft-layer-content');
    var slider = document.querySelector('#nft-layer-content .swiper-container').swiper;

    pop.find('[data-mp3]').attr('data-mp3', uid.replace('_', '-'));

    slider.removeAllSlides(); // 슬라이더 초기화

    slider.appendSlide([
        '<div class="swiper-slide"><img usemap="#playMp3_1" src="//data.siwonschool.com/korean_board/listening/'+uid+'_01.jpg" alt=""></div>',
        '<div class="swiper-slide"><img usemap="#playMp3_2" src="//data.siwonschool.com/korean_board/listening/'+uid+'_02.jpg" alt=""></div>',
    ]);

    layerPopId('nft-layer-content', 'fixed');

    slider.init();
    slider.slideToLoop(0, 0);

    $('input[name=cate]').val(cate);
    $('input[name=gdx]').val(gdx);
    $('input[name=uid]').val(uid);
}

function closeListening() {
    var pop       = $('#nft-layer-content');
    var slider    = document.querySelector('#nft-layer-content .swiper-container').swiper;
    var audioFile = document.querySelector('.mp3_player');

    audioFile.pause();
    pop.fadeOut(function() {
        slider.removeAllSlides();
    });
    $('.layer_popup_bg').fadeOut(function() {
        $(this).remove();
    });
}

function cateSelect(cate) {
    $('input[name=cate]').val(cate);
    $('input[name=cate_num]').val(cate);

    load_list("listen_list");
}

function page_change(obj,page){
    $("input[name=page_"+obj+"]").val(page);
    load_list(obj);
}

function load_list(obj){
    if($("#"+obj).length){
        var cate_num = $("input[name=cate_num]").val();
        var cate = $("input[name=cate]").val();
        var gdx = parseInt($("input[name=gdx]").val());
        var uid = $("input[name=uid]").val();

        if(!$("input[name=page_"+obj+"]").length){
            $("body").append("<input type=\"hidden\" name=\"page_"+obj+"\" value=1>");
        }
        var page = $("input[name=page_"+obj+"]").val();
        if(cate_num > 0) {
            page = 1;
        }
        $.ajax({
            url:"/?s=free&act=listening_list",
            type:"POST",
            dataType: "json",
            async:false,
            data:{obj:obj, page: page, cate: cate, cate_num:cate_num},
            success : function(data){
                $("#"+obj).html(data.list);
                $("#pagearea_"+obj).html(data.page_area);
            }
        });
    }
}

function lpad(str, padLen, padStr) {
    if (padStr.length > padLen) {
        console.log("오류 : 채우고자 하는 문자열이 요청 길이보다 큽니다");
        return str;
    }
    str += ""; // 문자로
    padStr += ""; // 문자로
    while (str.length < padLen)
        str = padStr + str;
    str = str.length >= padLen ? str.substring(0, padLen) : str;
    return str;
}
