//package com.BaiTapLab.Entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.Table;
//import lombok.Data;
//import java.util.List;
//@Data
//@Entity
//@Table(name = "BinhLuan")
//public class BinhLuan {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	int id;
//
//	@ManyToOne
//	@JoinColumn(name = "maBV")
//	BaiViet baiviet;
//
//	@ManyToOne
//	@JoinColumn(name = "username")
//	Account account;
//
//	String noiDung;
//
//	String hinhAnh;
//
//	@JsonIgnore
//	@OneToMany (mappedBy = "luotLikeBinhLuan")
//	List<LuotLikeBinhLuan> luotLikeBinhLuans;
//}
