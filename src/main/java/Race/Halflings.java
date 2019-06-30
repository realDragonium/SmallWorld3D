package Race;

public class Halflings implements Race {

    @Override
    public String getName() {
        return "halflings";
    }

    @Override
    public int getFicheAmount() {
        return 9;
    }
}
