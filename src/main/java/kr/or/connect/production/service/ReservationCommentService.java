package kr.or.connect.production.service;

import kr.or.connect.production.dto.ReservationUserComment;
import kr.or.connect.production.dto.ReservationUserCommentImage;

public interface ReservationCommentService {
	Long addReservationComment(ReservationUserComment reservationUserComment);
	Long addReservationCommentImageInfo(ReservationUserCommentImage reservationUserCommentImage);
	
}
