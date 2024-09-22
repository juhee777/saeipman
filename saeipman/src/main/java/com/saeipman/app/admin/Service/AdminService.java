package com.saeipman.app.admin.Service;

import java.util.List;

import com.saeipman.app.commom.paging.PagingDTO;
import com.saeipman.app.member.service.ImdaeinVO;

public interface AdminService {
	public List<Member> imdaeinList();
	public List<Member> imchainList();
	
	// 공지사항 목록 조회 (페이징 및 검색)
	List<NoticeVO> noticeList(PagingDTO paging, String keyword);
	// 공지사항 총 개수 조회 (검색어에 따라)
	int countNotices(String keyword);
	// 공지사항 단건조회
	NoticeVO noticeInfo(int postNo);
	// 공지사항 단건삭제
	void removeNotice(int postNo);
}
