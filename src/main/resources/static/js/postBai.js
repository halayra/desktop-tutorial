// window.Buffer = buffer.Buffer;
let url = 'http://localhost:8080';
const receiverAddress = "7wfp6apUR8WfHmB4e4LGDqc8JfnuZiDR7NKFXpLzBsgR";
var app = angular.module("myApp", []);
    app.controller("myCtrl", function ($scope, $http, $timeout) {
    $scope.list = [];
    $scope.baiviet = {};
    

    $scope.baiviet.ngayDang = new Date();

    $scope.baiviet.username = username; // `username` từ script trên
    console.log("Username là: " + $scope.baiviet.username);
    $scope.baiviet.hinhAnh = imageName; // `imageName` từ script trên
    console.log("Hình ảnh là: " + $scope.baiviet.hinhAnh);
    
    console.log($scope.baiviet);

    $scope.baiviet.hovaten = hoten;
    console.log("Họ và tên: " + $scope.baiviet.hovaten);

    $scope.load = function () {
      $http.get(url + "/rest/baiviet")
        .then( response => {
          $scope.list = response.data;
          $scope.list.forEach(post => {
            if (post.hinhAnh) {
              post.hinhAnh = post.hinhAnh;
            }
          });
        })
        .catch( error =>{
          console.log(error);
        });
    };
    $scope.load();
    
    $scope.create = function () {
      $scope.baiviet.account  = { 
        username: username,
        hinhAnh: imageName,
        hovaten: hoten
       };
      $http.post(url + "/rest/baiviet", $scope.baiviet)
        .then( response => {
          $scope.list.push(response.data);
          $scope.baiviet = {};
          //window.location.href = '/home';
          // $scope.load();
        })
        .catch( error =>{
          console.log(error);
        });
    };


    $scope.uploadFile = function(files) {
      if (files.length > 0) {
        var file = files[0];
        var reader = new FileReader();
        
        reader.onload = function(e) {
          // Lưu dữ liệu Base64 vào baiviet.hinhAnh
          $scope.baiviet.hinhAnh = e.target.result;
          
          // Cập nhật giao diện sau khi thay đổi
          $scope.$apply(); // Sử dụng $applyAsync để tránh việc cập nhật quá thường xuyên
        };
        
        reader.readAsDataURL(file); // Đọc file dưới dạng Base64
      }
    };

  //   $scope.create = async function () {
  //     try {
  //         // Bắt đầu quá trình chuyển tiền
  //         let transactionStatus = await sendSolToWallet();
          
  //         if (transactionStatus) {
  //             // Nếu chuyển tiền thành công, tiến hành đăng bài
  //             $scope.baiviet.account = { username: username };
  //             $http.post(url + "/rest/baiviet", $scope.baiviet)
  //                 .then(response => {
  //                     $scope.list.push(response.data);
  //                     $scope.baiviet = {};
  //                     alert("Đăng bài thành công!");
  //                 })
  //                 .catch(error => {
  //                     console.log(error);
  //                     alert("Đăng bài thất bại. Vui lòng thử lại.");
  //                 });
  //         } else {
  //             alert("Giao dịch thất bại. Vui lòng thử lại.", transactionStatus);
  //         }
  //     } catch (error) {
  //         console.log(error);
  //         alert("Có lỗi xảy ra. Vui lòng thử lại.");
  //     }
  // };


    
  });

  // Kết nối ví
  var wallet;
  const network = "https://api.devnet.solana.com";

  function toggleWalletConnection() {
      if (wallet) {
          disconnectWallet();
      } else {
          connectWallet();
      }
  };

  async function connectWallet() {
      try {
          wallet = await window.solana.connect();
          console.log(wallet.publicKey.toString());
          document.getElementById("wallet_button").innerText = "Disconnected";
          

          const connection = new solanaWeb3.Connection(network);
          const walletBalance = await connection.getBalance(wallet.publicKey);
          const sol = walletBalance / solanaWeb3.LAMPORTS_PER_SOL;

          // Gửi dữ liệu đến backend
          saveWalletToBackend(wallet.publicKey.toString(), sol, username);

      } catch (err) {
          console.log(err);
      }
  }

  function disconnectWallet() {
      if (window.solana.disconnect) {
          window.solana.disconnect();
      }
      wallet = null;
      document.getElementById("wallet_button").innerText = "Connect Wallet";
      /*document.getElementById("status_text").innerText = "Disconnected";*/
  }

  function saveWalletToBackend(publicKey, balance, username) {
      const walletData = {
          maVi: publicKey,
          soTien: balance,
          account: {
              username: username
          }
      };

  // Cách 1: Sử dụng fetch
      fetch('http://localhost:8080/api/wallet/save', {
          method: 'POST',
          headers: {
              'Content-Type': 'application/json'
          },
          body: JSON.stringify(walletData)
      })
      .then(response => response.json())
      .then(data => {
          console.log('Success:', data);
      })
      .catch((error) => {
          console.error('Error:', error);
      });
  }

  // Gửi tiền 
//   async function sendSolToWallet() {
//     try {
//         let transactionStatus = await signInTransactionAndSendMoney(receiverAddress, 2);
//         return transactionStatus; // Trả về trạng thái giao dịch (thành công hoặc thất bại)
//     } catch (error) {
//         console.log("Lỗi trong quá trình chuyển tiền:", error);
//         return false;
//     }
// }

// async function signInTransactionAndSendMoney(destPubkeyStr, quantity) {
//   const network = "https://api.devnet.solana.com";
//   const connection = new solanaWeb3.Connection(network);
//   const transaction = new solanaWeb3.Transaction();

//   try {
//       const N_lamports = quantity * solanaWeb3.LAMPORTS_PER_SOL;
//       console.log(N_lamports);

//       console.log("starting sendMoney");
//       const destPubkey = new solanaWeb3.PublicKey(destPubkeyStr);

//       const instruction = solanaWeb3.SystemProgram.transfer({
//           fromPubkey: wallet.publicKey,
//           toPubkey: destPubkey,
//           lamports: N_lamports,
//       });
//       let trans = await setWalletTransaction(instruction, connection);

//       let signature = await signAndSendTransaction(wallet, trans, connection);
//       return signature; // Trả về trạng thái giao dịch
//   } catch (e) {
//       console.warn("Failed", e);
//       return false;
//   }
// }

// async function setWalletTransaction(instruction, connection) {
//   const transaction = new solanaWeb3.Transaction();
//   transaction.add(instruction);
//   transaction.feePayer = wallet.publicKey;
//   let hash = await connection.getRecentBlockhash();
//   console.log("blockhash", hash);
//   transaction.recentBlockhash = hash.blockhash;
//   return transaction;
// }

// async function signAndSendTransaction(wallet, transaction, connection) {
//   // Sign transaction, broadcast, and confirm
//   const { signature } = await window.solana.signAndSendTransaction(transaction);
//   await connection.confirmTransaction(signature);
//   return signature;
// }



