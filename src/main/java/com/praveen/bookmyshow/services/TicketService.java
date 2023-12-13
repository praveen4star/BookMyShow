package com.praveen.bookmyshow.services;


import com.praveen.bookmyshow.exceptions.InvalidArgumentsException;
import com.praveen.bookmyshow.exceptions.SeatNotAvailableException;
import com.praveen.bookmyshow.models.*;
import com.praveen.bookmyshow.repositories.SeatRepository;
import com.praveen.bookmyshow.repositories.ShowRepository;
import com.praveen.bookmyshow.repositories.ShowSeatRepository;
import com.praveen.bookmyshow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private SeatRepository seatRepository;
    private ShowSeatRepository showSeatRepository;
    private ShowRepository showRepository;
    private UserRepository userRepository;
    @Autowired
    public TicketService(
            SeatRepository seatRepository,
            ShowSeatRepository showSeatRepository,
            ShowRepository showRepository,
            UserRepository userRepository

    ){
        this.seatRepository = seatRepository;
        this.showSeatRepository = showSeatRepository;
        this.showRepository = showRepository;
        this.userRepository = userRepository;
    }
    public Ticket bookTicket(
            Long userId,
            Long showId,
            List<Long> seatIds
    ) throws InvalidArgumentsException, SeatNotAvailableException {
        /*
        * get the seat info from the seat repo
        * get the show info from the show repo
        * get the showSeat Info from the showSeat repo
        * take lock on showSeat
        * check if the show Seat are available
        *      Or -> if it is blocked and the time is more than 5 mins, then
        *
        * else throw an exception
        * */
        List<Seat> seats = seatRepository.findAllByIdIn(seatIds);
        Optional<Show> showOptional = showRepository.findById(showId);
        if(showOptional.isEmpty()){
            throw new InvalidArgumentsException(
                    "Show by: " + showId + " doesn't exist."
            );
        }
        Show show = showOptional.get();
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new InvalidArgumentsException(
                    "User by: " + userId + " doesn't exist."
            );
        }
        User user = userOptional.get();
        List<ShowSeat> showSeats = getShowSeats(seats, show);
        Ticket ticket = new Ticket();
        ticket.setBookedBy(user);
        ticket.setTicketStatus(TicketStatus.PROCESSING);
        ticket.setBookingDate(new Date());
        ticket.setSeats(seats);
        ticket.setShow(show);
        ticket.setAmount(0);
        return ticket;

    }

    @Transactional(isolation = Isolation.SERIALIZABLE, timeout = 2)
    public List<ShowSeat> getShowSeats(List<Seat> seats, Show show) throws SeatNotAvailableException {
        List<ShowSeat> showSeats = showSeatRepository.findAllBySeatInAndShow( seats,show);
        for( ShowSeat showSeat: showSeats){
            if(!(showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE) ||
                    (showSeat.getShowSeatStatus().equals(ShowSeatStatus.BLOCKED) &&
                            Duration.between(showSeat.getLockedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalTime.now()).toMinutes() >  5))){
                throw new SeatNotAvailableException( "Seat: " + showSeat.getSeat().getId() + " is not available.");
            }
        }
        List<ShowSeat> savedShowSeats = new ArrayList<>();
        for( ShowSeat showSeat: showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            showSeat.setLockedAt(new Date());
            savedShowSeats.add(showSeatRepository.save(showSeat));
        }
        return savedShowSeats;
    }
}
