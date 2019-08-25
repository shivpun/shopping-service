package org.shopping.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.util.CollectionUtils;

@Entity
@Table(name = "SALE_ORDER")
@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class Order implements Serializable {

	private static final long serialVersionUID = 8090301900507511495L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id")
	@SequenceGenerator(name = "order_id", sequenceName = "order_id_seq")
	@XmlAttribute(name = "orderId")
	private Long orderId;

	@Column(name = "USER_ID")
	@XmlElement(name = "userId")
	private String userId;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	@XmlElementWrapper(name = "orderLines")
	@XmlElement(name = "orderLine")
	private Set<OrderLine> orderLines;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Set<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(Set<OrderLine> orderLines) {
		this.orderLines = orderLines;
		for (OrderLine line : orderLines) {
			// line.setOrder(this);
		}
	}

	public void addOrderLine(OrderLine orderLine) {
		if (CollectionUtils.isEmpty(getOrderLines())) {
			this.orderLines = new HashSet<OrderLine>();
		}
		if (orderLine != null && !this.orderLines.contains(orderLine)) {
			// orderLine.setOrder(this);
			this.orderLines.add(orderLine);
		}
	}
}
