package kr.or.connect.production.service;

import java.util.List;

import kr.or.connect.production.dto.CommentInfo;
import kr.or.connect.production.dto.DisplayImageInfo;
import kr.or.connect.production.dto.DisplayInfo;
import kr.or.connect.production.dto.ProductImageInfo;
import kr.or.connect.production.dto.ProductPriceInfo;

public interface ProductDetailService {
	public List<ProductImageInfo> getProductImageInfoList(Long displayInfoId);
	List<DisplayInfo> getDisplayInfo(Long productId);
	List<DisplayImageInfo> getDisplayImageInfoList(Long displayInfoId);
	List<CommentInfo> getCommentInfo(Long displayInfoId);
	int getCountCommentInfo(Long displayInfoId);
	String getAverageScore (Long displayInfoId);
	List<ProductImageInfo> getProductMainImage(Long displayInfoId);
	List<ProductPriceInfo> getProductPriceInfo(Long displayInfoId);
}
