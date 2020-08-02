package kr.or.connect.production.dao;

import static kr.or.connect.production.dao.FileInfoDaoSqls.SELECT_FILE_INFO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.production.dto.FileInfo;


@Repository
public class FileInfoDao {

	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<FileInfo> rowMapper = BeanPropertyRowMapper.newInstance(FileInfo.class);
	
	public FileInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
			.withTableName("file_info")
			.usingGeneratedKeyColumns("id");
	}
	
	public Long insertFileInfo(FileInfo fileInfo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(fileInfo);
		return insertAction.executeAndReturnKey(params).longValue();
	}
	
	public List<FileInfo> getFileInfo(Long fileInfoId){
		
		Map<String,Long> params = new HashMap<>();
		params.put("fileInfoId", fileInfoId);
		
		
		return jdbc.query(SELECT_FILE_INFO, params, rowMapper);
	}
	
}
