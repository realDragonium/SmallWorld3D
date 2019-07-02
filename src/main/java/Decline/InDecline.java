package Decline;

import Phase.*;

public class InDecline implements Decline {
    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public Phase startAt() {
        return new Redeploying();
    }
}
