package Controller;

public class RedeployingController {

    GameController gameCon;
    private FirebaseGameController fbGame;

    RedeployingController(GameController gameCon){
        this.gameCon = gameCon;
        fbGame = gameCon.getFireBase();

    }

    public void removeFiche() {
        AreaController activeArea = getActiveArea();
        PlayerController player = gameCon.getCurrentPlayer();

        if(activeArea != null){
            if(activeArea.getOwnerPlayer().getId().equals(player.getId())){
                if(activeArea.getFichesAmount() >= 1){
                    if(activeArea.getFichesAmount() == 1){
                        player.addPoints(-1);
                        player.getActiveCombination().getRace().removeArea(activeArea);
                    }
                }
            }
        }
    }

    public void addFiche() {
        AreaController activeArea = getActiveArea();
        PlayerController player = gameCon.getCurrentPlayer();

        if(activeArea != null){
            if(activeArea.getOwnerPlayer().getId().equals(player.getId())){
                if(player.getActiveCombination().getRace().hasEnoughFiches(1)){

                }
            }
        }
    }

    private AreaController getActiveArea(){
        AreaController activeArea = gameCon.getMapCon().getActiveArea();
        return activeArea;
    }
}
