package kr.or.connect.production.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.production.dao.ProductDao;
import kr.or.connect.production.dto.Product;
import kr.or.connect.production.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

  @Autowired 
  ProductDao productDao;
  
	@Override
	@Transactional
	public List<Product> productsAll(Integer start) {
		List<Product> list = productDao.selectProductAll(start, ProductService.LIMIT);
		return list;
	}
	
	@Override
	public List<Product> productByCategory(Integer start, Integer categoryId){
		List<Product> list = productDao.selectProductByCategory(start,ProductService.LIMIT,categoryId);
	
		return list;
		
	}

}