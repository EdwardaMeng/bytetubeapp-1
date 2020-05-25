package com.bytetube.dao;

import com.bytetube.BaseTest;
import com.bytetube.entity.Area;
import com.bytetube.entity.PersonInfo;
import com.bytetube.entity.Shop;
import com.bytetube.entity.ShopCategory;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ShopDaoTest extends BaseTest {
	@Autowired
	private ShopDao shopDao;

	@Test
	public void testAInsertShop() throws Exception {
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
		shop.setShopName("mytest2");
		shop.setShopDesc("mytest2");
		shop.setShopAddr("testaddr2");
		shop.setPhone("111111");
		shop.setShopImg("test2");
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setEnableStatus(0);
		shop.setAdvice("审核中");

		int effectedNum = shopDao.insertShop(shop);
		assertEquals(1, effectedNum);
	}

	@Test
	public void testDUpdateShop() {
		Shop shop = new Shop();
		shop.setShopId(1L);
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		owner.setUserId(1L);
		area.setAreaId(2);
		shopCategory.setShopCategoryId(1L);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("mytest1");
		shop.setShopDesc("updateTest");
		shop.setShopAddr("updateTest");
		shop.setPhone("111111");
		shop.setShopImg("test1");
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setEnableStatus(0);
		shop.setAdvice("审核中");

		int effectedNum = shopDao.updateShop(shop);
		assertEquals(1, effectedNum);
	}

	@Test

	public void testQueryByShopId() {
		long shopId = 1;
		Shop shop = shopDao.queryByShopId(shopId);
		System.out.println("areaId:" + shop.getArea().getAreaId());
		System.out.println("areaName：" + shop.getArea().getAreaName());
	}


	@Test
	public void testQueryShopListAndCount() {
//		Shop shopCondition = new Shop();
//		ShopCategory childCategory = new ShopCategory();
//		ShopCategory parentCategory = new ShopCategory();
//		parentCategory.setShopCategoryId(12L);
//		childCategory.setParent(parentCategory);
//		shopCondition.setShopCategory(childCategory);
//		List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 6);
//		int count = shopDao.queryShopCount(shopCondition);
//		System.out.println("店铺列表的大小：" + shopList.size());
//		System.out.println("店铺总数：" + count);
		Shop shopCondition = new Shop();
		PersonInfo owner = new PersonInfo();
		owner.setUserId(1L);
		shopCondition.setOwner(owner);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 5);
		int count = shopDao.queryShopCount(shopCondition);
		System.out.println("店铺列表的大小：" + shopList.size());
		System.out.println("店铺总数：" + count);

	}



}
