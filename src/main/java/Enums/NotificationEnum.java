package Enums;

public enum NotificationEnum {
    YES("YESSSSS"), NO("NOOOOOOOOOOOOOOOOO"),
    DRAGON("WATCH OUT!!! THERE IS A DRAGON!!!"),
    NOTENOUGHFICHES("YOU DONT HAVE ENOUGH FICHES"),
    YOUCANTLEAVE("YOU CANT LEAVE A AREA IN THIS PHASE"),
    RATMEN("NO RACE BENEFIT; THEIR SHEER NUMBER OF TOKENS IS ENOUGH!"),
    DICENOTENOUGH("You didn't throw high enough!");


    private String message;

    NotificationEnum(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
