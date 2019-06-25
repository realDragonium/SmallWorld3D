package Controller;

import Firebase.FirebaseServiceOwn;
import Managers.SceneManager;

/** This controller-class controlls the logic of the methods that can be called in the Redeploying phase
 *
 * @author yoran
 * @version June 2019
 *
 */

public class RedeployingController {

    GameController gameCon;
    private FirebaseServiceOwn fb = SceneManager.getInstance().getApp().getFirebaseService();

    RedeployingController(GameController gameCon){
        this.gameCon = gameCon;
        SceneManager.getInstance().loadRedeploying(this);

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
                    player.getActiveCombination().getRace().addFiche(activeArea.getOneFiche());
                    fb.areaUpdateFiches(activeArea.getId(), activeArea.getFichesAmount());
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
                    activeArea.addFiche(player.getActiveCombination().getRace().removeFiche());
                    fb.areaUpdateFiches(activeArea.getId(), activeArea.getFichesAmount());
                }
            }
        }
    }

    /**
     *
     * @return returns the selected area
     */

    private AreaController getActiveArea(){
        AreaController activeArea = null;

        if(gameCon.getMapCon().getActiveAreas().size() > 0){
            activeArea = gameCon.getMapCon().getActiveAreas().get(0);
        }
        return activeArea;
    }
}
