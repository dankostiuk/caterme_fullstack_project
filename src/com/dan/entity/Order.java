package com.dan.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Model representing the Order entity.
 * 
 * @author dan
 */
@Entity
@Table(name = "CustomerOrder")
public class Order implements Serializable {
	private static final long serialVersionUID = 478904952993944395L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private Long user_id;
	
	private String summary;
	
	private String description;
	
	private BigDecimal price;

	public Order() {
		
	}

	public Order(Long id, Long user_id, String summary, String description, BigDecimal price) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.summary = summary;
		this.description = description;
		this.price = price;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}
