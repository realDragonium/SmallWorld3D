package Objects;

import Controller.CombinationController;

public class AlchemistPowerOld implements PowerOld {

    private String id = "alchemist";
    private CombinationController combiCon;

    @Override
    public void doAction(){
        combiCon.getPlayer().addPoints(2);
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
