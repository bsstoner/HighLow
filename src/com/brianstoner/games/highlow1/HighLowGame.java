package com.brianstoner.games.highlow1;

public class HighLowGame {

	private int mNumDecks;
	private Deck[] mShoe;
	private int mCurStreak;
	private int mLongStreak;
	private int mPrevCardValue;
	private int mCurCardValue;
	private int mCurDeck;
	private boolean mGameActive;
	
	public HighLowGame(int num_decks, int longest_streak){
		this.startGame(num_decks, longest_streak);
	}
	
	public int getNumDecksLeft() {
		return (mNumDecks-1)-mCurDeck;
	}
	
	public int getCardsLeft() {
		return mShoe[mCurDeck].cardsLeftInDeck() + (this.getNumDecksLeft()*52);
	}
	
	public boolean getGameIsActive() {
		return mGameActive;
	}
	
	public void dealCard() {
		mPrevCardValue = mShoe[mCurDeck].topCard().getValue();
		
		if (mShoe[mCurDeck].cardsLeftInDeck()>0) {
			mShoe[mCurDeck].dealCard();
		} else if (this.getNumDecksLeft()>0) {
			// Go to the next deck....
			mCurDeck++;
			mShoe[mCurDeck].dealCard();
		} else {
			endGame();
		}
		
		mCurCardValue = mShoe[mCurDeck].topCard().getValue();
	}
	
	public Card topCard(){
		return mShoe[mCurDeck].topCard();
	}
	
	public int getCurrentStreak(){
		return mCurStreak;
	}
	
	public int getLongStreak() {
		return mLongStreak;
	}
	
	public String evaluateUserGuess(int view_id) {
		int intAnswer=compareCurToPrevCard();

		if (intAnswer==0) {
			return "Push";
		} else {	
	    	if (view_id==R.id.low_choice) {
	    		intAnswer--;
	    	} else {
	    		intAnswer++;
	    	}
	    	
	    	if (intAnswer==0) {
	    		mCurStreak=0;
	    		return "Wrong";
	    	} else {
	    		mCurStreak++;
	    		if (mCurStreak>mLongStreak) {
	    			mLongStreak=mCurStreak;
	    		}
	    		return "Correct!";
	    	}
		}
	}

	private int compareCurToPrevCard(){
		if(mCurCardValue>mPrevCardValue){
			return 1;
		} else if (mCurCardValue<mPrevCardValue) {
			return -1;
		} else if (mCurCardValue==mPrevCardValue) {
			return 0;
		} else {
			return 0;
		}
	}
	
	private void endGame(){
		mGameActive = false;
	}
	
	private void startGame(int num_decks, int longest_streak){
		mNumDecks = num_decks;
		mCurStreak = 0;
		mLongStreak = longest_streak;
		mPrevCardValue = 0;
		mCurCardValue = 0;
		
		mShoe = new Deck[mNumDecks];
		
		for(int d=0; d<=mNumDecks-1; d++) {
			mShoe[d] = new Deck();
			mShoe[d].shuffle();
		}
		
		mCurDeck = 0;
		
		mGameActive = true;
	}
	
}
