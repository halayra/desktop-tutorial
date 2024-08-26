package com.BaiTapLab.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BaiTapLab.Entity.Account;
import com.BaiTapLab.Entity.BaiViet;
import com.BaiTapLab.Entity.LuotTuongTacBaiViet;
import com.BaiTapLab.Repository.LuotTuongTacBaiVietRepo;
import com.BaiTapLab.Service.LuotTuongTacBaiVietService;

@CrossOrigin("*")
@RestController()
@RequestMapping("/rest/luotTuongTacBaiViet")
public class LuotTuongTacBaiVietRestController {
	@Autowired
	LuotTuongTacBaiVietRepo repo;
	
	@Autowired
    private LuotTuongTacBaiVietService luotTuongTacBaiVietService;
	@GetMapping
	public List<LuotTuongTacBaiViet> getList(){
		return repo.findAll();
	}
	
	@PostMapping
	public LuotTuongTacBaiViet save(@RequestBody LuotTuongTacBaiViet luotTuongTac) {
		return repo.save(luotTuongTac);
	}
	
//	@PostMapping("/huyLike")
//	public void huyLike(@RequestBody LuotTuongTacBaiViet luotTuongTac) {
//	    // Lấy thông tin từ entity để xóa
//	    BaiViet baiViet = luotTuongTac.getBaiviet();
//	    Account account = luotTuongTac.getAccount();
//	    
//	    int likeCount = repo.countByMaBVAndUsername(baiViet.getMaBV(), account.getUsername());
//	    if (likeCount > 0) {
//	        repo.deleteByBaivietAndAccount(baiViet, account);
//	    }
//	}
	
	@PostMapping("/huyLike")
    public ResponseEntity<Void> huyLike(@RequestBody LuotTuongTacBaiViet luotTuongTac) {
        BaiViet baiViet = luotTuongTac.getBaiviet();
        Account account = luotTuongTac.getAccount();

        luotTuongTacBaiVietService.huyLike(baiViet, account);
        return ResponseEntity.ok().build();
    }

	@GetMapping("/getLikes")
	public ResponseEntity<Map<String, Object>> getLikes(@RequestParam Integer maBV, @RequestParam String username) {
	    // Lấy tổng số lượt like của bài viết
	    int totalLikes = luotTuongTacBaiVietService.countLikesByMaBV(maBV);
	    
	    // Kiểm tra xem tài khoản hiện tại đã like bài viết hay chưa
	    int likeCountByUser = luotTuongTacBaiVietService.countByMaBVAndUsername(maBV, username);
	    boolean isLiked = likeCountByUser > 0;
	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("likeCount", totalLikes);  // Trả về tổng số lượt like của bài viết
	    response.put("isLiked", isLiked);  // Trả về trạng thái like của tài khoản hiện tại
	    
	    return ResponseEntity.ok(response);
	}
}
