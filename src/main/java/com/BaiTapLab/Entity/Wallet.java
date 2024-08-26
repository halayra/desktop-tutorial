package com.BaiTapLab.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Wallet")
public class Wallet {
	@Id
	String maVi;

	Double soTien;

	@ManyToOne
    @JoinColumn(name = "username")
    Account account;
	
//	@OneToOne
//    @JoinColumn(name = "username")
//    Account account;
}
