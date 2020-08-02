package kr.or.connect.production.dto;

public class MyReservationPriceInfoAndCount {
	
	private Long reservationInfoId;
	private Long count;
	private String priceTypeName;
	private int price;
	private double discountRate;
	
	public Long getReservationInfoId() {
		return reservationInfoId;
	}
	public void setReservationInfoId(Long reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getPriceTypeName() {
		return priceTypeName;
	}
	public void setPriceTypeName(String priceTypeName) {
		this.priceTypeName = priceTypeName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public double getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}
	@Override
	public String toString() {
		return "MyReservationPriceInfoAndCount [reservationInfoId=" + reservationInfoId + ", count=" + count
				+ ", priceTypeName=" + priceTypeName + ", price=" + price + ", discountRate=" + discountRate + "]";
	}
	
	
 
}
