package com.praveen.bookmyshow.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Theatre extends  BaseModel{
    private String name;
    private List<Auditorium> auditoriums;
    private String address;
    private City city;
}
