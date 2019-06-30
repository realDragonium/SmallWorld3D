package Race;

public class Dwarves implements Race {

    @Override
    public String getName() {
        return "dwarves";
    }

    @Override
    public int getFicheAmount() {
        return 5;
    }
}
