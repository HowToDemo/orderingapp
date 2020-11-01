package com.project.orderingapp.service.impl;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.orderingapp.api.DistanceMatrixApi;
import com.project.orderingapp.entity.Order;
import com.project.orderingapp.exception.ApiResponseException;
import com.project.orderingapp.exception.InvalidIntegerValueException;
import com.project.orderingapp.exception.NotIntegerException;
import com.project.orderingapp.exception.OrderAlreadyTakenException;
import com.project.orderingapp.exception.OrderIdNotFoundException;
import com.project.orderingapp.model.OrderRequest;
import com.project.orderingapp.repository.OrderRepository;
import com.project.orderingapp.repository.OrdersPaginationRepository;
import com.project.orderingapp.service.OrderService;
import com.project.orderingapp.model.OrderResponse;

@RunWith(SpringRunner.class)
public class OrderServiceTest {

	@InjectMocks
	private OrderServiceImpl orderServiceImpl;
	
	@Mock
	private OrderService orderService;
	
	@Mock
	private OrderRepository orderRepository;
	
	@Mock
	private OrdersPaginationRepository ordersRepository;
	
	@Mock
	private DistanceMatrixApi distanceMatrixApi;
	
	private List<String> validOrigin;
	private List<String> validDestination;
	private List<String> invalidOrigin;
	private List<String> invalidDestination;
	private List<Order> orders;
	
	private OrderRequest orderRequest;
	private OrderRequest invalidOrderRequest;
	private Order order;
	private Order takenOrder;
	
	
	@Before
	public void setUp() {
		validOrigin = new ArrayList<>();
		validOrigin.add("14.315601,120.95417");
		validDestination = new ArrayList<>();
		validDestination.add("14.359797,121.05181");
		orderRequest = new OrderRequest();
		orderRequest.setOrigin(validOrigin);
		orderRequest.setDestination(validDestination);
		
		invalidOrigin = new ArrayList<>();
		invalidOrigin.add("SAMPLE,120.95417");
		invalidDestination = new ArrayList<>();
		invalidDestination.add("14.359797,Sample");
		invalidOrderRequest = new OrderRequest();
		invalidOrderRequest.setOrigin(invalidOrigin);
		invalidOrderRequest.setDestination(invalidDestination);
		
		order = new Order();
		order.setId(Long.valueOf(1));
		order.setDistance(100);
		order.setStatus("STATUS");
		
		takenOrder = new Order();
		takenOrder.setId(Long.valueOf(1));
		takenOrder.setDistance(100);
		takenOrder.setStatus("TAKEN");
		
		orders = new ArrayList<>();
		orders.add(order);
		orders.add(order);
	}
	
	@Test
	public void whenCreateOrderReturnOrderResponse() {
		when(orderRepository.save(Mockito.any())).thenReturn(order);
		OrderResponse mockOrderResponse = orderServiceImpl.createOrder(orderRequest);
		assertNotNull(mockOrderResponse);
	}
	
	@Test(expected=ApiResponseException.class)
	public void whenCreateOrderWithInvalidDataTypeExpectApiException() {
		orderServiceImpl.createOrder(invalidOrderRequest);
	}
	
	@Test
	public void whenUpdateStatusReturnId() {
		when(orderRepository.existsById(Mockito.anyLong())).thenReturn(true);
		when(orderRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(order));
		when(orderRepository.save(Mockito.any())).thenReturn(order);
		int mockId = orderServiceImpl.updateStatus(5);
		assertNotEquals(0,mockId);
	}
	
	@Test(expected=OrderIdNotFoundException.class)
	public void whenUpdateStatusWithInvalidIdExpectOrderIdNotFoundException() {
		when(orderRepository.existsById(Long.valueOf(500))).thenReturn(false);
		when(orderRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(order));
		orderServiceImpl.updateStatus(500);
	}
	
	@Test(expected=OrderAlreadyTakenException.class)
	public void whenUpdateStatusWithTakenStatusExpectOrderAlreadyTakenException() {
		when(orderRepository.existsById(Long.valueOf(1))).thenReturn(true);
		when(orderRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(takenOrder));
		orderServiceImpl.updateStatus(1);
	}
	
	@Test
	public void whenGetOrdersReturnList() {
		Page<Order> pagedTasks = new PageImpl<>(orders);
		when(ordersRepository.findAll(ArgumentMatchers.isA(Pageable.class))).thenReturn(pagedTasks);
		List<OrderResponse> mockOrders = orderServiceImpl.getOrders("1", "5");
		assertNotEquals(0,mockOrders.size());
	}
	
	@Test(expected=InvalidIntegerValueException.class)
	public void whenGetOrdersWithZeroValueReturnInvalidIntegerValueException() {
		orderServiceImpl.getOrders("0", "5");
	}
	
	@Test(expected=NotIntegerException.class)
	public void whenGetOrdersWithZeroValueReturnNotIntegerException() {
		orderServiceImpl.getOrders("INVALID", "INVALID");
	}
}
