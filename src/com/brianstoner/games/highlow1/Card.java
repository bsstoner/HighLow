package com.brianstoner.games.highlow1;

import android.content.Context;

public class Card {

	private int mSuit;
	private int mValue;
	
	
	public Card(int card_suit, int card_value){
		mSuit=card_suit;
		mValue=card_value;
	}
	
	public int getSuit(){
		return mSuit;
	}
	
	public int getValue(){
		return mValue;
	}
	
	public String getSuitAsString(){
		switch(mSuit){
		case 3: return "Spades";
		case 2: return "Hearts";
		case 1: return "Diamonds";
		case 0: return "Clubs";
		default: return "no suit";
		}
	}
	
	public String getValueAsString(){
		switch(mValue){
		case 0: return "Two";
		case 1: return "Three";
		case 2: return "Four";
		case 3: return "Five";
		case 4: return "Six";
		case 5: return "Seven";
		case 6: return "Eight";
		case 7: return "Nine";
		case 8: return "Ten";
		case 9: return "Jack";
		case 10: return "Queen";
		case 11: return "King";
		case 12: return "Ace";
		default: return "unknown";
		}
	}
	
	public int getCardDrawableId(Context c){
		int resID = c.getResources().getIdentifier(this.getSuitAsString().toLowerCase() + "_" + this.getValueAsString().toLowerCase(),
				"drawable","com.brianstoner.games.highlow1");
		return resID;
	}
	
}
