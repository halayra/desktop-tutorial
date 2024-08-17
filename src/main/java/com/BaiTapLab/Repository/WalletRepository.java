package com.BaiTapLab.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BaiTapLab.Entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, String>{

}
