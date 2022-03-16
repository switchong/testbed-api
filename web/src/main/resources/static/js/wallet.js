if (window.ethereum) {
    handleEthereum();

    setInterval(handleSelectAddress, 1000);
} else {
    window.addEventListener('ethereum#initialized', handleEthereum, {
        once: true,
    });

    // If the event is not dispatched by the end of the timeout,
    // the user probably doesn't have MetaMask installed.
    setTimeout(handleEthereum, 3000); // 3 seconds
}

function handleEthereum() {
    const { ethereum } = window;
    if (ethereum && ethereum.isMetaMask) {
        //console.log('Ethereum successfully detected!');
        // Access the decentralized web!
    } else {
        console.log('Please install MetaMask!');
    }
}

function handleSelectAddress() {
    let address = ethereum.selectedAddress;
    if(address == null) {
        console.log('Please connect Metamask Wallet!');
    } else {
        metamask.connectedWallet(address);
        //clearInterval(handleSelectAddress);
    }
}

if(typeof window.ethereum !== 'undefined') {
    window.ethereum.request({method:"eth_chainId"}).then((netId) => {
        webEth.chainId = parseInt(netId, 16);
    })

    // 메타마스크 계정 변경시
    window.ethereum.on('accountsChanged', function(accounts) {
        let address, result;
        let isUse = false;
        if(accounts.length > 0) {
            address = accounts[0];
            let walletListt = walletCheck("result");
            if(walletListt.loginFlag == "Y") {
                if(walletListt.memberWalletResponsesList.length > 0) {
                    $.each(walletListt.memberWalletResponsesList, function (k,v) {
                        if(v.wcontractAddress == address) {
                            isUse = true;
                        }
                    });
                    if(isUse == false) {
                        // 지갑주소 저장
                        result = walletSave(address);
                    }
                }
            }
            if(result == 1 || result == 5) {
                location.href = "/";
            }
        }
    });

    // 메타마스크 메인넷 변경시
    window.ethereum.on('chainChanged', (networkId) => {
        webEth.chainId = parseInt(networkId, 16);
        if(webEth.chainId != 1) {
            swal("Please select your Metamask wallet as your Ethereum Mainnet.",'','error');
            return false;
        }
    });
}
$(document).ready(function(){

    // navigation mywallet-btn
    $('.nav-link.mywallet > img').on('click',function(event){
        // wallet check
        walletCheck("nav", event);
        // lod
    });

    // $('.wallet-wrap').hover(function(){
    //     $(this).addClass('on');
    // }, function(){
    //     $(this).removeClass('on');
    // });

    window.addEventListener('resize', ()=>{
        if($('.wallet-wrap').hasClass('on')) {
            $('.wallet-wrap').removeClass('on');
            $('.mywallet').removeClass('on');
        }
    })

    // Metamask Connect Button
    $('.btn-metamask').on('click',function(){
        let address, userResult, failResult, result;
        if(walletCheck("metamask") == true) {
            if(metamask.beforeWalletCheck() == true) {
                if(metamask.isConnect() == true) { // 메타마스크 연결 확인
                    address = metamask.getAccount();
                    let connWallet = metamask.connectedWallet(address);
                    // userWallet.promise.then(userResult, failResult);
                    if(connWallet == false) {
                        swal("Please check the metamask address.",'','error');
                        return false;
                    } else {
                        // 지갑주소 저장
                        walletSave(address);
                    }

                } else {    // 연결한 메타마스크 없을 경우
                    metamask.connectWallet().then((addr) => {
                        result = walletSave(addr);
                    });
                }
            }
        }
        if(result == 1 || result == 5) {
            location.href = "/";
        }
    });
});

function addressClipboard(wid) {
    const valOfText = $('#wallet_'+wid).children('input[type="text"]');
    const textarea = document.createElement("textarea");
    document.body.appendChild(textarea);
    textarea.value = valOfText.val();
    textarea.select();
    document.execCommand("copy");
    document.body.removeChild(textarea);
    swal('Contract Address Copied!!','','success');
    $('.wallet-wrap').removeClass('on');
    $('.mywallet').removeClass('on');
}

