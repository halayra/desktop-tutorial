package com.BaiTapLab.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.BaiTapLab.Entity.Account;
import com.BaiTapLab.Entity.BaiViet;
import com.BaiTapLab.Repository.LuotTuongTacBaiVietRepo;

@Service
public class LuotTuongTacBaiVietService {
	@Autowired
    private LuotTuongTacBaiVietRepo repo;

    @Transactional
    public void huyLike(BaiViet baiViet, Account account) {
        repo.deleteByBaivietAndAccount(baiViet, account);
    }

    public int countByMaBVAndUsername(Integer maBV, String username) {
        return repo.countByMaBVAndUsername(maBV, username);
    }
    
    public int countLikesByMaBV(Integer maBV) {
        return repo.countLikesByMaBV(maBV);
    }
}
