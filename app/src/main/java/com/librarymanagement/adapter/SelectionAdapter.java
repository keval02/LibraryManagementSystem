package com.librarymanagement.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.librarymanagement.BookIssueActivity;
import com.librarymanagement.R;
import com.librarymanagement.SelectionBean;
import com.librarymanagement.SubjectActivity;
import com.librarymanagement.constant.AppPreference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Keval on 4/13/2018.
 */

public class SelectionAdapter extends BaseAdapter {


    List<SelectionBean> selectionBeanList = new ArrayList<>();
    Context context;
    boolean isFinalPage = false;
    LayoutInflater inflater = null;
    AppPreference preference;

    public SelectionAdapter(List<SelectionBean> selectionBeanList, Context context, boolean isFinalPage) {
        this.selectionBeanList = selectionBeanList;
        this.context = context;
        this.isFinalPage = isFinalPage;
        this.preference = new AppPreference(context);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return selectionBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return selectionBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View views;
        views = inflater.inflate(R.layout.layout_selection, viewGroup, false);

        TextView txt_selection;

        txt_selection = (TextView) views.findViewById(R.id.txt_selection);

        txt_selection.setText(selectionBeanList.get(i).getName());

        txt_selection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isFinalPage) {
                    preference.setBookName(selectionBeanList.get(i).getName());
                    Intent intent = new Intent(context, BookIssueActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);


                } else {
                    preference.setBookType(selectionBeanList.get(i).getName());
                    Intent intent = new Intent(context, SubjectActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);


                }


            }
        });


        return views;
    }
}
