package com.BaiTapLab.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BaiTapLab.DAO.BaiVietDAO;
import com.BaiTapLab.Entity.Account;
import com.BaiTapLab.Entity.BaiViet;
import com.BaiTapLab.Repository.AccountRepository;


@CrossOrigin("*")
@RestController()
@RequestMapping("/rest/baiviet")
public class BaiVietRestController {
	@Autowired
	BaiVietDAO dao;

	@Autowired
	AccountRepository accountRepository;


	@GetMapping
	public List<BaiViet> getALL() {
		List<BaiViet> baiViets = dao.findAll();
	    return baiViets;
	}


	@PostMapping
	public BaiViet postBai(@RequestBody BaiViet baiviet) {
		// Kiểm tra và xử lý hình ảnh
	    if (baiviet.getHinhAnh() != null && !baiviet.getHinhAnh().isEmpty()) {
	        String imagePath = saveImageToStaticFolder(baiviet.getHinhAnh());
	        baiviet.setHinhAnh(imagePath); // Lưu đường dẫn hình ảnh vào entity
	    }
	    if (baiviet.getAccount() == null || baiviet.getAccount().getUsername() == null) {
	        Account account = accountRepository.findById(baiviet.getAccount().getUsername()).orElse(null);
	        if (account == null) {
	            account = new Account();
	            account.setUsername(baiviet.getAccount().getUsername());
	        }
	        baiviet.setAccount(account);
	    }
	    // Lưu bài viết vào database
	    return dao.save(baiviet);
	}

	private String saveImageToStaticFolder(String base64Image) {
        try {
            // Kiểm tra và xử lý dữ liệu Base64
            String[] parts = base64Image.split(",");
            if (parts.length < 2) {
                throw new IllegalArgumentException("Invalid Base64 image format");
            }

            String base64Data = parts[1];
            String metadata = parts[0];
            String imageType = metadata.split(";")[0].split("/")[1]; // Ví dụ: "image/png" -> "png"

            // Chọn định dạng lưu hình ảnh
            String fileExtension;
            if (imageType.equals("png")) {
                fileExtension = "png";
            } else if (imageType.equals("jpeg") || imageType.equals("jpg")) {
                fileExtension = "jpg";
            } else {
                throw new IllegalArgumentException("Unsupported image type: " + imageType);
            }

            // Giải mã dữ liệu Base64
            byte[] decodedBytes = Base64.getDecoder().decode(base64Data);
            String fileName = UUID.randomUUID().toString() + "." + fileExtension; // Đổi tên file với định dạng phù hợp
            Path path = Paths.get("D:/UploadImg/" + fileName);

            // Ghi dữ liệu vào file
            Files.write(path, decodedBytes);

            return  fileName; // Trả về đường dẫn tương đối đến hình ảnh
        } catch (IOException e) {
            throw new RuntimeException("Error saving image", e);
        }
    }


//	@Autowired
//	AccountDAO dao;
//
//	@GetMapping
//	public List<Account> getAll(Model model){
//		return dao.findAll();
//	}
//
//	@GetMapping("{username}")
//	public Account getOne(@PathVariable("username") String username) {
//		return dao.findById(username).get();
//	}
//
//	@PostMapping
//	public Account save(@RequestBody Account account) {
//		dao.save(account);
//		return account;
//	}
//
//	@PutMapping("{username}")
//	public Account put(@PathVariable("username") String username, @RequestBody Account account) {
//		dao.save(account);
//		return account;
//	}
//
//	@DeleteMapping("{username}")
//	public void delete(@PathVariable("username") String username) {
//		dao.deleteById(username);
//	}
}
