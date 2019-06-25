package Model;

public class GameModel {

    private int numberOfRounds;
    private int turnsARound;
    public boolean gameEnded = false;

    public GameModel(int rounds, int turn){
        numberOfRounds = rounds;
        turnsARound = turn;
    }

    public int getNumberOfRounds(){
        return numberOfRounds;
    }
    public int getTurnsARound(){
        return turnsARound;
    }
}
