package Observable;

import Enum.ApplicatieViewEnum;
import Observer.ApplicatieObserver;

public interface ApplicatieObservable {
    void register(ApplicatieObserver ao);
    void notifyObserver();
    ApplicatieViewEnum getCurrentView();
}
