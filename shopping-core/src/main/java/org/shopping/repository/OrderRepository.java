package org.shopping.repository;

import java.io.Serializable;

import org.shopping.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Serializable> {
}
