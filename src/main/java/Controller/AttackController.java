package Controller;

import Enum.TurnFase;
import Managers.SceneManager;

import java.util.List;

public class AttackController {

    private GameController gameCon;
    private AreaController targetArea;
    private int fichesCountNeeded;
    private boolean diceUsed = false;

    AttackController(GameController gameCon) {

        this.gameCon = gameCon;
//        SceneManager.getInstance().loadAttack(this);
    }

    public void removeFichesNeeded(int amount){
        fichesCountNeeded -= amount;
    }

    public AreaController getAttackArea(){
        return targetArea;
    }

    private void getTargetArea() {
        targetArea = gameCon.getMapCon().getActiveAreas().get(0);
    }

    private void attackAreaLocal() {
        fichesCountNeeded = targetArea.numbersNeeded();
        final PlayerController player = gameCon.getCurrentPlayer();
        if(player.hasActiveCombination()){
            player.getActiveCombination().checkForSpecialActions(TurnFase.conquering);
            if (player.getActiveCombination().getRace().hasEnoughFiches(fichesCountNeeded)) {
                if(player.getActiveCombination().getRace().getAllAreas().size() == 0) firstAttack(player);
                else isNeighbour(player);
            }
            else if(player.getActiveCombination().getRace().fichesCount() == 1 && !diceUsed){
                int waarde = gameCon.getDiceCon().ClickedDice();
                diceUsed = true;
                if (player.getActiveCombination().getRace().hasEnoughFiches(fichesCountNeeded - waarde)) {
                    attack(player, 1);
                }
            }
        }
    }

    private void firstAttack(PlayerController player){
        if(targetArea.firstAttackArea()){
            attack(player, fichesCountNeeded);
        }
    }

    private void attack(PlayerController player, int fiches){
        if(!targetArea.isAttackAble() || targetArea.getOwnerPlayer() == gameCon.getMyPlayer()) return;
        if (targetArea.getOwnerPlayer() != null) {
            targetArea.getOwnerPlayer().getActiveCombination().getRace().pushFiches(targetArea.removeFiches());
            targetArea.getOwnerPlayer().getActiveCombination().getRace().removeArea(targetArea);
        }
        player.getActiveCombination().getRace().addArea(targetArea);
        targetArea.attackArea(player.getActiveCombination().getRace().getFiches(fiches));
        targetArea.setPlayerOwner(player);
    }

    public void attackCountry() {
        getTargetArea();
        attackAreaLocal();
    }

    private void isNeighbour(PlayerController player){
        if(isNeighbour(player.getActiveCombination().getRace().getAllAreas())){
            attack(player, fichesCountNeeded);
        }
    }

    private boolean isNeighbour(List<AreaController> areas){
        String Id = targetArea.getId();
        for(AreaController area : areas){
            if(area.getNeighbours().contains(Id)) return true;
        }
        return false;
    }

}
