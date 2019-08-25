package com.shopping.integration.transform;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.shopping.entity.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;

public class SaleOrderTransform {
	
	@Value(value = "sale_order_#{new java.util.Date().getTime()}.xml")
	private String errorFileName;
	
	@Transformer
	public void marshall(Message<Order> message) throws JAXBException, FileNotFoundException {
		JAXBContext contextObj = JAXBContext.newInstance(Order.class);
		Marshaller marshallerObj = contextObj.createMarshaller();
		marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshallerObj.marshal(message.getPayload(), new FileOutputStream(errorFileName));
	}
}
