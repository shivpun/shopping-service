package org.shopping.entity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.jupiter.api.Test;
import org.shopping.util.OrderUtil;

public class OrderJaxbTest {

	private final String path = "src/test/resources/test/";

	@Test
	public void convertOrderToXml_1() throws JAXBException, FileNotFoundException {
		Order order = OrderUtil.createOrder(1L, "ABC");
		order.addOrderLine(OrderUtil.createOrderLine(1L, "PO001", "Product 1", 25, order));
		order.addOrderLine(OrderUtil.createOrderLine(2L, "PO002", "Product 2", 15, order));
		order.addOrderLine(null);
		JAXBContext contextObj = JAXBContext.newInstance(Order.class);
		Marshaller marshallerObj = contextObj.createMarshaller();
		marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshallerObj.marshal(order, new FileOutputStream(path + "order_1.xml"));
	}
	
	@Test
	public void convertXmlToOrder_1() throws JAXBException, FileNotFoundException {
		JAXBContext contextObj = JAXBContext.newInstance(Order.class);
		Unmarshaller unmarshaller = contextObj.createUnmarshaller();
		Order order = (Order) unmarshaller.unmarshal(new FileInputStream(path + "order_1.xml"));
		System.out.println(order);
	}

	@Test
	public void convertOrderToXml_2() throws JAXBException, FileNotFoundException {
		Set<OrderLine> orderLines = new HashSet<OrderLine>();
		Order order = OrderUtil.createOrder(1L, "ABC");
		orderLines.add(OrderUtil.createOrderLine(1L, "PO001", "Product 1", 25, order));
		orderLines.add(OrderUtil.createOrderLine(2L, "PO002", "Product 2", 15, order));
		order.setOrderLines(orderLines);
		JAXBContext contextObj = JAXBContext.newInstance(Order.class);
		Marshaller marshallerObj = contextObj.createMarshaller();
		marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshallerObj.marshal(order, new FileOutputStream(path + "order_2.xml"));
	}

}
