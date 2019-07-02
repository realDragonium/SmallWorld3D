package Power;

public class Flying implements Power {

    @Override
    public String getName() {
        return "flying";
    }

    @Override
    public int getFicheAmount() {
        return 5;
    }
}
