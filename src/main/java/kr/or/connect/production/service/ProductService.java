package kr.or.connect.production.service;

import java.util.List;

import kr.or.connect.production.dto.Product;

public interface ProductService {
	public static final Integer LIMIT = 4;
	public int getCountAll();
	public int getCountByCategory(Integer categoryId);
	public List<Product> productsAll(Integer start);
	public List<Product> productByCategory (Integer start, Integer categoryId);
	public List<Product> getSaveFileName(Integer start);
	public List<Product> getSaveFileNameByCategory(Integer start, Integer categoryId);
	
	
}
