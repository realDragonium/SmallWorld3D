package Race;

public class Elves implements Race {

    @Override
    public String getName() {
        return "elves";
    }

    @Override
    public int getFicheAmount() {
        return 8;
    }
}
