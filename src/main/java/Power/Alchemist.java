package Power;

public class Alchemist implements Power {

    @Override
    public String getName() {
        return "alchemist";
    }

    @Override
    public int getFicheAmount() {
        return 4;
    }
}
