package Race;

public class Ghouls implements Race {

    @Override
    public String getName() {
        return "ghouls";
    }

    @Override
    public int getFicheAmount() {
        return 5;
    }
}
