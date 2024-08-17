package com.BaiTapLab.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BaiTapLab.Entity.Account;
import com.BaiTapLab.Entity.Wallet;
import com.BaiTapLab.Repository.AccountRepository;
import com.BaiTapLab.Repository.WalletRepository;

@Service
public class WalletService {
	@Autowired
    private WalletRepository walletRepository;

    @Autowired
    private AccountRepository accountRepository;

//    public Wallet saveWallet(Wallet wallet) {
//        // Kiểm tra xem Account có tồn tại không
//        Account account = accountRepository.findById(wallet.getAccount().getUsername())
//                .orElseThrow(() -> new RuntimeException("Account not found"));
//        wallet.setAccount(account);
//        return walletRepository.save(wallet);
//    }

    public Wallet saveWallet(Wallet wallet) {
        // Kiểm tra xem Account có tồn tại không
        Optional<Account> accountOpt = accountRepository.findById(wallet.getAccount().getUsername());
        if (!accountOpt.isPresent()) {
            throw new RuntimeException("Account with username " + wallet.getAccount().getUsername() + " not found");
        }
        Account account = accountOpt.get();
        wallet.setAccount(account);
        return walletRepository.save(wallet);
    }
}
