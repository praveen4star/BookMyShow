package com.praveen.bookmyshow.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowSeatType extends BaseModel{
    private int amount;
    private SeatType seatType;
    private Show show;
}
