package Model;

public class AccountModel {

    private String accountName;
    private String playerId;

    public AccountModel(String name){
        accountName = name;
    }

    public String getAccountName(){
        return accountName;
    }

    public String getPlayerId(){
        return playerId;
    }

    public void setPlayerId(String id){
        playerId = id;
    }
}
