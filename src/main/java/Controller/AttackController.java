package Controller;

import Enums.GameViewEnum;
import Enums.NotificationEnum;
import Objects.SpecialFXMLLoader;
import View.AttackView;

import java.util.concurrent.Callable;

public class AttackController {

    private GameController gameCon;
    private AreaController targetArea;
    private FirebaseGameController fbGame;

    AttackController(GameController gameCon) {
        this.gameCon = gameCon;
        fbGame = gameCon.getFireBase();
        createAttackView();
    }

    private void createAttackView(){
        new SpecialFXMLLoader().loader("/AttackView.fxml", (Callable<AttackView>)() -> new AttackView(this));
    }

    public void AttackArea(String areaID) {
        int fiches = gameCon.getCurrentPlayer().getCurrentCombi().getFichesAmount();
        int fichesNeeded = gameCon.getMapCon().getAreaCon(areaID).getDefenceValue();
        gameCon.removeFromGameView(GameViewEnum.AREAINFO);
        if(fiches < fichesNeeded) gameCon.setMessage(NotificationEnum.NOTENOUGHFICHES);
        else fbGame.attackAction(areaID);
    }

//    public void removeFichesNeeded(int amount){
//        fichesCountNeeded -= amount;
//    }

//    public AreaController getAttackArea(){
//        return targetArea;
//    }

//    private void attack(PlayerController player, AreaController area, int fiches){
//        player.getActiveCombination().addArea(area);
//        area.attackArea(player.getActiveCombination().getRace().getFiches(fiches));
//        area.setPlayerOwner(player);
//    }

//    public void attackCountry() {
//        AreaController area = gameCon.getMapCon().getActiveArea();
//        gameCon.getCurrentPlayer().getActiveCombination().attackThisArea(area);
//    }

    //DEPRECATED
//    private boolean isNeighbour(AreaController area, PlayerController player){
//        for(AreaController areaCon : player.getActiveCombination()){
//            if(areaCon.getNeighbours().contains(area.getId())) return true;
//        }
//        return false;
//    }

    //DEPRECATED
//    private boolean isAbleToAttack(PlayerController player, int fichesNeeded, AreaController area){
//
//        if(area.getOwnerPlayer() != null) {
//            if (area.getOwnerPlayer().equals(gameCon.getMyPlayer())) return false;
//        }
//        if(area.isAttackAble()) {
//            if (player.hasActiveCombination()) {
//                if (player.hasEnoughFiches(fichesNeeded)) {
//                    if (player.getActiveCombination().getRace().getAllAreas().size() == 0) {
//                        if (area.isBorderArea()) {
//                            return true;
//                        }
//                        else{
//                            System.out.println("only able to attack from the outside");
//                        }
//                    } else {
//                        if (isNeighbour(area, player)) {
//                            return true;
//                        }
//                        else{
//                            System.out.println("not a neighbour");
//                        }
//                    }
//                }
//                else{
//                    System.out.println("not enough fiches");
//                }
//            }
//            else{
//                System.out.println("no active race");
//            }
//        }
//        else{
//            System.out.println("not attackable");
//        }
//        return false;
//    }

//    public void attackArea(AreaController area) {
//        PlayerController player = gameCon.getCurrentPlayer();
//        int deffence = area.getDefenceValue();
//        int fichesNeeded = deffence + 2;
//        //ask for powers and stuff!
//        System.out.println(player.getId());
//        if(isAbleToAttack(player, fichesNeeded, area)){
//            System.out.println("is able to attack");
//            attack(player, area, fichesNeeded);
//        }
//        System.out.println("not able to attack");
//    }


}
