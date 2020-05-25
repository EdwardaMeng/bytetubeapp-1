package com.bytetube.service;

import com.bytetube.BaseTest;
import com.bytetube.dto.ShopExecution;
import com.bytetube.entity.Area;
import com.bytetube.entity.PersonInfo;
import com.bytetube.entity.Shop;
import com.bytetube.entity.ShopCategory;
import com.bytetube.enums.ShopStateEnum;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopServiceTest extends BaseTest {
	@Autowired
	private ShopService shopService;


	@Test
	public void testGetShopList() {
		Shop shopCondition = new Shop();
		ShopCategory sc = new ShopCategory();
		sc.setShopCategoryId(1L);
		shopCondition.setShopCategory(sc);
		ShopExecution se = shopService.getShopList(shopCondition, 2, 2);
		System.out.println("店铺列表数为：" + se.getShopList().size());
		System.out.println("店铺总数为：" + se.getCount());
	}
	@Test
	public void testAddShop() throws Exception {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		owner.setUserId(1L);
		area.setAreaId(2);
		shopCategory.setShopCategoryId(1L);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("servicetest1");
		shop.setShopDesc("servicetest1");
		shop.setShopAddr("testaddr1");
		shop.setPhone("111111");
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");

		File file = new File("/Users/dalluo/Desktop/images/logo.jpg");
		FileItem fileItem = new DiskFileItem("mainFile", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length(), file.getParentFile());

		try {
			InputStream input = new FileInputStream(file);
			OutputStream os = fileItem.getOutputStream();
			IOUtils.copy(input, os);
			// Or faster..
			// IOUtils.copy(new FileInputStream(file), fileItem.getOutputStream());
		} catch (IOException ex) {
			// do something.
		}

		CommonsMultipartFile shopImg = new CommonsMultipartFile(fileItem);


		shopService.addShop(shop,shopImg);


	}

	@Test

	public void testModifyShop() throws IOException {
		Shop shop = new Shop();
		shop.setShopId(1L);
		shop.setShopName("修改后的店铺名称");
		File file = new File("/Users/dalluo/Desktop/images/logo3.jpeg");
		FileItem fileItem = new DiskFileItem("mainFile", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length(), file.getParentFile());

		try {
			InputStream input = new FileInputStream(file);
			OutputStream os = fileItem.getOutputStream();
			IOUtils.copy(input, os);
			// Or faster..
			// IOUtils.copy(new FileInputStream(file), fileItem.getOutputStream());
		} catch (IOException ex) {
			// do something.
		}

		CommonsMultipartFile shopImg = new CommonsMultipartFile(fileItem);
		ShopExecution shopExecution = shopService.modifyShop(shop, shopImg);
		System.out.println("新的图片地址为：" + shopExecution.getShop().getShopImg());
	}





}
