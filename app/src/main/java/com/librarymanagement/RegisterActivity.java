package com.librarymanagement;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

public class RegisterActivity extends AppCompatActivity {


    EditText edtUserName, edtEmail, edtMobile, edtPassword, edtRetype , edtAddress;
    Button btnRegister;
    AppPreference appPreference;
    CustomProgressDialog progressDialog;

    AdminAPI adminAPI ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        appPreference = new AppPreference(getApplicationContext());
        adminAPI = ServiceGenerator.getAPIClass();
        progressDialog = new CustomProgressDialog(RegisterActivity.this);
        progressDialog.setCancelable(false);

        fetchIds();
    }

    private void fetchIds() {

        edtUserName = (EditText) findViewById(R.id.edt_username);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtMobile = (EditText) findViewById(R.id.edt_mobile);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        edtRetype = (EditText) findViewById(R.id.edt_repassword);
        edtAddress = (EditText) findViewById(R.id.edt_address);

        btnRegister = (Button) findViewById(R.id.btn_register);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = edtUserName.getText().toString().trim();
                String mobileNo = edtMobile.getText().toString().trim();
                String emailId = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                String retypePassword = edtRetype.getText().toString().trim();
                String address = edtAddress.getText().toString().trim();



                if(userName.isEmpty())
                    Global.CustomToast(getApplicationContext(), getResources().getString(R.string.emptyusername));
                else if (emailId.isEmpty())
                    Global.CustomToast(getApplicationContext(), getResources().getString(R.string.emptyEmail));
                else if (!Global.validateEmail(emailId))
                    Global.CustomToast(getApplicationContext(), getResources().getString(R.string.validEmail));
                else if (mobileNo.isEmpty())
                    Global.CustomToast(getApplicationContext(), getResources().getString(R.string.emptyMobile));
                else if (mobileNo.length() < 10)
                    Global.CustomToast(getApplicationContext(), getResources().getString(R.string.validMobile));
                else if (address.isEmpty())
                    Global.CustomToast(getApplicationContext(), getResources().getString(R.string.emptyAdd));
                else if (password.isEmpty())
                    Global.CustomToast(getApplicationContext(), getResources().getString(R.string.emptyPassword));
                else if (password.length() < 6)
                    Global.CustomToast(getApplicationContext(), getResources().getString(R.string.validPassword));
                else if (!password.matches(retypePassword))
                    Global.CustomToast(getApplicationContext(), "Retype Password does not Matches With Password");
                else{

                    progressDialog.show();
                    Call<LoginModel> loginModelCall = adminAPI.REGISTER_MODEL_CALL(userName , address , emailId, password , mobileNo);
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
