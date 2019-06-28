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
        targetArea = gameCon.getMapCon().getActiveArea();
    }

    private void attackAreaLocal() {

    }

    private void attack(PlayerController player, AreaController area, int fiches){
        player.getActiveCombination().getRace().addArea(area);
//        area.attackArea(player.getActiveCombination().getRace().getFiches(fiches));
        area.setPlayerOwner(player);
    }

    public void attackCountry() {
        AreaController area = gameCon.getMapCon().getActiveArea();
        gameCon.getCurrentPlayer().getActiveCombination().attackThisArea(area);
    }

    private boolean isNeighbour(AreaController area, PlayerController player){
        for(AreaController areaCon : player.getActiveCombination().getRace().getAllAreas()){
            if(areaCon.getNeighbours().contains(area.getId())) return true;
        }
        return false;
    }

    private boolean isAbleToAttack(PlayerController player, int fichesNeeded, AreaController area){

        if(area.getOwnerPlayer() != null) {
            if (area.getOwnerPlayer().equals(gameCon.getMyPlayer())) return false;
        }
        if(area.isAttackAble()) {
            if (player.hasActiveCombination()) {
                if (player.hasEnoughFiches(fichesNeeded)) {
                    if (player.getActiveCombination().getRace().getAllAreas().size() == 0) {
                        if (area.firstAttackArea()) {
                            return true;
                        }
                        else{
                            System.out.println("only able to attack from the outside");
                        }
                    } else {
                        if (isNeighbour(area, player)) {
                            return true;
                        }
                        else{
                            System.out.println("not a neighbour");
                        }
                    }
                }
                else{
                    System.out.println("not enough fiches");
                }
            }
            else{
                System.out.println("no active race");
            }
        }
        else{
            System.out.println("not attackable");
        }
        return false;
    }

    public void attackArea(AreaController area) {
        PlayerController player = gameCon.getCurrentPlayer();
        int deffence = area.getDefenceValue();
        int fichesNeeded = deffence + 2;
        //ask for powers and stuff!
        System.out.println(player.getId());
        if(isAbleToAttack(player, fichesNeeded, area)){
            System.out.println("is able to attack");
            attack(player, area, fichesNeeded);
        }
        System.out.println("not able to attack");
    }


}
