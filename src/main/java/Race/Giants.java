package Race;

public class Giants implements Race {

    @Override
    public String getName() {
        return "ghouls";
    }

    @Override
    public int getFicheAmount() {
        return 7;
    }
}
