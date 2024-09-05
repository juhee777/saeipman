package com.saeipman.app.building.mapper;

import java.util.List;

import com.saeipman.app.building.service.BuildingVO;
import com.saeipman.app.file.service.FileVO;

public interface BuildingMapper {
	public List<BuildingVO> buildingList();
	public BuildingVO buildingListInfo(BuildingVO buildingVO);
	public int buildingInsert(BuildingVO buildingVO);
	public int buildingUpdate(BuildingVO buildingVo);
	public int selectBuildingDelete(String buildingId);
	
	
}
