package com.kolti.lissa.partymaker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.AccessToken;

public class MainActivity extends AppCompatActivity {

    private static final int RESULT_LOGIN_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (AccessToken.getCurrentAccessToken() == null) {
            Intent loginIntent = new Intent(MainActivity.this, FacebookLoginActivity.class);
            startActivityForResult(loginIntent, RESULT_LOGIN_ACTIVITY);
            overridePendingTransition(0,0);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case RESULT_LOGIN_ACTIVITY:
                overridePendingTransition(0,0);
                if(resultCode == RESULT_OK){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(R.id.content_main, new MainFragment())
                            .commitAllowingStateLoss();
                }
                if (resultCode == RESULT_CANCELED) {
                    if (AccessToken.getCurrentAccessToken() == null) {
                        Intent loginIntent = new Intent(MainActivity.this, FacebookLoginActivity.class);
                        startActivityForResult(loginIntent, RESULT_LOGIN_ACTIVITY);
                        overridePendingTransition(0,0);
                    }
                }
                break;
            default:
                super.onActivityResult(requestCode,resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
