package Enum;

import Phase.*;

public enum PhaseEnum {
    PREPARING(new Preparing()), CONQUERING(new Conquering()),
    REDEPLOYING(new Redeploying()), SPECTATEPREPARING(new SpectatePreparing()),
    SPECTATERECONQUERING(new SpectateConquering()),
    SPECTATEREDEPLOYING(new SpectateRedeploying());

    private Phase phase;
    PhaseEnum(Phase phase){
        this.phase = phase;
    }

    public Phase getPhase(){
        return phase;
    }
}
