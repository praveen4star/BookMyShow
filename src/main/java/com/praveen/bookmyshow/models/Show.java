package com.praveen.bookmyshow.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Show extends BaseModel{
    private Movie movie;
    private Auditorium auditorium;
    private Date startTime;
    private Date endTime;
    private Language language;

}
