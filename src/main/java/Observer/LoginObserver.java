package Observer;

import Observable.LoginObservable;

/** This interface is the LoginObserver which is implemented by the loginView class.
 * @author Beau Mosterd
 * @version June 2019
 */
public interface LoginObserver {

	/**
	 * @param lo has the LoginObservable parameter which is used to update the loginView
	 */
	void update(LoginObservable lo);
}
