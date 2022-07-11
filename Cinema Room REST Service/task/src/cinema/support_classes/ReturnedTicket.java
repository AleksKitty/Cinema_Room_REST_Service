package cinema.support_classes;

import cinema.Seat;

public class ReturnedTicket {

    private final Seat returned_ticket;

    public ReturnedTicket(Seat seat) {
        this.returned_ticket = seat;
    }

    public Seat getReturned_ticket() {
        return returned_ticket;
    }
}
