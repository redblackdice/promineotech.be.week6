package promineotech.be.week6;

public class Card {
	// Numeric value of the card
	private byte mValue;
	// Text representation of the card name
	private String mName;
	
	// Full Constructor
	public Card(byte _iValue, String _iName) {
		mValue = _iValue;
		mName = _iName;
	}
	// Default Value Constructor
	public Card() { this((byte)0, ""); }

	// SGetter: Names
	public String getName() { return mName; }
	public Card setName(String _iName) { mName = _iName; return this; }

	// SGetter: Values
	public byte getValue() { return mValue; }
	public Card setValue(byte _iValue) { mValue = _iValue; return this; }
	
	//
	public Card describe() {
		System.out.println("[" + mValue + ", \"" + mName + "\"]");
		return this;
	}
}
