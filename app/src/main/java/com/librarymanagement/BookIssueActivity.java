package com.librarymanagement;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BookIssueActivity extends AppCompatActivity {

    CardView cardDate;
    EditText edtUserName, edtEmail , edtBookName , edtBookType , edtDate;
    int mYear, mMonth, mDay;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_issue);

        fetchIds();
    }

    private void fetchIds() {


        cardDate = (CardView) findViewById(R.id.card_date);


        cardDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromdate();
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
