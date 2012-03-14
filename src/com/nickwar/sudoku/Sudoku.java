package com.nickwar.sudoku;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.os.Bundle;
import android.provider.Contacts.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

public class Sudoku extends Activity implements OnClickListener {

//    @Override
//    protected void onPause() {
//        super.onPause();
//        Music.stop(this);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Music.play(this, R.raw.main);
//    }

    private static final String TAG = "Sudoku";
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()) {
       case R.id.settings:
               startActivity(new Intent(this,com.nickwar.sudoku.Settings.class));
           return true;
       }
       
       return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.about_button:
            Intent i = new Intent(this,About.class);
            startActivity(i);
            break;
        case R.id.new_button:
            openNewGameDialog();
            break;
        case R.id.exit_button:
            finish();
            break;
        default:
            break;
        }
    }

    private void openNewGameDialog() {
       new AlertDialog.Builder(this).setTitle(R.string.new_game_title)
       .setItems(R.array.difficulty, 
               new DialogInterface.OnClickListener() {
                
                @Override
                public void onClick(DialogInterface dialog, int which) {
                   startGame(which);
                }
            }).show();
    }

    private void startGame(int i) {
        Log.d(TAG, "clicked on " + i);
        Intent intent = new Intent(Sudoku.this,Game.class);
        intent.putExtra(Game.KEY_DIFFICULTY, i);
        startActivity(intent);
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        View continueButton = this.findViewById(R.id.continue_button);
        continueButton.setOnClickListener(this);
        View newButton = this.findViewById(R.id.new_button);
        newButton.setOnClickListener(this);
        View aboutButton = this.findViewById(R.id.about_button);
        aboutButton.setOnClickListener(this);
        View exitButton = this.findViewById(R.id.exit_button);
        exitButton.setOnClickListener(this);
    }
}