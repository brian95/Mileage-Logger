package com.example.brianmote.mileagelogger.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.brianmote.mileagelogger.Helpers.ParseAuthHelper;
import com.example.brianmote.mileagelogger.Helpers.ProgressDialogHelper;
import com.example.brianmote.mileagelogger.Listeners.AuthListener;
import com.example.brianmote.mileagelogger.R;
import com.parse.ParseException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Register Activity";
    private ProgressDialogHelper dialogHelper;
    private ProgressDialog dialog;

    @Bind(R.id.registerUsername)
    EditText registerUsername;
    @Bind(R.id.registerEmail)
    EditText registerEmail;
    @Bind(R.id.registerPassword)
    EditText registerPassword;
    @Bind(R.id.submitBtn)
    Button submitBtn;

    private String username;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Register");

        submitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.submitBtn:
                if (dialog == null) {
                    dialog = new ProgressDialog(RegisterActivity.this);
                }
                if (dialogHelper == null) {
                    dialogHelper = new ProgressDialogHelper(dialog);
                }
                if (!dialog.isShowing()) {
                    dialogHelper.buildDialog();
                }

                Log.d(TAG, "Submit Btn Clicked");
                username = registerUsername.getText().toString().trim();
                email = registerEmail.getText().toString().trim();
                password = registerPassword.getText().toString().trim();

                ParseAuthHelper parseAuthHelper = new ParseAuthHelper(username, password, email);
                parseAuthHelper.setEmail(email);
                parseAuthHelper.setUsername(username);
                parseAuthHelper.setPassword(password);
                parseAuthHelper.register(new AuthListener() {
                    @Override
                    public void completed(ParseException e) {
                        if (dialog.isShowing()){
                            dialog.dismiss();
                        }
                        String msg;
                        if (e == null){
                            msg = "Account created";
                        } else {
                            msg = e.getMessage();
                        }
                        Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_LONG).show();
                    }
                });
        }
    }
}
