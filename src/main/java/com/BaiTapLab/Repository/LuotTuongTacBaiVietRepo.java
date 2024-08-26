package com.BaiTapLab.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.BaiTapLab.Entity.Account;
import com.BaiTapLab.Entity.BaiViet;
import com.BaiTapLab.Entity.LuotTuongTacBaiViet;

public interface LuotTuongTacBaiVietRepo extends JpaRepository<LuotTuongTacBaiViet, Integer>{
	@Query("SELECT COUNT(l) FROM LuotTuongTacBaiViet l WHERE l.baiviet.maBV = :maBV AND l.account.username = :username")
    int countByMaBVAndUsername(@Param("maBV") Integer maBV, @Param("username") String username);

    void deleteByBaivietAndAccount(BaiViet baiviet, Account account);
    
    @Query("SELECT COUNT(l) FROM LuotTuongTacBaiViet l WHERE l.baiviet.maBV = :maBV")
    int countLikesByMaBV(@Param("maBV") Integer maBV);
}
