package com.librarymanagement.helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.librarymanagement.R;

/**
 * Created by keval on 12-04-2018.
 */

public class CustomProgressDialog extends ProgressDialog {

    Context context;
    String comment;
    ImageView la;
    TextView txt_loading;

    public CustomProgressDialog(Context context){
        super(context);
        this.context = context;
        this.comment = "Please wait...";
    }
    public CustomProgressDialog(Context context, String comment) {
        super(context);
        this.context = context;
        this.comment = comment.isEmpty() ? "Please wait..." : comment;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_custom_progress_dialog);

        getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);
        this.setCancelable(false);
        txt_loading=(TextView) findViewById(R.id.txt_loading);
        txt_loading.setText(comment);

    }

    public void setMessage(String comment){
        this.comment=comment;
    }

    public void resetMessage(){
        this.comment="Please wait...";
    }

    @Override
    public void show() {
        super.show();
    }
    @Override
    public void dismiss() {
        super.dismiss();
    }
}
