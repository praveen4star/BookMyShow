package com.praveen.bookmyshow.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Payment extends BaseModel{
    private int amount;
    private PaymentProvider paymentProvider;
    private PaymentStatus paymentStatus;
    private Date time;
    private String refI;
    private Ticket ticket;
    private PaymentType paymentType;

}

