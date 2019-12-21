package kr.or.connect.production.dao;

import static kr.or.connect.production.dao.ProductDaoSqls.SELECT_COUNT_ALL;
import static kr.or.connect.production.dao.ProductDaoSqls.SELECT_FILENAME_ALL;
import static kr.or.connect.production.dao.ProductDaoSqls.SELECT_FILENAME_BY_CATEGORY;
import static kr.or.connect.production.dao.ProductDaoSqls.SELECT_PRODUCT_ALL;
import static kr.or.connect.production.dao.ProductDaoSqls.SELECT_PRODUCT_BY_CATEGORY;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.production.dto.Product;

@Repository
public class ProductDao {
	 private static final String SELECT_COUNT_BY_CATEGORY = null;
	private NamedParameterJdbcTemplate jdbc;
	    private SimpleJdbcInsert insertAction;
	    private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);

	    public ProductDao(DataSource dataSource) {
	        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	        this.insertAction = new SimpleJdbcInsert(dataSource)
	                .withTableName("product")
	                .usingGeneratedKeyColumns("id");
	    }
	    
	    public List<Product> selectProductAll(Integer start, Integer limit) {
	    		Map<String, Integer> params = new HashMap<>();
	    		params.put("start", start);
	    		params.put("limit", limit);
	        return jdbc.query(SELECT_PRODUCT_ALL, params, rowMapper);
	        
	    } 
		public int selectCountAll() {
			return jdbc.queryForObject(SELECT_COUNT_ALL, Collections.emptyMap(), Integer.class);
		}
		
		public int selectCountByCategory(Integer categoryId) {
				Map<String, Integer> params = new  HashMap<>();
				params.put("categoryId",categoryId);
			return jdbc.queryForObject(SELECT_COUNT_BY_CATEGORY, params, Integer.class);
		}
		
		public List<Product> selectSaveFileNameAll(Integer start, Integer limit) {
				Map<String, Integer> params = new HashMap<>();
				params.put("start", start);
				params.put("limit", limit);
	        return jdbc.query(SELECT_FILENAME_ALL, params, rowMapper);
		}
		
		public List<Product> selectSaveFileNameByCategory(Integer start, Integer limit, Integer categoryId) {
	    		Map<String, Integer> params = new HashMap<>();
	    		params.put("start", start);
	    		params.put("limit", limit);
	    		params.put("categoryId",categoryId);
            return jdbc.query(SELECT_FILENAME_BY_CATEGORY, params, rowMapper);
		}
        
		// 카테고리별 상품리스트 구하기
        public List<Product> selectProductByCategory(Integer start, Integer limit, Integer categoryId) {
	    		Map<String, Integer> params = new HashMap<>();
	    		params.put("start", start);
	    		params.put("categoryId", categoryId);
	    		params.put("limit", limit);
            return jdbc.query(SELECT_PRODUCT_BY_CATEGORY, params, rowMapper);
    } 
    
}
