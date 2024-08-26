document.addEventListener("DOMContentLoaded", () => {
  let network = "devnet";
  let address = "";
  let sendAddr = "";
  let nfts = [];
  let transferArr = [];

  const getNFTs = () => {
    const xKey = "vfEUWHTGcqjMC08l"; // Thêm X-API-KEY của bạn ở đây
    let nftUrl = `https://api.shyft.to/sol/v1/nft/read_all?network=${network}&address=${address}`;

    fetch(nftUrl, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        "x-api-key": xKey,
      },
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        if (data.success === true) {
          nfts = data.result;
          renderNFTs();
        }
      })
      .catch((err) => {
        console.warn(err);
      });
  };

  const renderNFTs = () => {
    const nftList = document.getElementById("nft-list");
    nftList.innerHTML = "";

    nfts.forEach((nft) => {
      const nftItem = document.createElement("div");
      nftItem.className = "nft-item";

      // Hiển thị tên NFT
      const nftName = document.createElement("p");
      nftName.textContent = nft.name; // Giả định rằng "name" là một thuộc tính trong dữ liệu NFT
      nftItem.appendChild(nftName);

      // Hiển thị hình ảnh NFT
      if (nft.image_uri) {
        // Giả định rằng "image_uri" là URL của hình ảnh trong dữ liệu NFT
        const nftImage = document.createElement("img");
        nftImage.src = nft.image_uri;
        nftImage.alt = nft.name;
        nftImage.className = "nft-image";
        nftItem.appendChild(nftImage);
      }

      nftList.appendChild(nftItem);
    });
  };

  document.getElementById("fetch-nfts").addEventListener("click", () => {
    address = document.getElementById("wallet-address").value;
    getNFTs();
  });
});
