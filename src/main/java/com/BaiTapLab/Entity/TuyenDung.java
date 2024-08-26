package com.BaiTapLab.Entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "TuyenDung")
public class TuyenDung {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer maTD;
	
	String tieuDe;
	String noiDung;
	String hinhAnh;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "ngayDang")
	Date ngayDang = new Date();
	
	@ManyToOne
	@JoinColumn(name = "username")
	Account account;
	
	String maViTuyenDung;
	
	
}
