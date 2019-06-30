package Race;

public class Wizards implements Race {

    @Override
    public String getName() {
        return "wizards";
    }

    @Override
    public int getFicheAmount() {
        return 8;
    }
}