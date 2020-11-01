package com.project.orderingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.orderingapp.constant.AppConstant;
import com.project.orderingapp.model.ErrorResponse;
import com.project.orderingapp.model.OrderRequest;
import com.project.orderingapp.model.OrderResponse;
import com.project.orderingapp.model.TakeOrderResponse;
import com.project.orderingapp.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@Tag(name = "Order", description = "ORDER API")
public class OrderController {


	@Autowired
	private OrderService orderService;
	
	@Operation(summary = "Place Order", description = "", tags = { "order" })
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Success",
		           content = @Content(schema = @Schema(implementation = OrderResponse.class))),
		@ApiResponse(responseCode = "400", description = "Invalid input",
           content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "405", description = "Invalid Parameters",
        content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	@PostMapping(value="/orders", consumes = { "application/json", "application/xml" })
	public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
		return ResponseEntity.ok().body(orderService.createOrder(orderRequest));
	}
	
	@Operation(summary = "Take Order", description = "", tags = { "order" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success",
			           content = @Content(schema = @Schema(implementation = TakeOrderResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input",
	           content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "404", description = "ID not found",
	           content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "409", description = "This order is already taken",
	        content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	@PatchMapping(value="/orders/{id}")
	public ResponseEntity<TakeOrderResponse> getOrder(@PathVariable(value = "id",required=true) Integer orderId) {
		orderService.updateStatus(orderId);
		return ResponseEntity.ok().body(new TakeOrderResponse(AppConstant.ORDER_SUCCESS_STATUS));
	}
	
	@Operation(summary = "Get List Of Order", description = "", tags = { "order" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success",
				content = @Content(schema = @Schema(implementation = TakeOrderResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid Data Type. Parameters must be an integer",
	        	content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "406", description = "Value must be 1 or higher",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	@GetMapping(value="/orders")
	public ResponseEntity<List<OrderResponse>> getOrders(@RequestParam String page, @RequestParam String limit) {
		return ResponseEntity.ok().body(orderService.getOrders(page, limit));
	}
}
