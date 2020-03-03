package kr.or.connect.production.dao;

import static kr.or.connect.production.dao.CommentInfoDaoSqls.COUNT_COMMENT_INFO;
import static kr.or.connect.production.dao.CommentInfoDaoSqls.SELECT_COMMENT_INFO;
import static kr.or.connect.production.dao.CommentInfoDaoSqls.TOTAL_SCORE_OF_COMMENT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.production.dto.CommentInfo;

@Repository
public class CommentInfoDao{
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<CommentInfo> rowMapper = BeanPropertyRowMapper.newInstance(CommentInfo.class);
	
	public CommentInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("reservation_info")
				.usingGeneratedKeyColumns("id");
	}
	
	public List<CommentInfo> selectCommentInfo( Integer displayInfoId){
		List<CommentInfo> commentInfo = null ;
		Map<String,Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		
		if(jdbc.query(SELECT_COMMENT_INFO, params, rowMapper) != null) {
			commentInfo = jdbc.query(SELECT_COMMENT_INFO, params, rowMapper); 
		}
		return commentInfo ;
	}
	
	public int countCommentInfo(Integer displayInfoId) {
		 int countcommentInfo  = 0;
		 Map<String,Integer> params = new HashMap<>();
		 params.put("displayInfoId", displayInfoId);
		 
		 if(jdbc.queryForObject(COUNT_COMMENT_INFO, params, Integer.class) != null) {
			 countcommentInfo = jdbc.queryForObject(COUNT_COMMENT_INFO, params, Integer.class);
		 }
		 return countcommentInfo;
	}
	
	public double getcommenTotalScore(Integer displayInfoId) {
		 double commentTotlaScore = 0.0;
		 Map<String,Integer> params = new HashMap<>();
		 params.put("displayInfoId", displayInfoId);
		 
		 if(jdbc.queryForObject(TOTAL_SCORE_OF_COMMENT, params, Integer.class) != null) {
			 commentTotlaScore = jdbc.queryForObject(TOTAL_SCORE_OF_COMMENT, params, Integer.class);
		 }
		 return commentTotlaScore;
	}
}
