package com.praveen.bookmyshow.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Ticket extends  BaseModel{
    private int amount;
    private Date bookingDate;
    private User bookedBy;
    private Show show;
    private List<Seat> seats;
    private List<Payment> payments;
    private TicketStatus ticketStatus;
}
