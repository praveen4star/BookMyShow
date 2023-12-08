package com.praveen.bookmyshow.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Auditorium extends  BaseModel{
    private String name;
    private List<Seat> seats;
    private List<AuditoriumFeature> features;
}
