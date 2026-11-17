package com.shopme.common.entity.order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.shopme.common.entity.IdBasedEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "order_track")
public class OrderTrack extends IdBasedEntity{

	@Column(length = 256)
	private String notes;
	
	private LocalDateTime updatedTime;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 45, nullable = false)
	private OrderStatus status;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public LocalDateTime getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(LocalDateTime localDate) {
		this.updatedTime = localDate;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	/*
	 * Older code
	@Transient
	public String getUpdatedTimeOnForm() {
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
		return dateFormatter.format(this.updatedTime);
	}
	
	public void setUpdatedTimeOnForm(String dateString) {
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
		
		try {
			this.updatedTime = dateFormatter.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}*/
	
	@Transient
	public String getUpdatedTimeOnForm() {
	    DateTimeFormatter dateFormatter =
	            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

	    return this.updatedTime.format(dateFormatter);
	}

	public void setUpdatedTimeOnForm(String dateString) {
	    DateTimeFormatter dateFormatter =
	            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

	    try {
	        this.updatedTime = LocalDateTime.parse(dateString, dateFormatter);
	    } catch (DateTimeParseException e) {
	        throw new IllegalArgumentException(
	                "Invalid date-time format: " + dateString, e);
	    }
	}
}
