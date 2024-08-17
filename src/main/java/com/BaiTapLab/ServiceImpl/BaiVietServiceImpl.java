package com.BaiTapLab.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.BaiTapLab.DAO.BaiVietDAO;
import com.BaiTapLab.Service.BaiVietService;

public class BaiVietServiceImpl implements BaiVietService{
	@Autowired
	BaiVietDAO baivietDAO;


}
