package com.praveen.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeatType extends BaseModel{
    private int price;
    @ManyToOne
    private SeatType seatType;
    @ManyToOne
    private Show show;
}
