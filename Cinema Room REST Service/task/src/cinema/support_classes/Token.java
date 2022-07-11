package cinema.support_classes;

import java.util.UUID;

public class Token {

    private UUID token;

    public Token() {
        // Constructor for @PostMapping
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
}
