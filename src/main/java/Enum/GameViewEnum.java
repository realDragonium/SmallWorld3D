package Enum;

public enum GameViewEnum {
    MAP2D("map2D"), SHOP("shop"), MAP3D("map3D"), PLAYER("players"), ROUND("round"), TURN("turn"),
    BUTTON("button"), TIMER("timer"), VERVAL("verval"), DICE("dice"), REDEPLOY("redeploy"), INFO("info"),
    ATTACK("attack"), AREAINFO("areaInfo");

    private String view;

    GameViewEnum(String view){
        this.view = view;
    }

    public String getStringValue(){
        return view;
    }
}
