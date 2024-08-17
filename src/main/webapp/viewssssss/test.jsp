<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="
https://cdn.jsdelivr.net/npm/base-58@0.0.1/Base58.min.js
"></script>
<!-- Production (minified) -->
<script src="https://unpkg.com/@solana/web3.js@latest/lib/index.iife.min.js"></script>
</head>
<body>
    <button onclick="connectWallet()">CONNECT</button>
    <button onclick="mintNFT()">Mint NFT</button>
    <input type="file" id="fileInput" />
    <script>
        let publicKey;
        // Auto connect
        (async () =>{
            await window.phantom.solana.connect();
            publicKey = window.phantom.solana.publicKey.toBase58();
            console.log(publicKey);
        })();

        //Manual connect
        const connectWallet = async () =>{
            await window.phantom.solana.connect();

            publicKey = window.phantom.solana.publicKey.toBase58();
            console.log(publicKey);
        }
        

        const Pri_Key = "vfEUWHTGcqjMC08l"
        const toTransaction = (encodedTransaction) => solanaWeb3.Transaction.from(Uint8Array.from(atob(encodedTransaction), c => c.charCodeAt(0)));
        // Mint NFT
        const mintNFT = async () => {
            var myHeaders = new Headers();
            myHeaders.append("x-api-key", Pri_Key);

            const fileInput = document.querySelector("#fileInput"); 

            console.log(fileInput.files[0]);
            var formdata = new FormData();
            formdata.append("network", "devnet");
            formdata.append("wallet", publicKey);
            formdata.append("name", "SHYFT NFT");
            formdata.append("symbol", "SH");
            formdata.append("description", "Shyft makes Web3 so easy!");
            formdata.append("attributes", '[{"trait_type":"dev power","value":"over 900"}]');
            formdata.append("external_url", "https://shyft.to");
            formdata.append("max_supply", "0");
            formdata.append("royalty", "5");
            formdata.append("file", fileInput.files[0]);
            formdata.append("data", fileInput.files[0]);
            formdata.append("receiver", publicKey);
            /* formdata.append('service_charge', '{ "receiver": "499qpPLdqgvVeGvvNjsWi27QHpC8GPkPfuL5Cn2DtZJe",  "token": "DjMA5cCK95X333t7SgkpsG5vC9wMk7u9JV4w8qipvFE8",  "amount": 0.01}'); */
			formdata.append('service_charge', '{ "receiver": "499qpPLdqgvVeGvvNjsWi27QHpC8GPkPfuL5Cn2DtZJe", "amount": 0.01}');			
            var requestOptions = {
            method: 'POST',
            headers: myHeaders,
            body: formdata,
            redirect: 'follow'
            };

            fetch("https://api.shyft.to/sol/v2/nft/create", requestOptions)
            .then(async respone => {
                let res = await respone.json();
                let transaction = toTransaction(res.result.encoded_transaction);
                window.phantom.solana.signTransaction(transaction);
                const signedTransaction = await window.phantom.solana.signTransaction(transaction);
                const connection = new solanaWeb3.Connection("https://api.devnet.solana.com");
                const signature = await connection.sendRawTransaction(signedTransaction.serialize()); 
            })
            .catch(error => console.log('error', error));
            // .then(response => response.text())
            // .then(result => console.log(result))
        }
    </script>
</body>
</html>