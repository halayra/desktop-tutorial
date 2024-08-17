package com.BaiTapLab.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BaiTapLab.Entity.Account;

public interface AccountRepository extends JpaRepository<Account, String>{
	Optional<Account> findByUsernameAndPassword(String username, String password);

	Account findByUsername(String username);
}
