package Decline;

public class NotInDecline implements Decline {

    @Override
    public boolean isActive() {
        return true;
    }

}
