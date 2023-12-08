package com.praveen.bookmyshow.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Seat extends BaseModel{
    private String seat_num;
    private SeatType seatType;
    private int rowVal;
    private int colVal;
}
