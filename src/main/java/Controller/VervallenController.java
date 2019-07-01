package Controller;

public class VervallenController {

    GameController gameCon;

    VervallenController(GameController gameCon){
        this.gameCon = gameCon;
    }


    public void inVerval() {
        makeCombinationNonActive();
    }

    private void makeCombinationNonActive(){
        gameCon.getFireBase().declineAction();

    }


}
