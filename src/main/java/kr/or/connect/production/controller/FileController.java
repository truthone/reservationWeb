package kr.or.connect.production.controller;

import java.io.FileOutputStream;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.production.dto.FileInfo;
import kr.or.connect.production.dto.ReservationUserComment;
import kr.or.connect.production.dto.ReservationUserCommentImage;
import kr.or.connect.production.service.FileService;
import kr.or.connect.production.service.ProductDetailService;
import kr.or.connect.production.service.ReservationCommentService;
import kr.or.connect.production.service.ReservationService;

@Controller
public class FileController {
	@Autowired
	ReservationService reservationService; 
	@Autowired
	ProductDetailService productDetailService;
	@Autowired
	FileService fileService;
	@Autowired
	ReservationCommentService reservationCommentService;
	
	@GetMapping("/reviewWrite/{reservationInfoId}")
	public String uploadForm(@PathVariable(name = "reservationInfoId", required = true) Long reservationInfoId,
			ModelMap model) {
		Long productId = reservationService.getReservationInfo(reservationInfoId).get(0).getProductId();
		String description = productDetailService.getDisplayInfo(productId).get(0).getProductDescription();
		
		model.addAttribute("productId",productId);
		model.addAttribute("description", description);
		model.addAttribute("reservationInfoId", reservationInfoId);
		
		return "comment";
	}
	
	
	@SuppressWarnings("null")
	@PostMapping("/api/reservations/{reservationInfoId}/comment")
	public String upload(@RequestParam(name ="reviewImg", required = false) MultipartFile file, @PathVariable(name = "reservationInfoId") Long reservationInfoId,
			@RequestParam(name = "score", required = true)int score, @RequestParam(name = "comment", required = true) String comment, 
			@RequestParam(name = "productId", required = true) Long productId) {
		
		final String DIRECTORY_PATH = "c:/Users/truth/Desktop/";
		String fileName = file.getOriginalFilename();
		long fileSize = file.getSize();
		String contentType = file.getContentType();
		String saveFileName = DIRECTORY_PATH + fileName;
		
		ReservationUserComment reservationUserCommentInfo = new ReservationUserComment();
        ReservationUserCommentImage reservationUserCommentImageInfo = new ReservationUserCommentImage();
       
        
        reservationUserCommentInfo.setComment(comment);
        reservationUserCommentInfo.setProductId(productId);
        reservationUserCommentInfo.setReservationInfoId(reservationInfoId);
        reservationUserCommentInfo.setScore(score);
        Long reservationUserCommentId = reservationCommentService.addReservationComment(reservationUserCommentInfo);
        
        try(    FileOutputStream fos = new FileOutputStream(DIRECTORY_PATH + file.getOriginalFilename());
                InputStream is = file.getInputStream();
        ){
        	    int readCount = 0;
        	    byte[] buffer = new byte[1024];
        	    
	            while((readCount = is.read(buffer)) != -1){
	                fos.write(buffer,0,readCount);
	            }
	            FileInfo fileInfo = new FileInfo();
	            fileInfo.setContentType(contentType);
	            fileInfo.setFileName(fileName);
	            fileInfo.setSaveFileName(saveFileName);
                Long fileId =  fileService.addFileInfo(fileInfo);
                reservationUserCommentImageInfo.setFileId(fileId);
                reservationUserCommentImageInfo.setProductId(productId);
                reservationUserCommentImageInfo.setReservationInfoId(reservationInfoId);
                reservationUserCommentImageInfo.setReservationUserCommentId(reservationUserCommentId);
       
                reservationCommentService.addReservationCommentImageInfo(reservationUserCommentImageInfo);
                
        }catch(Exception ex){
            //throw new RuntimeException("file Save Error");
        	System.out.println("파일없음");
        }
        
        return "redirect:/myreservation";
	}
}
