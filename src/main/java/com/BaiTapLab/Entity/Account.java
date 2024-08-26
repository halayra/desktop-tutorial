package com.BaiTapLab.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Account")
public class Account {
	@Id
	String username;

	String password;
	String email;
	String role;
	String hinhAnh;
	String phone;
	String hovaten;

	/*Khi Jackson chuyển đổi một đối tượng Account thành JSON,
	field mà được gắn @JsonIgnore sẽ bị bỏ qua và không xuất hiện trong output JSON.
	Điều này hữu ích trong các trường hợp bạn muốn bảo vệ dữ liệu nhạy cảm như mật khẩu
	hoặc các thông tin riêng tư khác không nên được xuất ra JSON.*/
	@OneToMany(mappedBy = "account")
    @JsonIgnore
    List<Wallet> wallets;
	
//	@OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
//    @JsonIgnore
//    Wallet wallet;

	@OneToMany(mappedBy = "account")
	@JsonIgnore
	List<BaiViet> baiviets;
	
	@OneToMany(mappedBy = "account")
	@JsonIgnore
	List<TuyenDung> tuyendungs;
	
	@OneToMany(mappedBy = "account")
	@JsonIgnore
	List<LuotTuongTacBaiViet> luotTuongTacBaiViets;

}
