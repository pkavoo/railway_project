package com.m_ticketing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.m_ticketing.helper.Progress;
import com.m_ticketing.session.SetProfile;

import org.json.JSONException;
import org.json.JSONObject;

import static com.m_ticketing.ini.Constants.Server.PROJECT;

public class Activity_Signin extends AppCompatActivity {
    EditText username, password;
    Button login;
    TextView signup;
    private Progress progress;
    private SetProfile setProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        progress = new Progress(this, "", "Please wait ...");
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        signup = (TextView) findViewById(R.id.link_signup);
        setProfile = new SetProfile(this);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Activity_Signup.class));
            }
        });


    }


    public void login(View view) {

        String username_text = username.getText().toString();
        String password_text = password.getText().toString();

        if (username_text.isEmpty() || username.length() < 4) {
            username.setError("Username should be more than 4 characters");
        } else {
            username.setError(null);
        }
        if (password_text.isEmpty()) {
            password.setError("Password must be more than 4 Characters");
        } else {
            progress.ShowDialogue();
            Toast.makeText(this, password_text, Toast.LENGTH_SHORT).show();
            Ion.with(this)
                    .load(PROJECT)
                    .setBodyParameter("action", "login")
                    .setBodyParameter("username", username_text)
                    .setBodyParameter("password", password_text)
                    .asString()
                    .setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String result) {
                            progress.DismisDialogue();
                            if (e == null) {
                                try {
                                    JSONObject jsonObject = new JSONObject(result);
                                    if (jsonObject.getBoolean("status")) {
                                        setProfile.setLoggedIn(true);
                                        setProfile.setUserId(jsonObject.getString("userid"));
                                        setProfile.Save();
                                        startActivity(new Intent(Activity_Signin.this, MainActivity.class));
                                        finish();
                                    } else {
                                        Toast.makeText(Activity_Signin.this, "Invalid password or Username try again", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e1) {
                                    e1.printStackTrace();
                                }
                            }


                        }
                    });


        }


    }
}
