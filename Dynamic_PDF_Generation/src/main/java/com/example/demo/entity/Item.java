package com.example.demo.entity;

import lombok.Data;

@Data
public class Item {
	private String name;
	private String quantity;
	private String rate;
	private String amount;
	
	private Transaction transaction;

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
	
}
