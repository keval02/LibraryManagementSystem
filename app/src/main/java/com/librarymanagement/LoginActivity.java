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

import com.librarymanagement.apis.AdminAPI;
import com.librarymanagement.apis.ServiceGenerator;
import com.librarymanagement.constant.AppPreference;
import com.librarymanagement.constant.Global;
import com.librarymanagement.helper.CustomProgressDialog;
import com.librarymanagement.model.LoginModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity {

    EditText edtEmailId, edtPassword;
    Button btnLogin;
    TextView txtRegister;

    AppPreference appPreference;
    AdminAPI adminAPI;
    CustomProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        appPreference = new AppPreference(getApplicationContext());
        adminAPI = ServiceGenerator.getAPIClass();
        progressDialog = new CustomProgressDialog(LoginActivity.this);
        progressDialog.setCancelable(false);

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
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
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
                else {
                    progressDialog.show();
                    Call<LoginModel> loginModelCall = adminAPI.LOGIN_MODEL_CALL(emailId, password);
                    loginModelCall.enqueue(new Callback<LoginModel>() {
                        @Override
                        public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                            progressDialog.dismiss();
                            LoginModel model = response.body();

                            if (model != null) {
                                if (model.isStatus()) {
                                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    appPreference.setLogin(true);
                                    appPreference.setUserName(model.getData().getUsername());
                                    appPreference.setEmailId(model.getData().getEmail());
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Global.CustomToast(getApplicationContext(), model.getMessage());
                                }


                            } else {
                                Global.defaultError(getApplicationContext());
                            }


                        }

                        @Override
                        public void onFailure(Call<LoginModel> call, Throwable t) {
                            progressDialog.dismiss();
                            Global.errorToast(getApplicationContext(), t);
                        }
                    });


                }


            }
        });


    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));

    }
}
