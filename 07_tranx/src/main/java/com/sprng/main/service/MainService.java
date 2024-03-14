package com.sprng.main.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sprng.main.dao.MainDAO;

public class MainService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	public void insert() {
		MainDAO dao = new MainDAO();
		dao.insert();
		
	}
	
}












