package org.shopping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@Entity
@Table(name = "ORDER_LINE")
@XmlRootElement(name = "orderLine")
@XmlAccessorType (XmlAccessType.FIELD)
public class OrderLine implements Serializable {

	private static final long serialVersionUID = 3005386845778655763L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_line_id")
	@SequenceGenerator(name = "order_line_id", sequenceName = "order_line_seq")
	@XmlAttribute(name = "code")
	private Long code;

	@Column(name = "PRODUCT_CODE")
	@XmlElement(name = "productCode")
	private String productCode;

	@Column(name = "PRODUCT_NAME")
	@XmlElement(name = "productName")
	private String productName;

	@Column(name = "PRODUCT_COST")
	@XmlElement(name = "cost")
	private double cost;

	@ManyToOne
	@JoinColumn(name = "order_id")
	@XmlPath(value = "/order")
	private Order order;

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof OrderLine)) {
			return false;
		}
		OrderLine other = (OrderLine) obj;
		if (code == null || other.code == null) {
			return false;
		}
		return code.equals(other.code);
	}
}
