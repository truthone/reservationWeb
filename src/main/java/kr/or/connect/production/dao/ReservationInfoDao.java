package kr.or.connect.production.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.production.dto.ReservationInfo;
import kr.or.connect.production.dto.ReservationInfoPrice;

@Repository
public class ReservationInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	
	public ReservationInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
			.withTableName("reservation_info")
			.usingGeneratedKeyColumns("id");
	}
	
	public Long insertReservationInfo(ReservationInfo reservationInfo) {
			SqlParameterSource params = new BeanPropertySqlParameterSource(reservationInfo);
			return insertAction.executeAndReturnKey(params).longValue();
	}
	
	public Long insertReservationInfoPrice(ReservationInfoPrice reservationInfoPrice) {
			SqlParameterSource params = new BeanPropertySqlParameterSource(reservationInfoPrice);
			return insertAction.executeAndReturnKey(params).longValue();
	}
}