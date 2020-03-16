package com.tranxactor.stripe1.model;

import java.util.Date;

import lombok.Data;

/**
 * @author Sreedhar Sivan
 * @create 03/16/2020
 */

@Data
public class BaseMessage {

	private Long amount;

	private String balanceTransaction;

	private String currency;

	private String id;

	private String object;

	private String outcomeNetworkStatus;

	private String outcomeSellerMessage;

	private String outcometype;

	private Boolean paid;

	private String paymentMethod;

	private String receiptUrl;

	private String status;
	
	private String created;

}
