package com.tranxactor.stripe1.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import com.stripe.model.Token;

@Service
public class StripeService {

	@Value("${STRIPE_SECRET_KEY}")
	private String API_SECRET_KEY;

	@Autowired
	public StripeService() {
		Stripe.apiKey = API_SECRET_KEY;
	}

	public Charge chargeNewCard(String token, double amount) throws InvalidRequestException, Exception {
		Stripe.apiKey = API_SECRET_KEY;
		Map<String, Object> card = new HashMap<>();
		card.put("number", "4242424242424242");
		card.put("exp_month", 3);
		card.put("exp_year", 2021);
		card.put("cvc", "314");
		Map<String, Object> params = new HashMap<>();
		params.put("card", card);

		Token token2 = Token.create(params);
		String tokenid = token2.getId();

		Map<String, Object> chargeParams = new HashMap<String, Object>();
		chargeParams.put("amount", (int) (amount * 5));
		chargeParams.put("currency", "USD");
		chargeParams.put("source", tokenid);
		Charge charge = Charge.create(chargeParams);

		return charge;
	}

}
