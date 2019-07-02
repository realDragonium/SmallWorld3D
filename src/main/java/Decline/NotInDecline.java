package Decline;

import Phase.*;

public class NotInDecline implements Decline {

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public Phase startAt() {
        return new Preparing();
    }
}
