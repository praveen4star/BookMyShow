package com.praveen.bookmyshow.controllers;

import com.praveen.bookmyshow.dtos.BookTicketRequestDto;
import com.praveen.bookmyshow.dtos.BookTicketResponseDto;
import com.praveen.bookmyshow.exceptions.InvalidArgumentsException;
import com.praveen.bookmyshow.exceptions.SeatNotAvailableException;
import com.praveen.bookmyshow.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.praveen.bookmyshow.services.TicketService;

import java.awt.print.Book;
import java.util.concurrent.TimeoutException;

@Controller
public class BookTicketController {
    private TicketService ticketService;

    @Autowired
    public BookTicketController(
            TicketService ticketService
    ){
        this.ticketService = ticketService;
    }


    public BookTicketResponseDto bookTicket(BookTicketRequestDto bookTicketRequestDto) {
        BookTicketResponseDto bookTicketResponseDto = new BookTicketResponseDto();
        try{
            Ticket ticket = ticketService.bookTicket(
                    bookTicketRequestDto.getUserId(),
                    bookTicketRequestDto.getShowId(),
                    bookTicketRequestDto.getSeatIds()
            );
            bookTicketResponseDto.setStatus("SUCCESS");
            bookTicketResponseDto.setMessage("Ticket booked successfully");
            bookTicketResponseDto.setTicketId(ticket.getId());
            bookTicketResponseDto.setAmount(ticket.getAmount());
        }
        catch (InvalidArgumentsException | SeatNotAvailableException e) {
            throw new RuntimeException(e);
        }
        return bookTicketResponseDto;
    }
}
