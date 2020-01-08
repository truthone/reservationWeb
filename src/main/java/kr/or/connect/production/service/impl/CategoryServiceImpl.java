package kr.or.connect.production.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.production.dao.CategoryDao;
import kr.or.connect.production.dto.Category;
import kr.or.connect.production.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

  @Autowired 
  CategoryDao categoryDao;

@Override
public List<Category> categories(){
	return categoryDao.categories();
}

@Override
public int getCountAll() {
    return categoryDao.countAll();
}

@Override
public int getCountByCategory(Integer categoryId){
	List<Category> category =  categoryDao.countByCategory(categoryId);
	int count = category.get(0).getCount();
	return count;
}

}
