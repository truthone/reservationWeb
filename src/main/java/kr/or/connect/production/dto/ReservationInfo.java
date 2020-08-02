package kr.or.connect.production.dto;

import java.util.Date;
import java.util.List;

public class ReservationInfo {
	
	private Long reservationInfoId;
	private Long productId;
	private Long displayInfoId;
	private String reservationName;
	private String reservationTel;
	private String reservationEmail;
	private Date reservationDate;
	private int cancelFlag;
	private Date createDate;
	private Date modifyDate;
	private List<ReservationInfoPrice> reservationInfoPrices;
	
	public Date getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public List<ReservationInfoPrice> getReservationInfoPrices() {
		return reservationInfoPrices;
	}
	public void setReservationInfoPrices(List<ReservationInfoPrice> reservationInfoPrices) {
		this.reservationInfoPrices = reservationInfoPrices;
	}
	public Long getReservationInfoId() {
		return reservationInfoId;
	}
	public void setReservationInfoId(Long reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getDisplayInfoId() {
		return displayInfoId;
	}
	public void setDisplayInfoId(Long displayInfoId) {
		this.displayInfoId = displayInfoId;
	}
	public String getReservationName() {
		return reservationName;
	}
	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}
	public String getReservationTel() {
		return reservationTel;
	}
	public void setReservationTel(String reservationTel) {
		this.reservationTel = reservationTel;
	}
	public String getReservationEmail() {
		return reservationEmail;
	}
	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}
	public int getCancelFlag() {
		return cancelFlag;
	}
	public void setCancelFlag(int cancelFlag) {
		this.cancelFlag = cancelFlag;
	}


	@Override
	public String toString() {
		return "ReservationInfo [reservationInfoId=" + reservationInfoId + ", productId=" + productId
				+ ", displayInfoId=" + displayInfoId + ", reservationName=" + reservationName + ", reservationTel="
				+ reservationTel + ", reservationEmail=" + reservationEmail + ", reservationDate=" + reservationDate
				+ ", cancelFlag=" + cancelFlag + ", createDate=" + createDate + ", modifyDate=" + modifyDate
				+ ", reservationInfoPrices=" + reservationInfoPrices + "]";
	}
}
