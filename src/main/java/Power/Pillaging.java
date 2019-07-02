package Power;

public class Pillaging implements Power {

    @Override
    public String getName() {
        return "pillaging";
    }

    @Override
    public int getFicheAmount() {
        return 5;
    }
}
