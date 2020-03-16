package com.tranxactor.stripe1.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;

import com.tranxactor.stripe1.model.BaseMessage;
import com.tranxactor.stripe1.service.StripeService;

import io.swagger.annotations.ApiParam;

@RestController
public class PaymentController {
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

	@Autowired
	private StripeService stripeService;


	@Value("${STRIPE_PUBLIC_KEY}")
	private String stripePublicKey;

	@RequestMapping(value = "/charge", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<BaseMessage> chargeCard1(

			@ApiParam(value = "Token value.") @Valid @RequestParam(value = "token", required = false) String token,
			@ApiParam(value = "Amount Value.") @Valid @RequestParam(value = "amount", required = false) Double amount) {
		BaseMessage baseMessage = new BaseMessage();
		try {
			Charge charge=	stripeService.chargeNewCard(token, amount);
			Date created = new Date(Long.parseLong(String.valueOf(charge.getCreated())) * 1000);		
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			String ourformat = formatter.format(created);
		
			baseMessage.setAmount(charge.getAmount());
			baseMessage.setCurrency(charge.getCurrency());
			baseMessage.setBalanceTransaction(charge.getBalanceTransaction());
			baseMessage.setId(charge.getId());
			baseMessage.setObject(charge.getObject());
			baseMessage.setOutcomeNetworkStatus(charge.getOutcome().getNetworkStatus());
			baseMessage.setOutcomeSellerMessage(charge.getOutcome().getSellerMessage());
			baseMessage.setOutcometype(charge.getOutcome().getType());
			baseMessage.setPaid(charge.getPaid());
			baseMessage.setPaymentMethod(charge.getPaymentMethod());
			baseMessage.setReceiptUrl(charge.getReceiptUrl());
			baseMessage.setStatus(charge.getStatus());
			baseMessage.setCreated(ourformat);
			
			
			
		} catch (InvalidRequestException e) {
            logger.error(e.getMessage(), e);
            throw new IllegalArgumentException(e);
		} catch (Exception e) {
	        logger.error(e.getMessage(), e);
	        e.printStackTrace();
            throw new IllegalArgumentException(e);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(baseMessage);

	}

}
