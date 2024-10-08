package com.saeipman.app.file.service;

import java.util.List;

import com.saeipman.app.building.service.BuildingVO;

public interface FileService {
	public String fileGroupId(FileVO fileVO);
	public int fileInsert(FileVO fIleVO);
	
	//file_name
	public List<String> getFileName(String buildingId);
	
	//업데이트시 그룹아이디
	public int getUpdateGroupId(BuildingVO buildingVO);
	
	// 파일 단건삭제
	public void removeFile(int fileId);
	// 파일 단건조회
	public FileVO getFileInfo(int fileId);
}
