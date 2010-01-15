package com.brianstoner.games.highlow1;

import java.util.*;

public class Deck {
	private Card[] mDeck;
	private Card[] mDeckTemp1;
	private Card[] mDeckTemp2;
	private int mTop;
	private int mDeckSize;
	
	public Deck(){
		mDeck = new Card[52];
		mTop = 0;
		mDeckSize = 52;
			
		int d = 0;
		
		for(int p=3;p>=0;p--)
        {
            for(int i=0; i<=12; i++, d++)
            {
            mDeck[d] = new Card(p, i);
            }
        }
	}
	
    public void shuffle()
    {
        for(int count = 0; count<501; count++)
        {
        Random num = new Random();
        // picks two random numbers from the number left in the deck,
        // and then adds the 'top' so as to not include the cards
        // already removed from the deck
        int num1 = (num.nextInt(mDeckSize) + mTop);
        int num2 = (num.nextInt(mDeckSize) + mTop);
        mDeckTemp1 = new Card[1];
        mDeckTemp2 = new Card[1];
        mDeckTemp1[0] = mDeck[num1];
        mDeckTemp2[0] = mDeck[num2];
        mDeck[num2] = mDeckTemp1[0];
        mDeck[num1] = mDeckTemp2[0];
        }      
    }

	
	public Card dealCard(){
		mTop+=1;
		mDeckSize-=1;
		return mDeck[mTop];
	}
	
	public Card topCard(){
		return mDeck[mTop];
	}
	
	public Card prevCard(){
		return mDeck[mTop-1];
	}
	
	public int cardsLeftInDeck(){
		return (mDeck.length-mTop-1);
	}
	

}
