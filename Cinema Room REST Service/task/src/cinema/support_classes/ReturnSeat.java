package cinema.support_classes;

import java.util.UUID;

public class ReturnSeat {

    private final UUID token;
    private final Ticket ticket;

    public ReturnSeat(UUID token, int row, int column, int price) {
        this.token = token;
        this.ticket = new Ticket(row, column, price);
    }

    public UUID getToken() {
        return token;
    }

    public Ticket getTicket() {
        return ticket;
    }

    private class Ticket {
        private final int row;
        private final int column;
        private final int price;

        public Ticket(int row, int column, int price) {
            this.row = row;
            this.column = column;
            this.price = price;
        }

        public int getRow() {
            return row;
        }
        public int getColumn() {
            return column;
        }

        public int getPrice() {
            return price;
        }
    }
}
