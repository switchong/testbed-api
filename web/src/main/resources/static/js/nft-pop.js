
$(document).ready(function($) {
    var obj		  = "listen_list";
    var pop       = $('#openListening');
    var audioFile = document.querySelector('.mp3_player');

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