function walletCheck(place, event) {
    let walletInfo;

    if(event) {
        walletInfo = $(event.target.parentNode.parentNode.parentNode.parentNode.nextSibling.nextSibling);
        if(place == "nav" && walletInfo.hasClass('on')) {
            walletInfo.removeClass('on');
            $('.mywallet').removeClass('on');
            return false;
        }
    }


    let method = "POST";
    let url = "/api/member/wallet/list";
    let param = {};
    let wResult = commonAjaxUrl(method, url, param);

    if(wResult.loginFlag == "N"){
        swal("Login to Use",'', 'error');
        return false;
    } else if(wResult.loginFlag == "Y" && wResult.walletFlag == "N") {
        if(place == "nav") {
            location.href="/member/mywallet";
            return;
        } else {
            return true;
        }
    } else {
        if(place == "result") {
            return wResult;
        }

        if(wResult.memberWalletResponsesList.length > 0) {
            walletRow(wResult.memberWalletResponsesList);
        }
        if(place == "nav") {
            if(walletInfo.hasClass('on')) {
                walletInfo.removeClass('on');
                $('.mywallet').removeClass('on');
            } else {
                walletInfo.addClass('on');
                $('.mywallet').addClass('on');
            }
            return true;
        } else if(place == "metamask") {
            swal("More than one wallet has already been linked.", '','info');
            location.href="/";
            return false;
        }
    }
}

function walletRow(dataRow) {
    let address = (userWallet.address)?userWallet.address:'';
    let balance_price = (userWallet.balance)?userWallet.balance:0;
    let walletHtml = '';
    $('.wallet-user-wrap .balance-price').text(balance_price);
    if(dataRow.length > 0) {
        $.each(dataRow, function(k,v){
            let isActive = (v.wcontractAddress == address)?' active':'';
            walletHtml += '<div class="wallet-row" id="wallet_'+v.wid+'" data-wid="'+v.wid+'">\n' +
                '                        <input type="text" style="display: none;" value="'+v.wcontractAddress+'"/>\n' +
                '                        <span class="address'+isActive+'">'+v.wcontractAddress+'</span>\n' +
                '                        <span class="wallet-address-copy"><img src="../img/icon/Copy_icon.png" class="whIs18" onclick="addressClipboard(\''+v.wid+'\')"></span>' +
                '                        <span class="wallet-address-delete"><img src="../img/icon/Delete_icon.png" class="whIs18" onclick="walletDelete('+v.wid+')"></span>' +
                '                    </div>';
        });
    }
    $('#navigation-top .wallet-wrap').find('.wallet-list').html(walletHtml);
}

function walletSave(address) {
    let result;
    let method = "POST";
    let url = "/api/member/wallet/save";
    let param = {"address":address};
    if(address != null) {
        result = commonAjaxUrl(method, url, param);
        if(result == 1 || result == 5) {
            swal("Wallet linkage is complete.",'','success');
        } else if(result == 2) {
            swal("Login to Use",'','error');
        } else if(result == 3) {
            swal("This is an already linked wallet.",'','info');
        } else if(result == 4) {
            swal("A wallet linked to another account.",'','info');
        } else if(result == 6) {
            swal("A wallet linked to another account.",'','info');
        } else {
            swal("Error",'','error');
        }

        return result;
    }
    return false;
}

async function walletDelete(wid) {
    let result;
    let method = "POST";
    let url = "/api/member/wallet/delete";
    let param = {"wid":wid};
    if(wid != null) {
        result = await commonAjaxUrl(method, url, param);
        $(`#wallet_${wid}.wallet-row`).remove();
        if($('.wallet-row').length === 0) {
            $('.wallet-wrap').removeClass('on');
            $('.mywallet').removeClass('on');
            location.href="/member/mywallet";
        }
    }
    return false;
}
