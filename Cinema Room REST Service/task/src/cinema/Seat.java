package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class Seat {
    @JsonIgnore
    private final UUID token;
    @JsonIgnore
    private boolean isAvailable;
    private final int row;
    private final int column;
    private final int price;

    public Seat(int row, int column) {
        this.token = UUID.randomUUID();
        this.isAvailable = true;

        this.row = row;
        this.column = column;
        if (row <= 4) {
            this.price = 10;
        } else {
            this.price = 8;
        }
    }

    public UUID getToken() {
        return token;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean available) {
        isAvailable = available;
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
