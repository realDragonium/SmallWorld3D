package Special.AttackPhase;

import Controller.AreaController;
import Controller.GameController;

public interface AttackPhase {
    void doAction(GameController gameCon, AreaController area);
}
