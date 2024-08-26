//  var wallet;
//     const network = "https://api.devnet.solana.com";

//     function toggleWalletConnection() {
//         if (wallet) {
//             disconnectWallet();
//         } else {
//             connectWallet();
//         }
//     }

//     async function connectWallet() {
//         try {
//             wallet = await window.solana.connect();
//             console.log(wallet.publicKey.toString());
//             document.getElementById("wallet_button").innerText = "Disconnected";

//             const connection = new solanaWeb3.Connection(network);
//             const walletBalance = await connection.getBalance(wallet.publicKey);
//             const sol = walletBalance / solanaWeb3.LAMPORTS_PER_SOL;

//             // Gửi dữ liệu đến backend
//             saveWalletToBackend(wallet.publicKey.toString(), sol, username);

//         } catch (err) {
//             console.log(err);
//         }
//     }

//     function disconnectWallet() {
//         if (window.solana.disconnect) {
//             window.solana.disconnect();
//         }
//         wallet = null;
//         document.getElementById("wallet_button").innerText = "Connect Wallet";
//         /*document.getElementById("status_text").innerText = "Disconnected";*/
//     }

//     function saveWalletToBackend(publicKey, balance, username) {
//         const walletData = {
//             maVi: publicKey,
//             soTien: balance,
//             account: {
//                 username: username
//             }
//         };

// 		// Cách 1: Sử dụng fetch
//         fetch('http://localhost:8080/api/wallet/save', {
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/json'
//             },
//             body: JSON.stringify(walletData)
//         })
//         .then(response => response.json())
//         .then(data => {
//             console.log('Success:', data);
//         })
//         .catch((error) => {
//             console.error('Error:', error);
//         });

        // Cách 2: Sử dụng axios
        /*axios.post('http://localhost:8080/api/wallet/save', walletData, {
		  headers: {
		    'Content-Type': 'application/json'
		  }
		})
		.then(response => {
		  console.log('Thành công:', response.data);
		})
		.catch(error => {
		  console.error('Lỗi:', error);
		});*/
    //}
// const receiverAddress = "7wfp6apUR8WfHmB4e4LGDqc8JfnuZiDR7NKFXpLzBsgR";
// async function sendSolToWallet(){
//     await signInTransactionAndSendMoney(receiverAddress, 2);
// }

//   function signInTransactionAndSendMoney(destPubkeyStr, quantity) {
//     (async () => {
//         const network = "https://api.devnet.solana.com";
//         const connection = new solanaWeb3.Connection(network);
//         const transaction = new solanaWeb3.Transaction();

//         try {
//             const N_lamports = quantity * solanaWeb3.LAMPORTS_PER_SOL;
//             console.log(N_lamports);

//             console.log("starting sendMoney");
//             const destPubkey = new solanaWeb3.PublicKey(destPubkeyStr);

//             const instruction = solanaWeb3.SystemProgram.transfer({
//                 fromPubkey: wallet.publicKey,
//                 toPubkey: destPubkey,
//                 lamports: N_lamports,
//             });
//             let trans = await setWalletTransaction(instruction, connection);

//             let signature = await signAndSendTransaction(
//                 wallet,
//                 trans,
//                 connection
//             );
//         }
//         catch (e) {
//             console.warn("Failed", e);
//         }
//     })();

//     async function setWalletTransaction(instruction, connection) {
//         const transaction = new solanaWeb3.Transaction();
//         transaction.add(instruction);
//         transaction.feePayer = wallet.publicKey;
//         let hash = await connection.getRecentBlockhash();
//         console.log("blockhash", hash);
//         transaction.recentBlockhash = hash.blockhash;
//         return transaction;
//     }

//     async function signAndSendTransaction(wallet, transaction, connection) {
//         // Sign transaction, broadcast, and confirm
//         const { signature } = await window.solana.signAndSendTransaction(
//             transaction
//         );
//         await connection.confirmTransaction(signature);
//         return signature;
//     }
// }
