package kr.or.connect.production.dto;

public class ReservationUserCommentImage {
	private Long productId;
	private Long reservationInfoId;
	private Long reservationUserCommentId;
	private Long fileId;
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getReservationInfoId() {
		return reservationInfoId;
	}
	public void setReservationInfoId(Long reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}
	public Long getReservationUserCommentId() {
		return reservationUserCommentId;
	}
	public void setReservationUserCommentId(Long reservationUserCommentId) {
		this.reservationUserCommentId = reservationUserCommentId;
	}
	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	
	
}
