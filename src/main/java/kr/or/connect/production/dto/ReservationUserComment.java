package kr.or.connect.production.dto;

import java.util.Date;

public class ReservationUserComment {
  private Long productId;
  private Long reservationInfoId;
  private int score;
  private String comment;
  private Date createDate;
  private Date modifyDate;

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

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
}
