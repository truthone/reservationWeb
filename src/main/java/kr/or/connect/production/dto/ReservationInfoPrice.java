package kr.or.connect.production.dto;

public class ReservationInfoPrice {
	
	private Long reservationInfoPriceId; 
	private Long reservationInfoId;
	private Long productPriceId;
	private int count;
	
	
	public Long getReservationInfoPriceId() {
		return reservationInfoPriceId;
	}
	public void setReservationInfoPriceId(Long id) {
		this.reservationInfoPriceId = id;
	}
	public Long getReservationInfoId() {
		return reservationInfoId;
	}
	public void setReservationInfoId(Long reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}
	public Long getProductPriceId() {
		return productPriceId;
	}
	public void setProductPriceId(Long productPriceId) {
		this.productPriceId = productPriceId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
