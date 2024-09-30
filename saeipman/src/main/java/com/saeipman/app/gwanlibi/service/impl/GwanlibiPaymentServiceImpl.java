package com.saeipman.app.gwanlibi.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.saeipman.app.gwanlibi.mapper.GwanlibiPaymentMapper;
import com.saeipman.app.gwanlibi.service.GwanlibiPaymentService;
import com.saeipman.app.gwanlibi.service.GwanlibiPaymentVO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GwanlibiPaymentServiceImpl implements GwanlibiPaymentService {
	
	private GwanlibiPaymentMapper gwanlibiPaymentMapper;
	
	@Override
	public List<GwanlibiPaymentVO> getGwanlibiPaymentStateList(String buildingId, String paymentMonth) {
		return gwanlibiPaymentMapper.selectGwanlibiPaymentStateList(buildingId, paymentMonth);
	}

}
