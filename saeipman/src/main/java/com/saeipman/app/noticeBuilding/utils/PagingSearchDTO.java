package com.saeipman.app.noticeBuilding.utils;

import lombok.Data;

@Data
public class PagingSearchDTO {

	private int startPage; // 페이징 -> 시작 페이지
	private int endPage;
	private int realEnd; // 데이터의 총 수에 따라 계산된 실제 마지막 페이지
	private boolean prePage, nextPage;
	private int nowPage; // 현재 페이지
	private int pageData; // 한 페이지에 나오는 게시물 수
	private int total; //전체 게시물 수
	
	private String scKeyword;

	// 기본생성자 => 현재 페이지, 1페이지 게시물 수 1과 10으로 초기설정
	public PagingSearchDTO() {
		this.nowPage = 1;
		this.pageData = 10;
	}
	
	//전체 게시물 수는 달라질 수 있음 따라서 total 값 입력받아서 변경하고, 변경된 값으로 페이지 수 다시 계산해야함.
	public void setTotal(int total) {
		this.total = total;
		pageCount();
	}

	private void pageCount() {
		
		// 올림처리하기 위해서 1.0 곱함(정수로만 곱하면 소수점 아래 값이 무시되기 때문에 페이징에 문제 생김..)
		realEnd = (int) (Math.ceil((total * 1.0) / pageData));
		
		//페이징한 부분에서 마지막 페이지 계산하려고.
		this.endPage = (int) (Math.ceil(nowPage / 5.0)) * 5;
		if (this.endPage > realEnd) {
		    this.endPage = realEnd;
		}
		
		this.startPage = this.endPage - 4;
		if (this.startPage < 1) {
		    this.startPage = 1;
		}

		this.prePage = this.startPage > 1;

		this.nextPage = this.endPage < realEnd;

	}
}
