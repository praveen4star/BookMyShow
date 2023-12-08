package com.praveen.bookmyshow.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ShowSeat extends  BaseModel{
    private Show show;
    private Seat seat;
    private ShowSeatStatus showSeatStatus;
    private Date lockedAt;
}
