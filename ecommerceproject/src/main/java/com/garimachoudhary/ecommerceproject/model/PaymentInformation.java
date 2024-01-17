package com.garimachoudhary.ecommerceproject.model;

import java.time.LocalDate;

import jakarta.persistence.Column;

public class PaymentInformation {

  @Column(name="cardholder_name")
  private String cardholderName;
  
  @Column(name="expiration_date")
  private LocalDate expirationDate;
  
  @Column(name="cvv")
  private String cvv;
  
  
  public PaymentInformation() {
	  
  }


public String getCardholderName() {
	return cardholderName;
}


public void setCardholderName(String cardholderName) {
	this.cardholderName = cardholderName;
}


public LocalDate getExpirationDate() {
	return expirationDate;
}


public void setExpirationDate(LocalDate expirationDate) {
	this.expirationDate = expirationDate;
}


public String getCvv() {
	return cvv;
}


public void setCvv(String cvv) {
	this.cvv = cvv;
}


public PaymentInformation(String cardholderName, LocalDate expirationDate, String cvv) {
	super();
	this.cardholderName = cardholderName;
	this.expirationDate = expirationDate;
	this.cvv = cvv;
}
  
  
}

