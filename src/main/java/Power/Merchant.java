package Power;

public class Merchant implements Power {

    @Override
    public String getName() {
        return "merchant";
    }

    @Override
    public int getFicheAmount() {
        return 0;
    }
}
