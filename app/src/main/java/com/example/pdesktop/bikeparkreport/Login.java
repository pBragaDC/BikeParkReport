package com.example.pdesktop.bikeparkreport;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.service.textservice.SpellCheckerService;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class Login extends ActionBarActivity {

    //Declaration of variables
    //facebook login variables
    private LoginButton facebookLoginButton;
    private CallbackManager callbackManager;

    //variables needed for toast messages
    int duration = Toast.LENGTH_SHORT;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //facebook login initialization
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();//init instance of callback manager

        //set view and instance
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);


        //button declarations
        facebookLoginButton = (LoginButton) findViewById(R.id.facebooklogin);

        //method to register call back, and handle events
        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>()
        {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //set display text to verify token
                //REMOVE THIS when app is in testing phase.
                CharSequence text = "User ID: "
                        + loginResult.getAccessToken().getUserId()
                        + "\n" +
                        "Auth Token: "
                        + loginResult.getAccessToken().getToken();

                //display toast
                Toast.makeText(getApplicationContext(), text, duration).show();

                //go to next screen if login successful
                Intent i = new Intent(Login.this, ParksActivity.class);
                startActivity(i);


            }

            @Override
            public void onCancel() {
                //set display text and display toast
                CharSequence text = "Login attempt canceled.";
                Toast.makeText(getApplicationContext(), text, duration).show();
            }

            @Override
            public void onError(FacebookException error) {
                //set display text and display toast
                CharSequence text = "Login attempt failed.";
                Toast.makeText(getApplicationContext(), text, duration).show();
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
