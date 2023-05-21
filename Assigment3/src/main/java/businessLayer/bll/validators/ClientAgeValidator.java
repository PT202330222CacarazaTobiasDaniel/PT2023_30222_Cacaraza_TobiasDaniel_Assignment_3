package businessLayer.bll.validators;

import Model.Client;

/**
 * A validator for the age of a client object.
 * @Author: Cacaraza Tobias Daniel, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class ClientAgeValidator implements Validator<Client> {
	private static final int MIN_AGE = 7;
	private static final int MAX_AGE = 30;

	/**
	 * Methode that validates the clients age
	 * @param t the desired client
	 */
	public void validate(Client t) {

		if (t.getAge() < MIN_AGE || t.getAge() > MAX_AGE) {
			throw new IllegalArgumentException("The Student Age limit is not respected!");
		}

	}

}
