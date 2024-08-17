<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://unpkg.com/@solana/web3.js@latest/lib/index.iife.js"></script>
    <title>Document</title>
</head>
<body>
    <button type="button" id="connect_button" onclick="connectWallet();">Connect</button>
    <p>Kết nối với wallet</p>
    <p id="status_p">Status: <span id="status_text"></span></p>

    <button type="button" id="getBalance_button" onclick="getBalanceInWallet();">Get balance</button>
    <p>Lấy thông tin số dư trong wallet</p>

    <button type="button" id="getSOL_button" onclick="requestFreeSOL(5);">Request Airdrop SOL</button>
    <p>Thêm Free SOL vào wallet, bằng Request Airdrop<br>
        Hiện tại bị giới hạn số lượng connection, và chưa hoàn thiện code để xử lý giới hạn
    </p>
    
</body>
</html>


<script>
    var wallet;

    const network = "https://api.devnet.solana.com";
    // const network = "https://api.mainnet-beta.solana.com";

    //Kết nối với wallet
    function connectWallet() {
        (async () => {
            try {
                wallet = await window.solana.connect();
                console.log(wallet.publicKey.toString());
                
                // walletAccountInfo = await connection.getAccountInfo(wallet.publicKey);
                // console.log(walletAccountInfo);

            } catch (err) {
                console.log(err);
            }
        })();
        window.solana.on(
            "connect",
            () => (
                document.getElementById("status_text").innerText = "Connected"
            )
        );
    }

    //Lấy thông tin số dư trong wallet
    function getBalanceInWallet() {
        console.log(wallet.publicKey.toString());

        (async () => {
            const connection = new solanaWeb3.Connection(network);
            const walletBalance = await connection.getBalance(wallet.publicKey);
            // Converting lamport to sol
            const sol = walletBalance / solanaWeb3.LAMPORTS_PER_SOL;
            console.log(`Wallet Balance: ${sol} sol`);
        })();
    }

    //Request Airdrop SOL 
    function requestFreeSOL(nSOL) {
        const lamports = nSOL * solanaWeb3.LAMPORTS_PER_SOL;
        console.log(lamports);

        (async () => {
            const connection = new solanaWeb3.Connection(network);
            const airDropSignature = await connection.requestAirdrop(
                wallet.publicKey, 
                lamports
            );
            await connection.confirmTransaction(airDropSignature);
        })();
    }

</script>