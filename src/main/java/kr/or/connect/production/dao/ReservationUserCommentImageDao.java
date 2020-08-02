package kr.or.connect.production.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.production.dto.ReservationUserCommentImage;

@Repository
public class ReservationUserCommentImageDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<ReservationUserCommentImageDao> rowMapper = BeanPropertyRowMapper.newInstance(ReservationUserCommentImageDao.class);
	
	public ReservationUserCommentImageDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
			.withTableName("reservation_user_comment_image")
			.usingGeneratedKeyColumns("id");
	}
	
	public Long insertReservationUserCommentImage(ReservationUserCommentImage reservationUserCommentImage) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationUserCommentImage);
		return insertAction.executeAndReturnKey(params).longValue();
	}
	
}
