package com.saeipman.app.minwon.service;

import java.util.List;
import java.util.Map;

import com.saeipman.app.message.MessageVO;

public interface MinwonService {
	//전체조회
	public List<MinwonVO> minwonList(Criteria cri);
	
	//단건조회
	public MinwonVO minwonSelect(MinwonVO minwonVO);
	
	//등록
	public int minwonInsert(MinwonVO minwonVO);
	
	//수정
	public Map<String, Object> minwonUpdate(MinwonVO minwonVO);
	
	//삭제
	public int minwonDelete(int postNo);
	
	//카테고리
	public List<MinwonVO> categoryList();
	
	//페이지 토탈
	public int pageTotal(Criteria cri);

	//민원상태처리 업로드
	public int acceptStateUpdate(MinwonVO minwonVO);

	
	

}
