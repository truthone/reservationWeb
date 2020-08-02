package kr.or.connect.production.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.production.dao.FileInfoDao;
import kr.or.connect.production.dto.FileInfo;
import kr.or.connect.production.service.FileService;

@Service
public class FileServiceImpl implements FileService{

	@Autowired
	FileInfoDao fileInfoDao;
	
	@Override
	public Long addFileInfo(FileInfo fileInfo) {
		fileInfo.setCreateDate(new Date());
		fileInfo.setModifiDate(new Date());
		Long fileInfoId = fileInfoDao.insertFileInfo(fileInfo);
		return fileInfoId;
	}
	
	@Override
	public List<FileInfo> getFileInfo(Long fileInfoId) {
		List<FileInfo> list = fileInfoDao.getFileInfo(fileInfoId);
		return list;
	}
}
