package com.librarymanagement;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.librarymanagement.constant.AppPreference;
import com.librarymanagement.constant.Global;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity {

    EditText edtEmailId, edtPassword;
    Button btnLogin;
    TextView txtRegister;

    AppPreference appPreference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        appPreference = new AppPreference(getApplicationContext());

        fetchIds();

    }

    private void fetchIds() {

        edtEmailId = (EditText) findViewById(R.id.edt_emailid);
        edtPassword = (EditText) findViewById(R.id.edt_password);

        btnLogin = (Button) findViewById(R.id.btn_login);

        txtRegister = (TextView) findViewById(R.id.txt_register);

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext() , RegisterActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailId = edtEmailId.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();


                if (emailId.isEmpty())
                    Global.CustomToast(getApplicationContext(), getResources().getString(R.string.emptyEmail));
                else if (!Global.validateEmail(emailId))
                    Global.CustomToast(getApplicationContext(), getResources().getString(R.string.validEmail));
                else if (password.isEmpty())
                    Global.CustomToast(getApplicationContext(), getResources().getString(R.string.emptyPassword));
                else if (password.length() < 6)
                    Global.CustomToast(getApplicationContext(), getResources().getString(R.string.validPassword));
                else{
                    Intent intent = new Intent(getApplicationContext() , HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    appPreference.setLogin(true);
                    startActivity(intent);
                    finish();
                }


            }
        });


    }



    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));

    }
}
