package com.m_ticketing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.m_ticketing.helper.Progress;
import com.twirry.twirrylibrary.Twirry;
import com.twirry.twirrylibrary.task.ExecutionInsert;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.m_ticketing.ini.Constants.Server.PROJECT;

public class Activity_Signup extends AppCompatActivity {
    EditText name, id, email, password, confirm_password, username;
    Button register;
    private Progress progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
progress = new Progress(this,"","please wait loading ...");
        name = (EditText) findViewById(R.id.name);
        id = (EditText) findViewById(R.id.id_number);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        confirm_password = (EditText) findViewById(R.id.confirm_password);
        username = (EditText) findViewById(R.id.username);



    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if (email.matches(emailRegex)) {
            return true;
        }
        return false;
    }

    public void signup(View view) {

        String name_text = name.getText().toString();
        String id_text = id.getText().toString();
        String email_text = email.getText().toString();
        String password_text = password.getText().toString();
        String confirm_password_text = confirm_password.getText().toString();
        String username_text = username.getText().toString();

        if (name_text.isEmpty() || name_text.length() <= 4) {
            name.setError("Name should have more than 4 characters");
        } else {
            name.setError(null);
        }
        if (id_text.isEmpty() || name_text.length() <= 4) {
            id.setError("ID number should have more than 4 characters");
        } else {
            name.setError(null);
        }

        if (email_text.isEmpty() && !isValidEmail(email_text)) {
            email.setError("Enter valid email");
        } else {
            email.setError(null);
        }

        if (password_text.isEmpty() || password_text.length() <= 4) {
            password.setError("Password should have more than 4 characters");
        } else {
            password.setError(null);
        }

        if (confirm_password_text.isEmpty() || confirm_password_text.equals(password_text)) {
            confirm_password.setError("Both passwords are not matching");
        } else {
            confirm_password.setError(null);
        }


        if (username_text.isEmpty() || name_text.length() <= 4) {
            username.setError("Name should have more than 4 characters");
        } else {
            username.setError(null);
progress.ShowDialogue();
            Ion.with(this)
                    .load(PROJECT)
                    .setBodyParameter("action","signup")
                    .setBodyParameter("name",name_text)
                    .setBodyParameter("idnumber",id_text)
                    .setBodyParameter("email",email_text)
                    .setBodyParameter("password",password_text)
                    .setBodyParameter("username",username_text)
                    .asString()
                    .setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String result) {
                            progress.DismisDialogue();
                            Toast.makeText(Activity_Signup.this, result, Toast.LENGTH_SHORT).show();
                            if (e==null){
                                try {
                                    JSONObject jsonObject = new JSONObject(result);
                                    if (jsonObject.getBoolean("status")){
                                    startActivity(new Intent(getApplicationContext(), Activity_Signin.class));
                                    finish();
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
