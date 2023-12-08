package com.praveen.bookmyshow.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class City extends BaseModel{
    private String name;
    private List<Theatre> theatres;

}
