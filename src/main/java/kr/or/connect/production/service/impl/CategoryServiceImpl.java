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
	return categoryDao.countByCategory();
}

@Override
public int getCountAll() {
	List<Category> categories = categoryDao.countByCategory();
	int countAll= 0;
	for (int i = 0; i < categories.size(); i++) {
		
		countAll += categories.get(i).getCount();
	}
	
	return countAll;
}

@Override
public int getCountByCategory(int categoryId){
	
	List<Category> categories = categoryDao.countByCategory();
	int count = 0; 
	count = categories.get(categoryId-1).getCount();
	
	return count;
}

}
