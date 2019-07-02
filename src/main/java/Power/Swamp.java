package Power;

public class Swamp implements Power {

    @Override
    public String getName() {
        return "swamp";
    }

    @Override
    public int getFicheAmount() {
        return 4;
    }
}
