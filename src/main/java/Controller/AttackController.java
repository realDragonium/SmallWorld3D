package Controller;

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

    }

    private void attack(PlayerController player, AreaController area, int fiches){
        player.getActiveCombination().getRace().addArea(area);
        area.attackArea(player.getActiveCombination().getRace().getFiches(fiches));
        area.setPlayerOwner(player);
    }

    public void attackCountry() {
        getTargetArea();
        attackAreaLocal();
    }

    private boolean isNeighbour(AreaController area, PlayerController player){
        for(AreaController areaCon : player.getActiveCombination().getRace().getAllAreas()){
            if(areaCon.getNeighbours().contains(area.getId())) return true;
        }
        return false;
    }

    private boolean isAbleToAttack(PlayerController player, int fichesNeeded, AreaController area){
        if((!area.isAttackAble() || area.getOwnerPlayer().equals(gameCon.getMyPlayer()))) {
            if (player.hasActiveCombination()) {
                if (player.getActiveCombination().getRace().hasEnoughFiches(fichesNeeded)) {
                    if (player.getActiveCombination().getRace().getAllAreas().size() == 0) {
                        if (area.firstAttackArea()) {
                            return true;
                        }
                    } else {
                        if (isNeighbour(area, player)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void attackArea(AreaController area) {
        PlayerController player = gameCon.getCurrentPlayer();
        int deffence = area.getDefenceValue();
        int fichesNeeded = deffence + 2;
        //ask for powers and stuff!
        if(isAbleToAttack(player, fichesNeeded, area)){
            attack(player, area, fichesNeeded);
        }
    }
}
