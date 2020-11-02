package com.project.orderingapp.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.project.orderingapp.model.OrderRequest;
import com.project.orderingapp.model.OrderResponse;
import com.project.orderingapp.model.TakeOrderResponse;
import com.project.orderingapp.service.OrderService;

@RunWith(SpringRunner.class)
public class OrderControllerTest {

	@InjectMocks
	private OrderController orderController;
	
	@Mock
	private OrderService orderService;
	
	private OrderResponse orderResponse;
	private OrderRequest orderRequest;
	private List<String> validOrigin;
	private List<String> validDestination;
	private List<OrderResponse> ordersResponse; 
	 
	@Before
	public void setUp() {
		validOrigin = new ArrayList<>();
		validOrigin.add("14.315601,120.95417");
		validDestination = new ArrayList<>();
		validDestination.add("14.359797,121.05181");
		orderRequest = new OrderRequest();
		orderRequest.setOrigin(validOrigin);
		orderRequest.setDestination(validDestination);
		
		orderResponse = new OrderResponse();
		orderResponse.setDistance(1000);
		orderResponse.setId(Long.valueOf(1));
		orderResponse.setStatus("SUCCESS");
		
		ordersResponse = new ArrayList<>();
		ordersResponse.add(orderResponse);
		ordersResponse.add(orderResponse);
	}
	
	@Test
	public void whenCreateOrderReturnHttpStatusOk() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(orderService.createOrder(Mockito.any())).thenReturn(orderResponse);
        ResponseEntity<OrderResponse> orderEntity = orderController.createOrder(orderRequest);
        assertEquals(HttpStatus.OK,orderEntity.getStatusCode());
	}
	
	@Test
	public void whenTakeOrderReturnHttpStatusOk() {
		int id = 0;
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(orderService.updateStatus(Mockito.anyInt())).thenReturn(id);
        ResponseEntity<TakeOrderResponse> takeOrderEntity = orderController.getOrder(1);
        assertEquals(HttpStatus.OK,takeOrderEntity.getStatusCode());
	}
	
	@Test
	public void whenGetOrdersReturnHttpStatusOk() {
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(orderService.getOrders(Mockito.anyString(), Mockito.anyString())).thenReturn(ordersResponse);
        ResponseEntity<List<OrderResponse>> ordersEntity = orderController.getOrders("1","5");
        assertEquals(HttpStatus.OK,ordersEntity.getStatusCode());
	}
}
