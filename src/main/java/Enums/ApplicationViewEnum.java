package Enums;

public enum ApplicationViewEnum {
    LOGIN("login"), GAME("game"), HOMESCREEN("homescreen");

    private String view;

    ApplicationViewEnum(String view){
        this.view = view;
    }

    public String getStringValue(){
        return view;
    }
}
