package kr.or.connect.production.dto;

public class ProductImageInfo {
	private String contentType;
	private String CreateDate;
	private boolean deleteFlag;
	private Long fileInfoId;
	private String fileName;
	private String modifyDate;
	private Long productId;
	private Long productImageId;
	private String saveFileName;
	private String type;
	
	
	public String getContentType() {
		return contentType;
	}


	public void setContentType(String contentType) {
		this.contentType = contentType;
	}


	public String getCreateDate() {
		return CreateDate;
	}


	public void setCreateDate(String createDate) {
		CreateDate = createDate;
	}


	public boolean isDeleteFlag() {
		return deleteFlag;
	}


	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}


	public Long getFileInfoId() {
		return fileInfoId;
	}


	public void setFileInfoId(Long fileInfoId) {
		this.fileInfoId = fileInfoId;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getModifyDate() {
		return modifyDate;
	}


	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}


	public Long getProductId() {
		return productId;
	}


	public void setProductId(Long productId) {
		this.productId = productId;
	}


	public Long getProductImageId() {
		return productImageId;
	}


	public void setProductImageId(Long productImageId) {
		this.productImageId = productImageId;
	}


	public String getSaveFileName() {
		return saveFileName;
	}


	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return "ProductImage [contentType=" + contentType + ", CreateDate=" + CreateDate + ", deleteFlag=" + deleteFlag
				+ ", fileInfoId=" + fileInfoId + ", fileName=" + fileName + ", modifyDate=" + modifyDate
				+ ", productId=" + productId + ", productImageId=" + productImageId + ", saveFileName=" + saveFileName
				+ ", type=" + type + "]";
	}
	
	
}
