const userWallet = {
    address : '',
    name : 'METAMASK',
    balance : 0
};

const webEth = {
    chainId : 0,
    connect : false,
};

const metamask = {
    isWallet (){
        if(typeof window.ethereum !== 'undefined'){
            return true
        }

        return false
    },
    beforeWalletCheck(){
        if(this.isDownload() === false){
            return false
        }

        return true
    },
    isDownload(){
        if(this.isWallet() === true)
            return true

        alert("메타마스크 지갑 설치후 사용하실수 있습니다")
        window.open("https://chrome.google.com/webstore/detail/metamask/nkbihfbeogaeaoehlefnkodbefgpgknn?hl=ko")
        return false
    },
    isConnect(){
        if(this.getAccount() === null || this.getAccount() === false || typeof this.getAccount() === 'undefined'){
            return false;
        }

        return true;
    },
    getAccount() {
        if(webEth.chainId === 1) {
            try{
                return ethereum.selectedAddress
            }catch (e){

            }
        }

        return false;
    },
    async connectWallet(){
        const accounts = await ethereum.enable()
        const account = accounts[0]

        return account;
    },
    async connectedWallet(address){
        if(address === null){
            return false
        }

        let balance = ((await this.getBalance(address)) * 0.0000000001 * 0.00000001).toFixed(4)
        try {
            userWallet.address = address;
            userWallet.balance = balance;
        } catch (e) {
            console.log("connectedWallet Error: " + e.message);
        }

        return true;
    },
    async getBalance(address){
        if(address == null) {
            return false;
        }
        const accounts = await ethereum.request({
            jsonrpc: "2.0",
            method: 'eth_getBalance',
            params : [address,"latest"]
        });

        return parseInt(accounts,16)
    },
    async sign(message){    // 서명 함수
        const address = this.getAccount()
        try {
            return await ethereum.request({
                method: 'personal_sign',
                params : [message,address],
                from : address
            });
        }catch (e) {
            console.log("sign Error: " + e.message);
        }
        return false
    }
}

function handleAccountsChanged(accounts) {

}
