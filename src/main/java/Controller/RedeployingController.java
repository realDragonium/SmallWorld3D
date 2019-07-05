package Controller;

import Enums.GameViewEnum;
import Enums.NotificationEnum;
import Objects.SpecialFXMLLoader;
import View.RedeployingView;

import java.util.concurrent.Callable;

public class RedeployingController {

    GameController gameCon;
    private FirebaseGameController fbGame;

    RedeployingController(GameController gameCon){
        this.gameCon = gameCon;
        fbGame = gameCon.getFireBase();
        createRedeployView();
    }

    private void createRedeployView() {
        new SpecialFXMLLoader().loader("/RedeployingView.fxml", (Callable<RedeployingView>) () -> new RedeployingView(this));
    }

    public void removeFiche(String areaId){
        closeAreaInfo();
        if(gameCon.getMapCon().getAreaCon(areaId).getFichesAmount() > 1) fbGame.removeFicheAction(areaId);
        else gameCon.setMessage(NotificationEnum.YOUCANTLEAVE);
    }

    public void addFiche(String areaId){
        closeAreaInfo();
        if(gameCon.getTurnCon().getCurrentCombi().getFichesAmount() > 0) fbGame.addsFicheAction(areaId);
        else gameCon.setMessage(NotificationEnum.NOTENOUGHFICHES);
    }

    private void closeAreaInfo(){
        gameCon.removeFromGameView(GameViewEnum.AREAINFO);
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
//                        player.getActiveCombination().getRaceName().removeArea(activeArea);
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
//                if(player.getActiveCombination().getRaceName().hasEnoughFiches(1)){
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
