package com.spring.ecommerce.repos;

import com.spring.ecommerce.entities.orders.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Orders, Integer> {
}
