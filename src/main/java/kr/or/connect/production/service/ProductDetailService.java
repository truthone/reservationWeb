package kr.or.connect.production.service;

import java.util.List;

import kr.or.connect.production.dto.CommentInfo;
import kr.or.connect.production.dto.DisplayImageInfo;
import kr.or.connect.production.dto.DisplayInfo;
import kr.or.connect.production.dto.ProductImageInfo;
import kr.or.connect.production.dto.ProductPriceInfo;

public interface ProductDetailService {
	public List<ProductImageInfo> getProductImageInfoList(Integer displayInfoId);
	List<DisplayInfo> getDisplayInfo(Integer displayInfoId);
	List<DisplayImageInfo> getDisplayImageInfoList(Integer displayInfoId);
	List<CommentInfo> getCommentInfo(Integer displayInfoId);
	int getCountCommentInfo(Integer displayInfoId);
	String getAverageScore (Integer displayInfoId);
	List<ProductImageInfo> getProductMainImage(Integer displayInfoId);
	List<ProductPriceInfo> getProductPriceInfo(Integer displayInfoId);
}
