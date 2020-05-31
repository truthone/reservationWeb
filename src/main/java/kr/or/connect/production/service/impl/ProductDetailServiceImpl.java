package kr.or.connect.production.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class ProductDetailServiceImpl implements ProductDetailService  {

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
	public List<ProductImageInfo> getProductImageInfoList(Integer displayInfoId) {
		List<ProductImageInfo>  ProductImageInfoList = productImageInfoDao.selectProductImageInfo(displayInfoId);
		return ProductImageInfoList;
	}

	@Override
	public List<DisplayInfo> getDisplayInfo(Integer displayInfoId) {
			return displayInfoDao.selectDisplayInfo(displayInfoId);
	}

	@Override
	public List<DisplayImageInfo> getDisplayImageInfoList(Integer displayInfoId) {
		return displayImageInfoDao.selectDisplayImageInfo(displayInfoId);
	}

	@Override
	public List<CommentInfo> getCommentInfo(Integer displayInfoId){
		return commentInfoDao.selectCommentInfo(displayInfoId);
	}
	
	@Override
	public int getCountCommentInfo(Integer displayInfoId) {
		return commentInfoDao.countCommentInfo(displayInfoId);
	}
	
	@Override
	public String getAverageScore (Integer displayInfoId) {
		double averageScore = 0.0;
		double totalScore = commentInfoDao.getcommenTotalScore(displayInfoId);
		int totalCount = commentInfoDao.countCommentInfo(displayInfoId);
		
		averageScore = totalScore / totalCount;
		String averageScoreToString;
		
		if(Double.isNaN(averageScore)) {
			averageScoreToString = "0.0";
			return averageScoreToString;
		}else {
			averageScoreToString = String.format("%.1f",averageScore);
			return averageScoreToString;
		}		
	}

	@Override
	public List<ProductImageInfo> getProductMainImage(Integer displayInfoId){
		return productImageInfoDao.selectProductMainImage(displayInfoId);
	}
	
	@Override
	public List<ProductPriceInfo> getProductPriceInfo(Integer displayInfoId){
		return productPriceInfoDao.selectProductPriceInfo(displayInfoId);
	}
}
