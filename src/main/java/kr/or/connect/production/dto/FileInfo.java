package kr.or.connect.production.dto;

import java.util.Date;

public class FileInfo {
	private Long id;
	private String fileName;
	private String saveFileName;
	private String contentType;
	private int deleteFlag;
	private Date createDate;
	private Date modifiDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public int getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifiDate() {
		return modifiDate;
	}
	public void setModifiDate(Date modifiDate) {
		this.modifiDate = modifiDate;
	}
	
}
