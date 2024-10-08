package com.saeipman.app.gwanlibi.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class GwanlibiVO {
	
	private String monthGwanlibiNo;       // 월 관리비 번호 - PK
	private String buildingId;			  // 건물 식별 ID - FK
	private int totalMoney; 			  // 관리비 총 금액
	@DateTimeFormat(pattern = "yyyy-MM")
	private Date paymentMonth;  		  // 관리비 정산 월
	private String buildingName; 		  // 건물 이름
	private String doroAddr;
	private int previousMonth;			  // 전월 관리 비용
	private int thisMonth;				  // 금월 관리 비용
	private String gwanlibiName;		  // 관리비 이름
	
	private int gaguGwanlibi; 			  // 가구별 관리비
	private double gaguGwanlibiByItem;        // 가구별 관리비
	private String strGwanlibiByGagu;	  // 가구별 관리비 (,)
	
	private int gwanlibiItemMoney;		  // 항목별 관리비
	private String strGwanlibiItemMoney;  // 항목별 관리비 (,)
	
	private Integer gwanlibiItemNo;		  // 관리비 항목 번호
	private String variableYn; 			  // 관리비 변동 여부
	private Integer version;			  // 관리비 항목 버전 (업데이트 될 때마다 새로운 버전으로 추가)
	private int fixedPrice;				  // 고정 관리비
	
	private String gwanlibiDetailsNo;	  // 관리비 상세 내역 번호
	private Integer totalGagu;			  // 총 가구 수
	
	
	private String roomId;
	@DateTimeFormat(pattern = "yyyy-MM")
	private Date paymentDueDate;		  // 관리비 납부 기한
	private int ipjuState;
	//private String strIpjuState;
	
}
