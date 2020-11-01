package com.project.orderingapp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.project.orderingapp.entity.Order;

@Repository
public interface OrdersPaginationRepository extends PagingAndSortingRepository<Order,Long>{
}
