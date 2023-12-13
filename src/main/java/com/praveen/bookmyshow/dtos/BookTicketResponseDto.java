package com.praveen.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookTicketResponseDto {
    private String status;
    private Long ticketId;
    private int amount;
    private String message;
    private List<Long> seatNumber;


}
