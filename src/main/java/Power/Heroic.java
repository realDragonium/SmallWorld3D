package Power;

public class Heroic implements Power {

    @Override
    public String getName() {
        return "heroic";
    }

    @Override
    public int getFicheAmount() {
        return 5;
    }
}
