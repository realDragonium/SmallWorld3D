package Controller;

import Enums.GameViewEnum;
import Enums.NotificationEnum;
import Objects.SpecialFXMLLoader;
import View.AttackView;
import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;
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

    private void createAttackView() {
        new SpecialFXMLLoader().loader("/AttackView.fxml", (Callable<AttackView>) () -> new AttackView(this));
    }

    public void AttackArea(String areaID) {
        final AreaController area = gameCon.getMapCon().getAreaCon(areaID);
        final CombinationController combi = gameCon.getTurnCon().getCurrentCombi();
        int fiches = combi.getFichesAmount();
        int fichesNeeded = gameCon.getTurnCon().getCurrentCombi().fichesNeeded(area);
        gameCon.removeFromGameView(GameViewEnum.AREAINFO);
        if (fiches < fichesNeeded) notEnoughFiches(combi, area);
        else fbGame.attackAction(areaID);
    }

    private void notEnoughFiches(CombinationController combi, AreaController area) {
        if (!combi.hasOnlyOneFiche()) gameCon.setMessage(NotificationEnum.NOTENOUGHFICHES);
        else {
            int eyes = gameCon.getDiceCon().rollDice();
            fbGame.diceAction(eyes, area.getId());
            int needed = gameCon.getTurnCon().getCurrentCombi().fichesNeeded(area);

            TimerTask delayedAttack = new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        if(eyes + 1 >= needed)
                            fbGame.specificAttackAction(area.getId(), 1);
                        fbGame.nextPhaseAction();
                    });
                }
            };
            new Timer().schedule(delayedAttack, 4000);
        }
    }


//    public void removeFichesNeeded(int amount){
//        fichesCountNeeded -= amount;
//    }

//    public AreaController getAttackArea(){
//        return targetArea;
//    }

//    private void attackableAreas(PlayerController player, AreaController area, int fiches){
//        player.getActiveCombination().addArea(area);
//        area.attackArea(player.getActiveCombination().getRaceName().getFiches(fiches));
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
//                    if (player.getActiveCombination().getRaceName().getAllAreas().size() == 0) {
//                        if (area.isBorderArea()) {
//                            return true;
//                        }
//                        else{
//                            System.out.println("only able to attackableAreas from the outside");
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
//            System.out.println("is able to attackableAreas");
//            attackableAreas(player, area, fichesNeeded);
//        }
//        System.out.println("not able to attackableAreas");
//    }


}
