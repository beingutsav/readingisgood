package com.getir.readingisgoodservicerest.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CustomerMonthlyStatistics {
	
	private String month;
	private int totalOrderCount;
	private int totalBookCount;
	private BigDecimal totalPurchasedAmount;
	
	
	public CustomerMonthlyStatistics(String month, int totalOrderCount, int totalBookCount,
			BigDecimal totalPurchasedAmount) {
		super();
		this.month = month;
		this.totalOrderCount = totalOrderCount;
		this.totalBookCount = totalBookCount;
		this.totalPurchasedAmount = totalPurchasedAmount;
	}
	
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public int getTotalOrderCount() {
		return totalOrderCount;
	}
	public void setTotalOrderCount(int totalOrderCount) {
		this.totalOrderCount = totalOrderCount;
	}
	public int getTotalBookCount() {
		return totalBookCount;
	}
	public void setTotalBookCount(int totalBookCount) {
		this.totalBookCount = totalBookCount;
	}
	public BigDecimal getTotalPurchasedAmount() {
		return totalPurchasedAmount;
	}
	public void setTotalPurchasedAmount(BigDecimal bigDecimal) {
		this.totalPurchasedAmount = bigDecimal;
	}
	
	
	public CustomerMonthlyStatistics merge(CustomerMonthlyStatistics statistics) {
		statistics.setTotalOrderCount(statistics.getTotalOrderCount() + this.getTotalOrderCount());
		statistics.setTotalPurchasedAmount(statistics.getTotalPurchasedAmount().add(this.getTotalPurchasedAmount()));
		statistics.setTotalBookCount(statistics.getTotalBookCount() + this.getTotalBookCount());
		
		return statistics;
	}
	
	@JsonIgnore
	public String getKey() {
		return this.getMonth().toString();
	}

	
	
	
}
