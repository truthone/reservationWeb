package kr.or.connect.production.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.production.dto.ReservationUserComment;

@Repository
public class ReservationUserCommentDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<ReservationUserComment> rowMapper = BeanPropertyRowMapper.newInstance(ReservationUserComment.class);
	
	public ReservationUserCommentDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
			.withTableName("reservation_user_comment")
			.usingGeneratedKeyColumns("id");
	}
	
	public Long insertReservationUserComment(ReservationUserComment reservationUserComment) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationUserComment);
		return insertAction.executeAndReturnKey(params).longValue();
	}
	
}
