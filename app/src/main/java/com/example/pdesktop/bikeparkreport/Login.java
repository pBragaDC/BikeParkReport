package com.example.pdesktop.bikeparkreport;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
//facebook imports
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
//twitter imports
import io.fabric.sdk.android.Fabric;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;


public class Login extends ActionBarActivity {

    //Declaration of variables
    //facebook login variables
    private LoginButton facebookLoginButton;
    private CallbackManager callbackManager;

    //twitter variables
    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "jPlvceHLCGi5wuPGmL5xYHnow";
    private static final String TWITTER_SECRET = "frHymjYEmpVyWLZy6Eexe48NLzZx5ufEJXYK0DvJThaHPA68ik";
    private TwitterLoginButton twitterLoginButton;

    //google private variables
    private SignInButton googleLoginButton;
    private GoogleApiClient mGoogleApiClient;
    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    //variables needed for toast messages
    int duration = Toast.LENGTH_SHORT;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //facebook login initialization
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();//init instance of callback manager

        //twitter auth and fabric init
        TwitterAuthConfig twitterAuthConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(twitterAuthConfig));

        //google sign in
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        //set view and instance
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        //button declarations
        facebookLoginButton = (LoginButton) findViewById(R.id.facebooklogin);

        //twitter button declarations
        twitterLoginButton = (TwitterLoginButton)findViewById(R.id.twitter_login_button);
        twitterLoginButton.setCallback(new LoginHandler());

        //init google login button, and wait for button click
        googleLoginButton = (SignInButton) findViewById(R.id.googlelogin);
        googleLoginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
                /*
                switch(v.getId())
                {
                    case R.id.googlelogin:
                        signIn();
                        break;
                }
*/
            }
        });

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
                CharSequence text = "Facebook login attempt canceled.";
                Toast.makeText(getApplicationContext(), text, duration).show();
            }

            @Override
            public void onError(FacebookException error) {
                //set display text and display toast
                CharSequence text = "Facebook login attempt failed.";
                Toast.makeText(getApplicationContext(), text, duration).show();
            }
        });
    }

    //twitter login handler
    private class LoginHandler extends Callback<TwitterSession>
    {
        //on successful login
        @Override
        public void success(Result<TwitterSession> twitterSessionResult)
        {
            //set display text to verify token
            //REMOVE THIS when app is in testing phase.
            CharSequence text = "Status: " +
                    "Your login was successful " +
                    twitterSessionResult.data.getUserName() +
                    "\nAuth Token Received: " +
                    twitterSessionResult.data.getAuthToken().token;

            //display toast
            Toast.makeText(getApplicationContext(), text, duration).show();

            //go to next screen if login successful
            Intent i = new Intent(Login.this, ParksActivity.class);
            startActivity(i);
        }

        @Override
        public void failure(TwitterException e)
        {
            //set display text and display toast
            CharSequence text = "Twitter login attempt failed.";
            Toast.makeText(getApplicationContext(), text, duration).show();
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            Intent i = new Intent(Login.this, ParksActivity.class);
            startActivity(i);
        } else {
            //set display text and display toast
            CharSequence text = "Google login attempt failed.";
            Toast.makeText(getApplicationContext(), text, duration).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    //on activity method, performs action depending what button was pressed
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        //on facebook activity result
        callbackManager.onActivityResult(requestCode, resultCode, data);

        //on twitter activity result
        twitterLoginButton.onActivityResult(requestCode, resultCode, data);

        //google signin
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }


}

