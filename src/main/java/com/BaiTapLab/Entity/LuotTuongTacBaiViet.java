package com.BaiTapLab.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "LuotTuongTacBaiViet")
public class LuotTuongTacBaiViet {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	@ManyToOne
	@JoinColumn(name = "mabv")
	BaiViet baiviet;

	@ManyToOne
	@JoinColumn(name = "username")
	Account account;
}
