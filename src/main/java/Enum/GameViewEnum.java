package Enum;

public enum GameViewEnum {
    MAP("map"), SHOP("shop");

    private String view;

    GameViewEnum(String view){
        this.view = view;
    }

    public String getStringValue(){
        return view;
    }
}
