package cinema;

import cinema.support_classes.Purchase;
import cinema.support_classes.ReturnSeat;
import cinema.support_classes.ReturnedTicket;
import cinema.support_classes.Token;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;


@RestController
public class CinemaController {

    private final Cinema cinema = new Cinema();
    private final Statistics statistics = new Statistics(cinema.getTotal_rows(), cinema.getTotal_columns());

    @GetMapping("/seats")
    public Cinema getTasks() {
        return cinema;
    }

    @PostMapping("/purchase")
    public Object makePurchase(@RequestBody Purchase purchase) {

        if (purchase.getColumn() < 1 || purchase.getRow() < 1 ||
                purchase.getColumn() > cinema.getTotal_columns() || purchase.getRow() > cinema.getTotal_rows() ) {
            return new ResponseEntity<>(Map.of("error", "The number of a row or a column is out of bounds!"),
                    HttpStatus.BAD_REQUEST);
        }

        List<Seat> allSeats = cinema.getAllSeats();

        for (Seat seat : allSeats) {
            if (seat.getIsAvailable() && (seat.getColumn() == purchase.getColumn() && seat.getRow() == purchase.getRow())) {
                seat.setIsAvailable(false);
                statistics.setCurrent_income(statistics.getCurrent_income() + seat.getPrice());
                statistics.setNumber_of_available_seats(statistics.getNumber_of_available_seats() - 1);
                statistics.setNumber_of_purchased_tickets(statistics.getNumber_of_purchased_tickets() + 1);
                return new ReturnSeat(seat.getToken(), seat.getRow(), seat.getColumn(), seat.getPrice());
            }
        }

        return new ResponseEntity<>(Map.of("error", "The ticket has been already purchased!"),
                HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/return")
    public Object returnTicket(@RequestBody Token token) {

        for (Seat seat : cinema.getAllSeats()) {
            if (seat.getToken().equals(token.getToken())) {
                seat.setIsAvailable(true);
                statistics.setCurrent_income(statistics.getCurrent_income() - seat.getPrice());
                statistics.setNumber_of_available_seats(statistics.getNumber_of_available_seats() + 1);
                statistics.setNumber_of_purchased_tickets(statistics.getNumber_of_purchased_tickets() - 1);
                return new ReturnedTicket(seat);
            }
        }

        return new ResponseEntity<>(Map.of("error", "Wrong token!"), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/stats")
    public Object returnTicket(@RequestParam(required = false) String password) {

        if ("super_secret".equals(password)) {
            return statistics;
        }

        return new ResponseEntity<>(Map.of("error", "The password is wrong!"), HttpStatus.UNAUTHORIZED);
    }
}
