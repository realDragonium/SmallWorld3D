package Enum;

public enum ApplicatieViewEnum {
    LOGIN("login"), GAME("game"), HOMESCREEN("homescreen");
    private String view;

    ApplicatieViewEnum(String view){
        this.view = view;
    }

    public String getStringValue(){
        return view;
    }
}
