package com.shopme.checkout;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Calendar;

public class CheckoutInfo {

	private float productCost;
	private float productTotal;
	private float shippingCostTotal;
	private float paymentTotal;
	private int deliverDays;
	private boolean codeSupported;
	
	public float getProductCost() {
		return productCost;
	}
	public void setProductCost(float productCost) {
		this.productCost = productCost;
	}
	public float getProductTotal() {
		return productTotal;
	}
	public void setProductTotal(float productTotal) {
		this.productTotal = productTotal;
	}
	public float getShippingCostTotal() {
		return shippingCostTotal;
	}
	public void setShippingCostTotal(float shippingCostTotal) {
		this.shippingCostTotal = shippingCostTotal;
	}
	public float getPaymentTotal() {
		return paymentTotal;
	}
	public void setPaymentTotal(float paymentTotal) {
		this.paymentTotal = paymentTotal;
	}
	public int getDeliverDays() {
		return deliverDays;
	}
	public void setDeliverDays(int deliverDays) {
		this.deliverDays = deliverDays;
	}
	
	/*
	public LocalDateTime getDeliverDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, deliverDays);
		
		return calendar.getTime();
	}
	*/
	
	/*
	public LocalDateTime getDeliverDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, (int) deliverDays);
		
		return LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId());
	}
	*/
	
	public LocalDateTime getDeliverDate() {
	    return LocalDateTime.now().plusDays(deliverDays);
	}
	
	public boolean isCodeSupported() {
		return codeSupported;
	}
	public void setCodeSupported(boolean codeSupported) {
		this.codeSupported = codeSupported;
	}
	
	public String getPaymentTotal4PayPal() {
		DecimalFormat formatter = new DecimalFormat("###,###.##");
		return formatter.format(paymentTotal);
	}
	
}
