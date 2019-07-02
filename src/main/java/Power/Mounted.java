package Power;

public class Mounted implements Power {

    @Override
    public String getName() {
        return "mounted";
    }

    @Override
    public int getFicheAmount() {
        return 5;
    }
}
