package com.BaiTapLab.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BaiTapLab.Entity.Account;
import com.BaiTapLab.Repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	AccountRepository accountRepository;

	public List<Account> getAll() {
		return accountRepository.findAll();
	}

	public Optional<Account> findByUsernameAndPassword(String username, String password) {
		return accountRepository.findByUsernameAndPassword(username, password);
	}
}
