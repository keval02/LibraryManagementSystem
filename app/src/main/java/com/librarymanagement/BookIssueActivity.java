package com.librarymanagement;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.librarymanagement.apis.AdminAPI;
import com.librarymanagement.apis.ServiceGenerator;
import com.librarymanagement.constant.AppPreference;
import com.librarymanagement.constant.Global;
import com.librarymanagement.helper.CustomProgressDialog;
import com.librarymanagement.model.AppModel;
import com.librarymanagement.model.LoginModel;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BookIssueActivity extends AppCompatActivity {

    CardView cardDate;
    Button btnRegister;
    EditText edtUserName, edtEmail, edtBookName, edtBookType, edtDate;
    int mYear, mMonth, mDay;
    DatePickerDialog datePickerDialog;

    AppPreference appPreference;
    CustomProgressDialog progressDialog;

    AdminAPI adminAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_issue);

        appPreference = new AppPreference(getApplicationContext());
        adminAPI = ServiceGenerator.getAPIClass();
        progressDialog = new CustomProgressDialog(BookIssueActivity.this);
        progressDialog.setCancelable(false);


        fetchIds();
    }

    private void fetchIds() {

        edtUserName = (EditText) findViewById(R.id.edt_username);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtBookName = (EditText) findViewById(R.id.edt_bookname);
        edtBookType = (EditText) findViewById(R.id.edt_booktype);
        edtDate = (EditText) findViewById(R.id.edt_date);

        edtUserName.setText(appPreference.getUserName());
        edtEmail.setText(appPreference.getEmailId());
        edtBookName.setText(appPreference.getBookName());
        edtBookType.setText(appPreference.getBookType());


        edtBookName.setEnabled(false);
        edtBookType.setEnabled(false);


        btnRegister = (Button) findViewById(R.id.btn_register);


        cardDate = (CardView) findViewById(R.id.card_date);


        cardDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromdate();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName = edtUserName.getText().toString().trim();
                String emailId = edtEmail.getText().toString().trim();
                String bookName = edtBookName.getText().toString().trim();
                String bookType = edtBookType.getText().toString().trim();
                String date = edtDate.getText().toString().trim();


                if (userName.isEmpty())
                    Global.CustomToast(getApplicationContext(), getResources().getString(R.string.emptyusername));
                else if (emailId.isEmpty())
                    Global.CustomToast(getApplicationContext(), getResources().getString(R.string.emptyEmail));
                else if (!Global.validateEmail(emailId))
                    Global.CustomToast(getApplicationContext(), getResources().getString(R.string.validEmail));
                else if (bookName.isEmpty())
                    Global.CustomToast(getApplicationContext(), getResources().getString(R.string.emptyBookName));
                else if (bookType.isEmpty())
                    Global.CustomToast(getApplicationContext(), getResources().getString(R.string.emptyBookType));
                else if (date.isEmpty())
                    Global.CustomToast(getApplicationContext(), getResources().getString(R.string.emptyDate));
                else {

                    progressDialog.show();
                    Call<AppModel> appModelCall = adminAPI.BOOK_ISSUE_DETAILS(userName, emailId, bookName, bookType, date);
                    appModelCall.enqueue(new Callback<AppModel>() {
                        @Override
                        public void onResponse(Call<AppModel> call, Response<AppModel> response) {
                            progressDialog.dismiss();
                            AppModel model = response.body();

                            if (model != null) {
                                if (model.isStatus()) {
                                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
                        public void onFailure(Call<AppModel> call, Throwable t) {
                            progressDialog.dismiss();
                            Global.errorToast(getApplicationContext(), t);
                        }
                    });


                }


            }
        });

    }

    private void fromdate() {

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {


                        edtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

                    }

                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));

    }
}
