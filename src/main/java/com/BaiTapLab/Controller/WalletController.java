package com.BaiTapLab.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BaiTapLab.Entity.Wallet;
import com.BaiTapLab.Service.WalletService;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {
	@Autowired
    private WalletService walletService;

    @PostMapping("/save")
    public ResponseEntity<Wallet> saveWallet(@RequestBody Wallet wallet) {
        Wallet savedWallet = walletService.saveWallet(wallet);
        return ResponseEntity.ok(savedWallet);
    }
}
