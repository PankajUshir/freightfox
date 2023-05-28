package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
public class Transaction {
	
	private String seller;
	private String sellerGstin;
	private String sellerAddress;
	private String buyer;
	private String buyerGstin;
	private String buyerAddress;
	
	private Set<Item> items = new HashSet<Item>();
	
	
	public Transaction(String seller, String sellerGstin, String sellerAddress, String buyer, String buyerGstin,
			String buyerAddress, Set<Item> items) {
		super();
		this.seller = seller;
		this.sellerGstin = sellerGstin;
		this.sellerAddress = sellerAddress;
		this.buyer = buyer;
		this.buyerGstin = buyerGstin;
		this.buyerAddress = buyerAddress;
		this.items = items;
	}
	
	public void setItems(Set<Item> items) {
		for(Item  i : items)
			i.setTransaction(this);
		this.items = items;
	}
	
}
