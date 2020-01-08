package kr.or.connect.production.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.production.dao.PromotionDao;
import kr.or.connect.production.dto.Promotion;
import kr.or.connect.production.service.PromotionService;


@Service
public class PromotionServiceImpl implements PromotionService {
    @Autowired
    PromotionDao promotionDao;

	@Override
	public List<Promotion> getPromotionInfo() {
		List<Promotion> list = promotionDao.selectAll();
		return list;
	}
   
}
