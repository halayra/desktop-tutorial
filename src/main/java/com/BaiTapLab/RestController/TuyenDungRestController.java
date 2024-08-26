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

import com.BaiTapLab.DAO.TuyenDungDAO;
import com.BaiTapLab.Entity.Account;
import com.BaiTapLab.Entity.TuyenDung;
import com.BaiTapLab.Repository.AccountRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/tuyendung")
public class TuyenDungRestController {
	@Autowired
	TuyenDungDAO dao;
	
	@Autowired
	AccountRepository accountRepository;
	@GetMapping
	public List<TuyenDung> getMethodName() {
		return dao.findAll();
	}
	
	@PostMapping
	public TuyenDung dangbai(@RequestBody TuyenDung tuyendung) {
		// Kiểm tra và xử lý hình ảnh
	    if (tuyendung.getHinhAnh() != null && !tuyendung.getHinhAnh().isEmpty()) {
	        String imagePath = saveImageToStaticFolder(tuyendung.getHinhAnh());
	        tuyendung.setHinhAnh(imagePath); // Lưu đường dẫn hình ảnh vào entity
	    }
	    if (tuyendung.getAccount() == null || tuyendung.getAccount().getUsername() == null) {
	        Account account = accountRepository.findById(tuyendung.getAccount().getUsername()).orElse(null);
	        if (account == null) {
	            account = new Account();
	            account.setUsername(tuyendung.getAccount().getUsername());
	        }
	        tuyendung.setAccount(account);
	    }
	    // Lưu bài viết vào database
	    return dao.save(tuyendung);
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
}
