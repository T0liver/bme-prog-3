package junitlab.bank;

/**
 * Egy kezdetleges takarékszövetkezet interfésze.
 * Alapvető számlakezelési műveleteket definiál.  
 */
public interface Bank {

	/**
	 * Egy új számla megnyitása. A számlán kezdetben nulla az egyenleg.
	 * @return A frissen létrejött számla száma.
	 */
	public String openAccount();
	
	/**
	 * Üres számla megszüntetése.
	 * @param accountNumber A megszüntetendő számlaszám.
	 * @return Sikerült-e elvégezni a műveletet. Ha a számla egyenlege nem nulla, nem lehet megszűntetni.
	 * @throws AccountNotExistsException Ha a megadott számlaszám nem létezik.
	 */
	public boolean closeAccount(String accountNumber) throws AccountNotExistsException;
	
	/**
	 * Az aktuális egyenleg lekérdezése.
	 * @param accountNumber A kérdéses számlaszám.
	 * @return Az egyenleg.
	 * @throws AccountNotExistsException Ha a megadott számlaszám nem létezik.
	 */
	public long getBalance(String accountNumber) throws AccountNotExistsException;
	
	/**
	 * Egy adott összeg befizetése a számlára. Technikai okokból csak 100-zal
	 * osztható összegeket lehet befizetni a számlára. Az ettől eltérő összegeket
	 * a kerekítés szabályai szerint kell kezelni.
	 * @param accountNumber A feltöltendő számlaszám.
	 * @param amount A befizetendő összeg. Mindig pozitívnak kell lennie.
	 * @throws AccountNotExistsException Ha a megadott számlaszám nem létezik.
	 */
	public void deposit(String accountNumber, long amount) throws AccountNotExistsException;
	
	/**
	 * Egy adott összeg kivétele a számláról. Technikai okokból csak 100-zal
	 * osztható összegeket lehet kifizetni a számláról. Az ettől eltérő összegeket
	 * a kerekítés szabályai szerint kell kezelni.
	 * @param accountNumber A terhelendő számlaszám.
	 * @param amount A kifizetendő összeg. Mindig pozitívnak kell lennie.
	 * @throws AccountNotExistsException Ha a megadott számlaszám nem létezik.
	 * @throws NotEnoughFundsException Ha kevesebb pénz van a számlán, mint amit megadtunk.
	 */
	public void withdraw(String accountNumber, long amount) throws AccountNotExistsException, NotEnoughFundsException;
	
	/**
	 * Egy adott összeg átutalása egyik számláról a másikra. Az átutalásokra
	 * nem vonatkozik a százas kerekítési szabály, tetszőleges összeg átutalható. 
	 * @param sourceAccount A terhelendő számlaszám.
	 * @param targetAccount A cél számlaszám, amire utalunk.
	 * @param amount A kifizetendő összeg. Mindig pozitívnak kell lennie.
	 * @throws AccountNotExistsException Ha a megadott számlaszám nem létezik.
	 * @throws NotEnoughFundsException Ha kevesebb pénz van a forrásszámlán, mint amit át akarunk utalni.
	 */
	public void transfer(String sourceAccount, String targetAccount, long amount) throws AccountNotExistsException, NotEnoughFundsException;
}
