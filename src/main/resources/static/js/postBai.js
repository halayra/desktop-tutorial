// window.Buffer = buffer.Buffer;
let url = "http://localhost:8080";
const receiverAddress = "7wfp6apUR8WfHmB4e4LGDqc8JfnuZiDR7NKFXpLzBsgR";
var app = angular.module("myApp", []);
app.controller("myCtrl", function ($scope, $http, $timeout) {
  $scope.list = [];
  $scope.tuongTacBaiVietList = [];
  $scope.baiviet = {};

  $scope.username = username; // Đảm bảo gán giá trị từ server
  console.log("Username từ scope: " + $scope.username);

  $scope.baiviet.ngayDang = new Date();

  $scope.baiviet.username = username; // `username` từ script trên
  console.log("Username là: " + $scope.baiviet.username);
  $scope.baiviet.hinhAnh = imageName; // `imageName` từ script trên
  console.log("Hình ảnh là: " + $scope.baiviet.hinhAnh);

  console.log($scope.baiviet);

  $scope.baiviet.hovaten = hoten;
  console.log("Họ và tên: " + $scope.baiviet.hovaten);

  // $scope.load = function () {
  //   $http
  //     .get(url + "/rest/baiviet")
  //     .then((response) => {
  //       $scope.list = response.data;
  //       $scope.list.forEach((post) => {
  //         if (post.hinhAnh) {
  //           post.hinhAnh = post.hinhAnh;
  //         }
  //         post.isLiked = $scope.tuongTacBaiVietList.some(
  //           (t) =>
  //             t.baiviet.maBV === post.maBV &&
  //             t.account.username === $scope.username
  //         );
  //         post.likeCount = Number(post.likeCount) || 0;
  //         $scope.list.forEach((post) => {
  //           // Lấy giá trị likeCount và isLiked từ cơ sở dữ liệu
  //           $scope.loadLikes(post);
  //         });
  //       });
  //     })
  //     .catch((error) => {
  //       console.log(error);
  //     });
  // };
  $scope.load = function () {
    $http
      .get(url + "/rest/baiviet")
      .then((response) => {
        $scope.list = response.data;
        $scope.list.forEach((post) => {
          $scope.loadLikes(post); // Cập nhật số lượt like từ API
        });
      })
      .catch((error) => {
        console.log(error);
      });
  };
  $scope.load();

  $scope.create = function () {
    $scope.baiviet.account = {
      username: username,
      hinhAnh: imageName,
      hovaten: hoten,
    };
    $http
      .post(url + "/rest/baiviet", $scope.baiviet)
      .then((response) => {
        $scope.list.push(response.data);
        $scope.baiviet = {};
        //window.location.href = '/home';
        // $scope.load();
      })
      .catch((error) => {
        console.log(error);
      });
  };

  $scope.uploadFile = function (files) {
    if (files.length > 0) {
      var file = files[0];
      var reader = new FileReader();

      reader.onload = function (e) {
        // Lưu dữ liệu Base64 vào baiviet.hinhAnh
        $scope.baiviet.hinhAnh = e.target.result;

        // Cập nhật giao diện sau khi thay đổi
        $scope.$apply(); // Sử dụng $applyAsync để tránh việc cập nhật quá thường xuyên
      };

      reader.readAsDataURL(file); // Đọc file dưới dạng Base64
    }
  };

  $scope.toggleLike = function (post) {
    if (post.isLiked) {
      // Hủy thích bài viết
      $http
        .post(url + "/rest/luotTuongTacBaiViet/huyLike", {
          baiviet: { maBV: post.maBV },
          account: { username: $scope.username },
        })
        .then((response) => {
          post.isLiked = false;
          // post.likeCount--;
          post.likeCount = Math.max(0, post.likeCount - 1);
          $scope.tuongTacBaiVietList = $scope.tuongTacBaiVietList.filter(
            (t) =>
              !(
                t.baiviet.maBV === post.maBV &&
                t.account.username === $scope.username
              )
          );
        })
        .catch((error) => {
          console.log(error);
        });
    } else {
      // Thích bài viết
      $http
        .post(url + "/rest/luotTuongTacBaiViet", {
          baiviet: { maBV: post.maBV },
          account: { username: $scope.username },
        })
        .then((response) => {
          post.isLiked = true;
          //post.likeCount++;
          post.likeCount = (post.likeCount || 0) + 1;
          $scope.tuongTacBaiVietList.push(response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  };

  $scope.loadTuongTacBaiViet = function () {
    $http.get(url + "/rest/luotTuongTacBaiViet").then((response) => {
      $scope.tuongTacBaiVietList = response.data;
    });
  };
  $scope.loadTuongTacBaiViet();

  $scope.loadLikes = function (post) {
    $http
      .get("/rest/luotTuongTacBaiViet/getLikes", {
        params: {
          maBV: post.maBV,
          username: $scope.username,
        },
      })
      .then((response) => {
        post.likeCount = response.data.likeCount; // Hiển thị tổng số lượt like
        post.isLiked = response.data.isLiked; // Hiển thị trạng thái like của tài khoản hiện tại
      })
      .catch((error) => {
        console.log(error);
      });
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
let wallet;
const network = "https://api.devnet.solana.com";

function toggleWalletConnection() {
  if (wallet) {
    disconnectWallet();
  } else {
    connectWallet();
  }
}

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
      username: username,
    },
  };

  // Kiểm tra dữ liệu trước khi gửi
  console.log("Data to send trước khi send:", walletData);
  console.log("Data to send (JSON) sau khi send:", JSON.stringify(walletData));
  // Cách 1: Sử dụng fetch
  fetch("http://localhost:8080/api/wallet/save", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(walletData),
  })
    .then((response) => response.json())
    .then((data) => {
      console.log("Success:", data);
    })
    .catch((error) => {
      console.error("Error:", error);
    });
}

// // Liệt kê danh sách
// document.addEventListener("DOMContentLoaded", () => {
//   let network = "devnet";
//   let address = "";
//   let sendAddr = "";
//   let nfts = [];
//   let transferArr = [];

//   const getNFTs = () => {
//     const xKey = "vfEUWHTGcqjMC08l"; // Thêm X-API-KEY của bạn ở đây
//     let nftUrl = `https://api.shyft.to/sol/v1/nft/read_all?network=${network}&address=${address}`;

//     fetch(nftUrl, {
//       method: "GET",
//       headers: {
//         "Content-Type": "application/json",
//         "x-api-key": xKey,
//       },
//     })
//       .then((response) => response.json())
//       .then((data) => {
//         console.log(data);
//         if (data.success === true) {
//           nfts = data.result;
//           renderNFTs();
//         }
//       })
//       .catch((err) => {
//         console.warn(err);
//       });
//   };

//   const renderNFTs = () => {
//     const nftList = document.getElementById("nft-list");
//     nftList.innerHTML = "";

//     nfts.forEach((nft) => {
//       const nftItem = document.createElement("div");
//       nftItem.className = "nft-item";

//       // Hiển thị tên NFT
//       const nftName = document.createElement("p");
//       nftName.textContent = nft.name; // Giả định rằng "name" là một thuộc tính trong dữ liệu NFT
//       nftItem.appendChild(nftName);

//       // Hiển thị hình ảnh NFT
//       if (nft.image_uri) {
//         // Giả định rằng "image_uri" là URL của hình ảnh trong dữ liệu NFT
//         const nftImage = document.createElement("img");
//         nftImage.src = nft.image_uri;
//         nftImage.alt = nft.name;
//         nftImage.className = "nft-image";
//         nftItem.appendChild(nftImage);
//       }

//       nftList.appendChild(nftItem);
//     });
//   };

//   document.getElementById("fetch-nfts").addEventListener("click", () => {
//     //address = document.getElementById("wallet-address").value;
//     const publicKeyString = wallet.publicKey.toString();

//     // Gán giá trị publicKey vào ô input có ID "wallet-address"
//     document.getElementById("wallet-address").value = publicKeyString;

//     // In ra giá trị của publicKey để kiểm tra
//     console.log(publicKeyString);
//     getNFTs();
//   });
// });

// Gửi tiền
async function sendSolToWallet() {
  try {
    let transactionStatus = await signInTransactionAndSendMoney(
      receiverAddress,
      2
    );
    return transactionStatus; // Trả về trạng thái giao dịch (thành công hoặc thất bại)
  } catch (error) {
    console.log("Lỗi trong quá trình chuyển tiền:", error);
    return false;
  }
}

async function signInTransactionAndSendMoney(destPubkeyStr, quantity) {
  const network = "https://api.devnet.solana.com";
  const connection = new solanaWeb3.Connection(network);
  const transaction = new solanaWeb3.Transaction();

  try {
    const N_lamports = quantity * solanaWeb3.LAMPORTS_PER_SOL;
    console.log(N_lamports);

    console.log("starting sendMoney");
    const destPubkey = new solanaWeb3.PublicKey(destPubkeyStr);

    const instruction = solanaWeb3.SystemProgram.transfer({
      fromPubkey: wallet.publicKey,
      toPubkey: destPubkey,
      lamports: N_lamports,
    });
    let trans = await setWalletTransaction(instruction, connection);

    let signature = await signAndSendTransaction(wallet, trans, connection);
    return signature; // Trả về trạng thái giao dịch
  } catch (e) {
    console.warn("Failed", e);
    return false;
  }
}

async function setWalletTransaction(instruction, connection) {
  const transaction = new solanaWeb3.Transaction();
  transaction.add(instruction);
  transaction.feePayer = wallet.publicKey;
  let hash = await connection.getRecentBlockhash();
  console.log("blockhash", hash);
  transaction.recentBlockhash = hash.blockhash;
  return transaction;
}

async function signAndSendTransaction(wallet, transaction, connection) {
  // Sign transaction, broadcast, and confirm
  const { signature } = await window.solana.signAndSendTransaction(transaction);
  await connection.confirmTransaction(signature);
  return signature;
}
