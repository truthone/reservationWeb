package kr.or.connect.production.service.impl;

import java.util.List;
import kr.or.connect.production.dao.CommentInfoDao;
import kr.or.connect.production.dao.DisplayImageInfoDao;
import kr.or.connect.production.dao.DisplayInfoDao;
import kr.or.connect.production.dao.ProductImageInfoDao;
import kr.or.connect.production.dao.ProductPriceInfoDao;
import kr.or.connect.production.dto.CommentInfo;
import kr.or.connect.production.dto.DisplayImageInfo;
import kr.or.connect.production.dto.DisplayInfo;
import kr.or.connect.production.dto.ProductImageInfo;
import kr.or.connect.production.dto.ProductPriceInfo;
import kr.or.connect.production.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {
  @Autowired
  DisplayImageInfoDao displayImageInfoDao;

  @Autowired
  DisplayInfoDao displayInfoDao;

  @Autowired
  ProductImageInfoDao productImageInfoDao;

  @Autowired
  CommentInfoDao commentInfoDao;

  @Autowired
  ProductPriceInfoDao productPriceInfoDao;

  @Override
  public List<ProductImageInfo> getProductImageInfoList(Long displayInfoId) {
    List<ProductImageInfo> ProductImageInfoList = productImageInfoDao.selectProductImageInfo(
      displayInfoId
    );
    return ProductImageInfoList;
  }

  @Override
  public List<DisplayInfo> getDisplayInfo(Long displayInfoId) {
    return displayInfoDao.selectDisplayInfo(displayInfoId);
  }

  @Override
  public List<DisplayImageInfo> getDisplayImageInfoList(Long displayInfoId) {
    return displayImageInfoDao.selectDisplayImageInfo(displayInfoId);
  }

  @Override
  public List<CommentInfo> getCommentInfo(Long displayInfoId) {
    return commentInfoDao.selectCommentInfo(displayInfoId);
  }

  @Override
  public int getCountCommentInfo(Long displayInfoId) {
    return commentInfoDao.countCommentInfo(displayInfoId);
  }

  @Override
  public String getAverageScore(Long displayInfoId) {
    double averageScore = 0.0;
    double totalScore = commentInfoDao.getcommenTotalScore(displayInfoId);
    int totalCount = commentInfoDao.countCommentInfo(displayInfoId);

    averageScore = totalScore / totalCount;
    String averageScoreToString;

    if (Double.isNaN(averageScore)) {
      averageScoreToString = "0.0";
      return averageScoreToString;
    } else {
      averageScoreToString = String.format("%.1f", averageScore);
      return averageScoreToString;
    }
  }

  @Override
  public List<ProductImageInfo> getProductMainImage(Long displayInfoId) {
    return productImageInfoDao.selectProductMainImage(displayInfoId);
  }

  @Override
  public List<ProductPriceInfo> getProductPriceInfo(Long displayInfoId) {
    return productPriceInfoDao.selectProductPriceInfo(displayInfoId);
  }
}
