package kr.or.connect.production.service.impl;

import java.util.Date;
import kr.or.connect.production.dao.ReservationUserCommentDao;
import kr.or.connect.production.dao.ReservationUserCommentImageDao;
import kr.or.connect.production.dto.ReservationUserComment;
import kr.or.connect.production.dto.ReservationUserCommentImage;
import kr.or.connect.production.service.ReservationCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationCommentServiceImpl
  implements ReservationCommentService {
  @Autowired
  ReservationUserCommentDao reservationUserCommentDao;

  @Autowired
  ReservationUserCommentImageDao reservationUserCommentImageDao;

  @Override
  @Transactional(readOnly = false)
  public Long addReservationComment(
    ReservationUserComment reservationUserComment
  ) {
    reservationUserComment.setCreateDate(new Date());
    reservationUserComment.setModifyDate(new Date());
    Long reservationUserCommentId = reservationUserCommentDao.insertReservationUserComment(
      reservationUserComment
    );
    return reservationUserCommentId;
  }

  @Override
  @Transactional(readOnly = false)
  public Long addReservationCommentImageInfo(
    ReservationUserCommentImage reservationUserCommentImage
  ) {
    Long reservationUserCommentImageId = reservationUserCommentImageDao.insertReservationUserCommentImage(
      reservationUserCommentImage
    );
    return reservationUserCommentImageId;
  }
}
