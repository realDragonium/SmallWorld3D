package Controller;

import Objects.FXMLLOADER;
import View.RedeployingView;
import javafx.scene.Group;

import java.util.concurrent.Callable;

public class RedeployingController {

    GameController gameCon;
    private FirebaseGameController fbGame;

    RedeployingController(GameController gameCon){
        this.gameCon = gameCon;
        createRedeployView();
    }

    private void createRedeployView() {
        new FXMLLOADER().loader("/RedeployingView.fxml", (Callable<RedeployingView>) () -> new RedeployingView(this));
    }

    //DEPRECATED
//    public void removeFiche() {
//        AreaController activeArea = getActiveArea();
//        PlayerController player = gameCon.getCurrentPlayer();
//
//        if(activeArea != null){
//            if(activeArea.getOwnerPlayer().getId().equals(player.getId())){
//                if(activeArea.getFichesAmount() >= 1){
//                    if(activeArea.getFichesAmount() == 1){
//                        player.addPoints(-1);
//                        player.getActiveCombination().getRace().removeArea(activeArea);
//                    }
//                }
//            }
//        }
//    }

    //DEPRECATED
//    public void addFiche() {
//        AreaController activeArea = getActiveArea();
//        PlayerController player = gameCon.getCurrentPlayer();
//
//        if(activeArea != null){
//            if(activeArea.getOwnerPlayer().getId().equals(player.getId())){
//                if(player.getActiveCombination().getRace().hasEnoughFiches(1)){
//
//                }
//            }
//        }
//    }

//    private AreaController getActiveArea(){
//        AreaController activeArea = gameCon.getMapCon().getActiveArea();
//        return activeArea;
//    }
}
