package Model;

import Observable.infoObservable;
import Observer.infoObserver;

import java.util.ArrayList;
import java.util.List;

public class InfoModel implements infoObservable {
    private List<infoObserver> infoObservers = new ArrayList<>();
    private String currentText;
    public String Humans = "Each Farmland Region your" +
            " Humans occupy is worth 1 " +
            "bonus Victory coin, at the " +
            "end of your turn.";
    public String Wizards = "Each Magic Region your Wizards " +
            " occupy is worth 1 bonus Victory " +
            "coin, at the end of your turn.";
    public String Dwarves = "Each Mine Region your Dwarves " +
            "occupy is worth 1 bonus Victory " +
            "coin, at the end of your turn. " +
            "This power is kept even when " +
            "the Dwarves are In Decline.";
    public String Giants = "Your Giants may conquer any " +
            "Region adjacent to a Mountain " +
            "Region they occupy at a cost of 1 less Giant " +
            "token than normal. A minimum of 1 Giant " +
            "token is still required.";
    public String Tritons = "Your Tritons may conquer all Coastal Regions " +
            "(those bordering a Sea or Lake) at a cost of " +
            "1 less Triton token than normal. A minimum " +
            "of 1 Triton token is still required.";
    public String Ratmen = "No Race benefit; their sheer number of " +
            "tokens is enough!";
    public String pickRace = "The player selects one Race and Special Power combo, from" +
            "among the six that are visible on the table (including the " +
            "combo made of the Race banner and Special Power badge " +
            "sitting on top of the stacks at the bottom of the column). " +
            "The cost of each combo is determined by its position in the " +
            "column. The first combo - located at the top of the column - is " +
            "free. Each of the other combos, as you move in succession " +
            "down the column, costs one additional Victory coin. That cost " +
            "is paid by the player dropping one of his Victory coins on each " +
            "of the combos situated above the combo he wishes to pick. " +
            "If the combo a player selects contains some Victory coins " +
            "(dropped by players who previously passed up this combo), " +
            "the player pockets these coins; he must still drop one of his " +
            "own Victory coins on each of the combos located above the " +
            "one he selected, however. " +
            "The player places his combo selection face up in front of him, " +
            "and picks a number of matching Race tokens in the removable " +
            "storage tray equal to the sum of the values indicated on the " +
            "Race banner and its associated Special Power badge. " +
            "Unless stated otherwise (for example, Skeletons or Sorcerers) " +
            "these Race tokens are the only ones the player will be able to " +
            "deploy for this race during the course of the game. " +
            "If, on the other hand, a Special Power (or Race power) lets " +
            "you take additional Race tokens from the storage tray during " +
            "the course of the game, you are still limited by the total " +
            "number of tokens physically available. So a player with 18 " +
            "Sorcerer tokens on the board won't be able to use his Sorcerer " +
            "power again until some of his tokens become available. " +
            "Finally, the player replenishes the column of combos " +
            "available to others; this is achieved by sliding existing combos " +
            "(and the Victory coins sitting on them, if any) up one position " +
            "in the column, so as to fill the void, and revealing a new combo " +
            "from the top of the stack, if appropriate. There should thus " +
            "always be 6 combos visible to all players, on the table (within " +
            "the limit of Race banners and Special Power badges available " +
            "in the pile, once reshuffled if need be, of course).";
    public String firstConquest = "A player's race deploying on the map for the first time must " +
            "enter it by conquering one of its border Regions (i.e. a Region " +
            "adjacent to the edge of the board or one whose shore is on a Sea " +
            "adjacent to the edge of the board, even if the Sea is occupied by " +
            "a Seafaring Race).";
    public String conqueringRegion = "To conquer a Region, a player must have available to deploy: " +
            "2 Race tokens + 1 additional Race token for each Encampment, " +
            "Fortress, Mountain, or Troll's Lair marker + 1 additional Race token " +
            "for each Lost Tribe or other player's Race token already present in " +
            "the Region. Seas and Lakes cannot usually be conquered. " +
            "Upon conquering a Region, the player must deploy the Race " +
            "tokens he used to conquer this Region inside its borders on " +
            "the map. These tokens must remain in this Region until the " +
            "player reorganizes his troops at the end of this turn (see Troop " +
            "Redeployment, p. 5).";
    public String enemyLosses = "If Race tokens of another player occupied the Region prior " +
            "to its conquest, that player must immediately take all of these " +
            "Race tokens back in hand and: " +
            "l Permanently discard one Race token back into the Storage " +
            "tray; " +
            "l Keep the other Race tokens in hand, and redeploy them " +
            "in any other Region(s) still occupied by his race (if any) as " +
            "the final action of the current player's turn. " +
            "The Region(s) in which the remaining Race tokens, if any, " +
            "redeploy do not have to be adjacent or contiguous to the " +
            "Region(s) they fled from. If all of a player's Regions were " +
            "attacked this turn, leaving him with some Race tokens in hand " +
            "but PhaseNone on the board, he may redeploy these as if he was " +
            "doing a First Conquest, on his next turn. " +
            "When a Region defended by a single token is captured, the " +
            "defending token is discarded. This will usually be the case if the " +
            "defending token is a Lost Tribe or when the defending token " +
            "belongs to a Race In Decline (see Entering into Decline, p. 6). " +
            "Note: A player may choose to conquer a Region occupied by " +
            "his own In Decline token, if he wishes to: He will lose that " +
            "token, but might gain access to a Region that is more " +
            "profitable for his new Active Race tokens to occupy. " +
            "Mountains are immovable, and remain in place to provide " +
            "defense for their new conqueror.";
    public String followingConquests = "The active player can repeat this process to conquer as " +
            "many new Regions as he wishes during his turn, provided he " +
            "has enough Race tokens left to accomplish these successive " +
            "conquests. " +
            "Each of the newly conquered Regions must be adjacent to " +
            "(i.e. sharing a border with) a Region already occupied by his " +
            "active Race tokens, unless permitted otherwise by his Race and " +
            "Special Power combo.";
    public String finalConquest = "During the final conquest attempt of his turn, a player may " +
            "find himself with not enough Race tokens left to conquer " +
            "another Region outright. Provided he still has at least one " +
            "unused Race token, the player may attempt one final conquest " +
            "for his turn by selecting a Region that he would normally be 3 " +
            "or less Race tokens short to conquer. Once the Region is " +
            "selected, the player rolls the Reinforcement Die once. Note that " +
            "the Region the player wishes to make his last conquest target " +
            "for the turn must be selected before rolling the die. This Region " +
            "does not have to be the weakest one available for attack either, " +
            "provided it could still be conquered with a lucky die roll. " +
            "If the sum of the die rolled, combined with the Race token(s) " +
            "left in his possession, is high enough to conquer the Region, the " +
            "player deploys his remaining Race token(s) there. Otherwise, he " +
            "deploys his remaining token(s) in one of the Regions he already " +
            "occupied prior. Either way, his conquests for the turn end " +
            "immediately thereafter.";
    public String troopRedeployment = "Once a player's conquests for the turn have ended, he may " +
            "freely redeploy the Race tokens he has on the board, moving " +
            "them from one Region to any other Region occupied by his race " +
            "(not necessarily just an adjacent or contiguous Region), provided " +
            "that at least one Race token remains in each Region under his" +
            "control. ";
    public String victoryCoins = "His turn now complete, the player receives 1 coin from the " +
            "Victory stash for each Region his Race tokens occupy on the " +
            "map. The player may also collect additional Victory coins as a " +
            "result of his Race and/or Special Power benefit. " +
            "As the game progresses, a player will likely have some tokens " +
            "from another race on the map. These tokens are the remnants " +
            "of an earlier race he chose to put In Decline previously (see " +
            "Entering into Decline, p. 6). " +
            "The Regions these In Decline tokens occupy also each contribute " +
            "1 Victory coin to the player; Though the Race banner and Special " +
            "Power benefits no longer contribute any bonus coins, unless " +
            "explicitly stated otherwise in said Race or Special Power benefit. " +
            "Players keep their Victory coins stacked together, their value " +
            "hidden from other players at all times; final scores are not " +
            "revealed until the end of the game. If necessary, a player may " +
            "request change from the Victory stash for his coins at any time.";
    public String decline = "Once a player thinks that his Active race is overextended and no longer has the impetus required to" +
            "continue expanding successfully or defend itself from " +
            "increasingly threatening neighbors, he may choose to put it In " +
            "Decline by selecting a new Race and Special Power combo from " +
            "those available on the table at the start of his next turn. " +
            "To do so, the player flips his current Race banner upside down, " +
            "so that the grayed-out In Decline side becomes visible to all, " +
            "and discards the Special Power badge that was associated with " +
            "it as that Special Power badge is no longer in effect, unless " +
            "dictated otherwise (e.g. Spirit Special Power). " +
            "He also flips a single Race token onto its In Decline side in " +
            "each Region those tokens occupied and removes all other " +
            "tokens of this Race from the map, placing them back into the " +
            "storage tray. " +
            "Each player can only have a single race In Decline on the map " +
            "at any given time. If the player still has tokens from an earlier " +
            "In Decline race left on the map, those are all immediately " +
            "removed from the map and placed back in the storage tray, " +
            "before flipping the new tokens into Decline. " +
            "The Race banner of the now vanished race is placed at the " +
            "bottom of the stack of Race banners, or in the lowest empty " +
            "slot in the banner column, if any. The same is also done when " +
            "the last token of a race In Decline is wiped off the map as the " +
            "result of their last Region being conquered. " +
            "The player can make no conquests during the turn his race " +
            "goes into decline; his turn ends immediately after scoring! He " +
            "gains 1 Victory coin for each Region his newly In Decline tokens " +
            "occupy, but unless stated otherwise, he scores no Victory coins " +
            "from his now In Decline Race banner power or discarded Special " +
            "Power benefits. " +
            "On his next turn, the player will select a new Race and Special " +
            "Power combo from among those now available to him. He then " +
            "follows the same rules used during the first turn of the game. " +
            "The only difference, but it's a sizeable one, is that the player " +
            "will now likely collect Victory coins from his new race as well as " +
            "from left-over tokens of his now In Decline race, during the " +
            "Scoring Victory coins phase of his turn. " +
            "These Merchant Skeletons were bad to the bone! " +
            "Their remnants are now wiped off the map and their Race banner " +
            "placed back at the bottom of the pile. " +
            "SW Rules EN_Mise en page 1 19/05/15 13:49 Page7 " +
            "7 " +
            "In the unlikely event there are not enough Special Power " +
            "badges left in the Special Powers stack to keep putting new " +
            "Race and Special Power combos on the table, shuffle previously " +
            "discarded Special Power badges into a new stack.";
    public String endGame = "Once the Game Turn marker has reached its final spot on the " +
            "Game Turn track and all players have had a chance to play a " +
            "final turn, the Victory coins held by each player are revealed " +
            "and tallied. The player with the highest score wins. In the case " +
            "of a tie, the player with the most Race tokens (Active + In " +
            "Decline) still on the board is the winner.";
    public String readyTroops = "Leaving in place one Race token in each Region they occupy, " +
            "the player may take all his other Active Race tokens from the " +
            "map back in hand and use them to conquer new Region.";
    public String conquer = "All rules relative to the conquest of a new Region (see " +
            "Conquering Regions, p. 4) must be respected, with the exception " +
            "of the rule relative to the First Conquest - which only applies to " +
            "new races entering on the map.";
    public String abandoningRegion = "Only those Race tokens that were taken back in hand may " +
            "be used to conquer new Regions. If a player wishes to free up " +
            "some more Race tokens, he may opt to entirely empty up " +
            "some - or all - Regions, leaving no Tokens there; but in this " +
            "case, these now abandoned Regions will no longer be " +
            "considered his, nor bring him any Victory coins. If the player " +
            "chooses to abandon all the Regions he previously occupied, " +
            "his next conquest must follow the same rules as its First " +
            "Conquest (see First Conquest, p. 4).";

    @Override
    public void register(infoObserver ob) {
        infoObservers.add(ob);
    }

    @Override
    public void notifyAllObs() {
        for (infoObserver obs : infoObservers) {
            obs.update(this);
        }
    }

    public void showInfo(String string) {
        currentText = string;
        notifyAllObs();
    }

    @Override
    public String currentText() {
        return currentText;
    }
}
