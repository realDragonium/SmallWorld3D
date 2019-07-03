package Enums;

public enum NotificationEnum {
    YES("YESSSSS"), NO("NOOOOOOOOOOOOOOOOO"),
    DRAGON("WATCH OUT!!! THERE IS A DRAGON!!!"),
    NOTENOUGHFICHES("YOU DONT HAVE ENOUGH FICHES"),
    YOUCANTLEAVE("YOU CANT LEAVE A AREA IN THIS PHASE");

    private String message;

    NotificationEnum(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
