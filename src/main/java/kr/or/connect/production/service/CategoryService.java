package kr.or.connect.production.service;

import java.util.List;
import kr.or.connect.production.dto.Category;

public interface CategoryService {
  public static final Integer LIMIT = 4;

  public List<Category> categories();

  public int getCountProductsAll();

  public int getCountProductsByCategory(Integer categoryId);
}
