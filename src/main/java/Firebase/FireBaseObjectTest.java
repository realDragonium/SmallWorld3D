package Firebase;

public class FireBaseObjectTest {

    private String race;
    private String power;

    public FireBaseObjectTest(){    }

    public FireBaseObjectTest(String race, String power){
        this.race = race;
        this.power = power;
    }

    public String getRace(){
        return race;
    }
    public String getPower(){
        return power;
    }
}
