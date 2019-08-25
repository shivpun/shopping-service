package org.shopping.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.shopping.ShoppingApplicationTest;
import org.shopping.entity.Order;
import org.shopping.util.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles(value = { "test" })
@ContextConfiguration(classes = ShoppingApplicationTest.class)
public class OrderRepositoryTest {

	@Autowired
	private OrderRepository orderRepository;

	@Test
	public void testSave() {
		Order order = OrderUtil.createOrder(1L, "ABC");
		order.addOrderLine(OrderUtil.createOrderLine(1L, "PO001", "Product 1", 25, order));
		order.addOrderLine(OrderUtil.createOrderLine(2L, "PO002", "Product 2", 15, order));
		order.addOrderLine(null);
		Order o = orderRepository.save(order);
		assertNotNull(o, "Order Object can't be null");
		assertNotNull(o.getOrderLines(), "Order Line Object can't be null");
	}
}
