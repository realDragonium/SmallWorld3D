package Race;

public class Humans implements Race {

    @Override
    public String getName() {
        return "humans";
    }

    @Override
    public int getFicheAmount() {
        return 7;
    }
}
