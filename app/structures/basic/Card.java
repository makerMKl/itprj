package structures.basic;


/**
 * This is the base representation of a Card which is rendered in the player's hand.
 * A card has an id, a name (cardname) and a manacost. A card then has a large and mini
 * version. The mini version is what is rendered at the bottom of the screen. The big
 * version is what is rendered when the player clicks on a card in their hand.
 * 
 * @author Dr. Richard McCreadie
 *
 */
public class Card {
	
	int id;
	int iType;
	int iNO;
	int iHealth;

	int iAttack;
	String cardname;
	int manacost;
	
	MiniCard miniCard;
	BigCard bigCard;
	String cardConfig;// -- add MK.L 2.15

	String unitConfig;// -- add MK.L 2.15
	public int getiType() {
		return iType;
	}

	public void setiType(int iType) {
		this.iType = iType;
	}
	public String getCardConfig() {
		return cardConfig;
	}

	public void setCardConfig(String cardConfig) {
		this.cardConfig = cardConfig;
	}

	public String getUnitConfig() {
		return unitConfig;
	}

	public void setUnitConfig(String unitConfig) {
		this.unitConfig = unitConfig;
	}
	public int getiNO() {
		return iNO;
	}

	public void setiNO(int iNO) {
		this.iNO = iNO;
	}
	public Card() {};
	
	public Card(int id, String cardname, int manacost, MiniCard miniCard, BigCard bigCard
			, String cardConfig,String unitConfig,int iType,int iNo,int iHealth,int iAttack) {
		super();
		this.id = id;
		this.iNO = iNO;
		this.iType = iType;
		this.cardname = cardname;
		this.manacost = manacost;
		this.miniCard = miniCard;
		this.bigCard = bigCard;
		this.cardConfig = cardConfig;
		this.unitConfig = unitConfig;
		this.iHealth = iHealth;
		this.iAttack = iAttack;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCardname() {
		return cardname;
	}
	public void setCardname(String cardname) {
		this.cardname = cardname;
	}
	public int getManacost() {
		return manacost;
	}
	public void setManacost(int manacost) {
		this.manacost = manacost;
	}
	public MiniCard getMiniCard() {
		return miniCard;
	}
	public void setMiniCard(MiniCard miniCard) {
		this.miniCard = miniCard;
	}
	public BigCard getBigCard() {
		return bigCard;
	}
	public void setBigCard(BigCard bigCard) {
		this.bigCard = bigCard;
	}
	public int getiHealth() {
		return iHealth;
	}

	public void setiHealth(int iHealth) {
		this.iHealth = iHealth;
	}
	public int getiAttack() {
		return iAttack;
	}
	public void setiAttack(int iAttack) {
		this.iAttack = iAttack;
	}
	
}
