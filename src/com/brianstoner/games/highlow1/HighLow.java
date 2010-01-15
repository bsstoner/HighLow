package com.brianstoner.games.highlow1;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HighLow extends Activity {
    /** Called when the activity is first created. */
	public static final String PREFS_NAME = "HighLowPrefs";
	public static final String LONG_STREAK_PREF = "longestStreak";
	
	private HighLowGame mGame;
	private TextView mViewCardsLeft;
	private TextView mViewCurStreak;
	private TextView mViewLongStreak;
	private TextView mViewResult;
	private ImageView mViewCard;
	private static Context mAppContext;
	private Button mLowChoice;
	private Button mHighChoice;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mAppContext = (Context) this;
        
        this.getWindow().setBackgroundDrawableResource(R.drawable.green_back);
        
        // Set up the Views.....
        mViewCard = (ImageView) findViewById(R.id.card_image);
        mViewCardsLeft = (TextView) findViewById(R.id.cards_left);
        mViewCurStreak = (TextView) findViewById(R.id.cur_streak);
        mViewLongStreak = (TextView) findViewById(R.id.long_streak);
    	mViewResult = (TextView) findViewById(R.id.result_view);
        mLowChoice = (Button) findViewById(R.id.low_choice);
        mHighChoice = (Button) findViewById(R.id.high_choice);

        Button newGame = (Button) findViewById(R.id.new_game);
        
        mLowChoice.setOnClickListener(mChoiceHandler);
        mHighChoice.setOnClickListener(mChoiceHandler);
        newGame.setOnClickListener(mNewGameHandler);

		// Get Longest Streak.....
        SharedPreferences settings = this.getSharedPreferences(PREFS_NAME, 0);
        int longestStreak = settings.getInt(LONG_STREAK_PREF, 0); 
		
        startGame(longestStreak);
    }
	
	private OnClickListener mChoiceHandler = new OnClickListener() {
	    public void onClick(View v) {
	    	dealCard();
	    	if (mGame.getGameIsActive()){
	    		mViewResult.setText(mGame.evaluateUserGuess(v.getId()));
		    	updateStats();
	    	} else {
	    		endGame();
	    	}
	    }
	};
	
	private OnClickListener mNewGameHandler = new OnClickListener() {
	    public void onClick(View v) {
	    	startGame(mGame.getLongStreak());
	    }
	};
	private void dealCard(){
		mGame.dealCard();
		mViewCard.setImageResource(mGame.topCard().getCardDrawableId(mAppContext));
		mViewCard.setAdjustViewBounds(true);
	}
	
	private void updateStats(){
		mViewCardsLeft.setText(Integer.toString(mGame.getCardsLeft()));
		mViewCurStreak.setText(Integer.toString(mGame.getCurrentStreak()));
		mViewLongStreak.setText(Integer.toString(mGame.getLongStreak()));
	}
	
	private void endGame(){
		mViewCardsLeft.setText("0");
		mViewResult.setText("Game Over");
		mViewCard.setImageResource(R.drawable.card_back);
		mLowChoice.setEnabled(false);
		mHighChoice.setEnabled(false);
		
		Button newGame = (Button) findViewById(R.id.new_game);
		newGame.setVisibility(View.VISIBLE);
	}
	
	private void startGame(int longestStreak) {

		mGame = new HighLowGame(1,longestStreak);
    	mViewCard.setImageResource(mGame.topCard().getCardDrawableId(mAppContext));
        updateStats();
		mLowChoice.setEnabled(true);
		mHighChoice.setEnabled(true);
		
		Button newGame = (Button) findViewById(R.id.new_game);
		newGame.setVisibility(View.INVISIBLE);
	}
	
	@Override
	protected void onStop(){
		super.onStop();
		
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putInt(LONG_STREAK_PREF,mGame.getLongStreak());
		
		editor.commit();
	}

}