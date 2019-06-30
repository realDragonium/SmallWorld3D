package Enum;

import Phase.*;

public enum PhaseEnum {
    PREPARING(new Preparing()), NONE(new PhaseNone()), CONQUERING(new Conquering()),
    REDEPLOYING(new Redeploying());

    private Phase phase;
    PhaseEnum(Phase phase){
        this.phase = phase;
    }

    public Phase getPhase(){
        return phase;
    }
}
