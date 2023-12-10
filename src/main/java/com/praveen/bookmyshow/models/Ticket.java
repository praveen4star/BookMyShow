package com.praveen.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Ticket extends  BaseModel{
    private int amount;
    private Date bookingDate;
    @ManyToOne
    private User bookedBy;
    @ManyToOne
    private Show show;
    @ManyToMany
    private List<Seat> seats;
    @OneToMany(mappedBy = "ticket")
    private List<Payment> payments;
    @Enumerated(EnumType.ORDINAL)
    private TicketStatus ticketStatus;
}
