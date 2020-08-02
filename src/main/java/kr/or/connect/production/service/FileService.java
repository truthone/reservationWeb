package kr.or.connect.production.service;

import java.util.List;

import kr.or.connect.production.dto.FileInfo;

public interface FileService {
	Long addFileInfo(FileInfo fileInfo);
	List<FileInfo> getFileInfo(Long fileInfoId);
}
