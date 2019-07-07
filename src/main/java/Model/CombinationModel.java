package Model;

import Attacks.*;
import Controller.AreaController;
import Controller.FicheController;
import Controller.PlayerController;
import Decline.Decline;
import Decline.InDecline;
import Decline.NotInDecline;
import Defend.Defend;
import Defend.NormalDefend;
import Enums.PowerEnum;
import Enums.RaceEnum;
import Observable.CombinationObservable;
import Observer.CombinationObserver;
import Points.NormalPoints;
import Points.NullPoints;
import Points.Points;
import Power.Power;
import Race.Race;
import Special.SpecialAction;
import Special.SpecialNone;
import javafx.scene.transform.Translate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class CombinationModel implements CombinationObservable {
    private List<CombinationObserver> observers = new ArrayList<>();

    public PlayerController player;
    public Stack<FicheController> raceFiches = new Stack<>();
    public HashSet<AreaController> areas = new HashSet<>();
    public List<AreaController> thisRoundConquered = new ArrayList<>();
    public boolean buyButton = false;

    private Translate position;
    public boolean inShop = true;
    public HashSet<AreaController> lastUsedAreas = new HashSet<>();

    public Race race;
    public Power power;


    public AttackableAreas attackableAreas = new NormalAreasAttack();
    public AttackableType attackableType = new NormalAttackableType();

    public Decline decline = new NotInDecline();
    public Decline inDecline = new InDecline();

    public Points points = new NormalPoints();
    public Defend defend = new NormalDefend();

    public SpecialAction powerSpecialAction = new SpecialNone();
    public SpecialAction raceSpecialAction = new SpecialNone();

    public Points racePoints = new NullPoints();
    public Points powerPoints = new NullPoints();

    public AttackableAreas raceAreas = new NormalAreasAttack();
    public AttackableAreas powerAreas = new NormalAreasAttack();

    public AttackBoost raceAttackBoost = new NoAttackBoost();
    public AttackBoost powerAttackBoost = new NoAttackBoost();

    public CombinationModel(String raceId, String powerId) {
        this.race = RaceEnum.valueOf(raceId).getRace();
        this.power = PowerEnum.valueOf(powerId).getPower();
    }

    public Stack<FicheController> removeFiches(int count) {
        Stack<FicheController> tempFiches = new Stack<>();
        for (int i = 0; i < count; i++)
            tempFiches.add(raceFiches.pop());
        return tempFiches;
    }

    public void setPosition(Translate pos) {
        position = pos;
    }


    public void addFiche(FicheController fiche) {
        Translate fichePos = new Translate(player.get3dPos().getX(), (player.get3dPos().getY() + ((raceFiches.size() - 1) * 10)), player.get3dPos().getZ());
        raceFiches.add(fiche);
        fiche.moveToPosition(fichePos);
    }

    public FicheController removeFiche() {
        return raceFiches.pop();
    }

    @Override
    public void register(CombinationObserver mvo) {
        observers.add(mvo);
        notifyAllObservers();
    }

    @Override
    public void unregister(CombinationObserver mvo) {
        observers.remove(mvo);
    }

    @Override
    public void notifyAllObservers() {
        for (CombinationObserver obs : observers) {
            obs.update(this);
        }
    }

    @Override
    public String getRaceId() {
        return race.getName();
    }

    @Override
    public String getPowerId() {
        return power.getName();
    }

    @Override
    public Translate getPosition() {
        return position;
    }

}
