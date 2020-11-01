package com.project.orderingapp.service;

import java.util.List;

import com.project.orderingapp.model.OrderRequest;
import com.project.orderingapp.model.OrderResponse;

public interface OrderService {

	public OrderResponse createOrder(OrderRequest orderRequest);
	
	public int updateStatus(int id);
	
	public List<OrderResponse> getOrders(String page, String limit);
}
