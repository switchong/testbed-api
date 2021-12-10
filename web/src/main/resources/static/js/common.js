$(document).ready(function(){
    //nav country
    $('#navigation-country .nav-country').on('click',function(){
        var country = $(this).data('country');
        if(country != "en") {
            alert("Comming soon!!");
            return false;
        }
    });
    // main nft image click
    $('#nftgram-list .card-img-top').on('click',function(){
        var collectionId = $(this).data('collectionid');
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
        var view_type = $(this).data('view');
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
            var html = toList(data.nftList);
            // total += data.total;
            $("#"+obj).append(html);

            if(data.total < size) {
                $(window).unbind();
            }
        }
    });
    $('input[name="page"]').val(nextPage);
    $('#nftgram-list .card-img-top').on('click',function(){
        var collectionId = $(this).data('collectionid');
        location.href="/gallery/"+collectionId;
    })
}

function toList(list) {
    var html = '';
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

function updateViewCount(nftId) {
    if(nftId > 0) {
        $.ajax({
            url:"/api/upview",
            type:"POST",
            dataType: "json",
            async:false,
            data:{"nftId":nftId},
            success : function(data){
                console.log("updateViewCount : "+data);
            }
        });
    }
}

function updateLike(likeFlag, nftId) {
    if(nftId > 0) {
        var likeCount = parseInt($('#likeCount').text());
        $.ajax({
            url:"/api/uplike",
            type:"POST",
            dataType: "json",
            async:false,
            data:{"nftId":nftId, "likeFlag":likeFlag},
            success : function(data){
                likeCount++;
                console.log(likeCount);
            }
        });
    }
}