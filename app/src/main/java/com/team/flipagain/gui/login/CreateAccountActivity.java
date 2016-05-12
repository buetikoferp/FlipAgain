package com.team.flipagain.gui.login;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.team.flipagain.R;
import com.team.flipagain.domain.User;
import com.team.flipagain.gui.mainScreen.MainScreenActivity;
import com.team.flipagain.messaging.ClientMessager;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
        private UserLoginTask mAuthTask = null;
        private final String mEmail;
        private final String mPassword;
        User user;


        // UI references.
        private AutoCompleteTextView mEmailView;
        private EditText mPasswordView;
        private View mProgressView;
        private View mLoginFormView;


        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
            user = new User(1, email, password);
            user.setIsNewUser(true);
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            ClientMessager cm = new ClientMessager();
            try {
                ClientMessager clientMessenger = new ClientMessager();
                return cm.validateUser(user);

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } catch (TimeoutException e) {
                e.printStackTrace();
                return false;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }

        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;

            if (success) {
                finish();
                Intent myIntent = new Intent(CreateAccountActivity.this, MainScreenActivity.class);
                CreateAccountActivity.this.startActivity(myIntent);
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;

        }
    }
}
