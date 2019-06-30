package Enum;

public enum NotificationEnum {
    YES("YESSSSS"), NO("NOOOOOOOOOOOOOOOOO"),
    DRAGON("WATCH OUT!!! THERE IS A DRAGON!!!");

    private String message;

    NotificationEnum(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
