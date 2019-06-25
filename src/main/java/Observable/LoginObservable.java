package Observable;

import Observer.LoginObserver;

/**
 * This interface is the LoginObservable which is implemented by the LoginModel class.
 * @author Yoran de Vos
 * @version June 2019
 */
public interface LoginObservable {

    /**
     * @param mvo  registers an observer to the observable
     */
    void register(LoginObserver mvo);

    /**
     * @param mvo unregisters an observer to the observable
     */
    void unregister(LoginObserver mvo);

    /**
     * @return a boolean whether the loginState is false or true. In case false it will be failed login
     */
    boolean getLoginState();

    /**
     * Notifies an observer
     */
    void notifyAllObservers();
}
