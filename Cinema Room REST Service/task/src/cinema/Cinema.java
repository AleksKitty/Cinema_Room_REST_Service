package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class Cinema {

    // parameters of the cinema
    private final int total_rows = 9;
    private final int total_columns = 9;

    @JsonIgnore
    private final List<Seat> allSeats = new ArrayList<>();

    private List<Seat> available_seats;

    public Cinema() {
        for (int i = 1; i <= total_columns; i++) {
            for (int j = 1; j <= total_rows; j++) {
                allSeats.add(new Seat(j, i));
            }
        }
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public List<Seat> getAllSeats() {
        return allSeats;
    }

    public List<Seat> getAvailable_seats() {
        available_seats = new ArrayList<>();

        for (Seat seat: allSeats) {
            if (seat.getIsAvailable()) {
                available_seats.add(seat);
            }
        }
        return available_seats;
    }
}
