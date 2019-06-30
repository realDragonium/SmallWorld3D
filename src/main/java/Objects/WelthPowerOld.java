package Objects;

import Controller.CombinationController;

public class WelthPowerOld implements PowerOld {

    private String id = "wealthy";
    private boolean used = false;
    private CombinationController combiCon;

    @Override
    public void doAction(){
        if(!used){
            combiCon.getPlayer().addPoints(7);
            used = true;
        }
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setCombiCon(CombinationController combiCon) {
        this.combiCon = combiCon;
    }
}
