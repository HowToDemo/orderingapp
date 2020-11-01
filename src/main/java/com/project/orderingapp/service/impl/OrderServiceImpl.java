package com.project.orderingapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.orderingapp.api.DistanceMatrixApi;
import com.project.orderingapp.constant.AppConstant;
import com.project.orderingapp.entity.Order;
import com.project.orderingapp.exception.ApiResponseException;
import com.project.orderingapp.exception.InvalidIntegerValueException;
import com.project.orderingapp.exception.NotIntegerException;
import com.project.orderingapp.exception.OrderAlreadyTakenException;
import com.project.orderingapp.exception.OrderIdNotFoundException;
import com.project.orderingapp.model.OrderRequest;
import com.project.orderingapp.model.OrderResponse;
import com.project.orderingapp.repository.OrderRepository;
import com.project.orderingapp.repository.OrdersPaginationRepository;
import com.project.orderingapp.service.OrderService;
import com.project.orderingapp.utility.OrderingAppUtil;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private DistanceMatrixApi distanceMatrixApi;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrdersPaginationRepository ordersRepository;
	
	Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Override
	public OrderResponse createOrder(OrderRequest orderRequest) {
		try {
			List<String> origin = OrderingAppUtil.convertStringToList(orderRequest.getOrigin().get(0),",");
			List<String> destination = OrderingAppUtil.convertStringToList(orderRequest.getDestination().get(0),",");
			Order order = new Order();
			if(OrderingAppUtil.validateCoordinates(origin,destination)) {
				order.setOriginLatitude(origin.get(AppConstant.LATITUDE));
				order.setOriginLongitude(origin.get(AppConstant.LONGITUDE));
				order.setDestinationLatitude(destination.get(AppConstant.LATITUDE));
				order.setDestinationLongitude(destination.get(AppConstant.LONGITUDE));
				order.setStatus(AppConstant.ORDER_UNASSIGNED_STATUS);
				order.setDistance(distanceMatrixApi.getTotalDistance(origin,destination));
				order = orderRepository.save(order);
			}
			
			return new OrderResponse(order.getId(),order.getDistance(),order.getStatus());
		} catch(NumberFormatException nfe) {
			throw new ApiResponseException();
		}
	}

	@Override
	public int updateStatus(int id) {
		if(validateOrderId(id)) {
			Long orderId = Long.valueOf(id);
			Order order = orderRepository.findById(orderId).get();
			if(AppConstant.ORDER_TAKEN_STATUS.equals(order.getStatus())) {
				throw new OrderAlreadyTakenException();
			} else {
				order.setStatus(AppConstant.ORDER_TAKEN_STATUS);
				order = orderRepository.save(order);
				id = order.getId().intValue();
			}
		}
		return id;
	}
	
	private Boolean validateOrderId(int id) {
		Long orderId = Long.valueOf(id);
		Boolean output = true;
		if(!orderRepository.existsById(orderId)) {
			throw new OrderIdNotFoundException();
		}
		return output;
	}

	@Override
	public List<OrderResponse> getOrders(String page, String limit) {
		List<OrderResponse> orderResponseList = new ArrayList<>();
		
		try {
			Pageable pageable=PageRequest.of(Integer.parseInt(page)-1, Integer.parseInt(limit));
			Page<Order> orderPages = ordersRepository.findAll(pageable);
			orderResponseList = mapOrdersToResponse(orderPages.getContent());
		} catch(NumberFormatException nfe) {
			logger.error("Invalid DataType. {} and {} is not an integer",page,limit);
			throw new NotIntegerException();
		} catch(IllegalArgumentException iae) {
			throw new InvalidIntegerValueException(); 
		}
		return orderResponseList;
	}
	
	private List<OrderResponse> mapOrdersToResponse(List<Order> orders) {
		List<OrderResponse> ordersResponse = new ArrayList<>(); 
		for(Order order : orders) {
			OrderResponse orderObj = new OrderResponse();
			orderObj.setId(order.getId());
			orderObj.setDistance(order.getDistance());
			orderObj.setStatus(order.getStatus());
			ordersResponse.add(orderObj);
		}
		return ordersResponse;
	}
}
