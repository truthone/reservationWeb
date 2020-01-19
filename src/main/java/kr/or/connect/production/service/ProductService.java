package kr.or.connect.production.service;

import java.util.List;

import kr.or.connect.production.dto.Product;

public interface ProductService {
	public static final Integer LIMIT = 4;
	public List<Product> productsAll(Integer start);
	public List<Product> productsByCategory (Integer start, Integer categoryId);
}
