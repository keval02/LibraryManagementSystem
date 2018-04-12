package com.librarymanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.librarymanagement.adapter.SelectionAdapter;

import java.util.ArrayList;
import java.util.List;

public class MaterialLearningActivity extends AppCompatActivity {

    ListView listFields;
    SelectionAdapter selectionAdapter;
    List<SelectionBean> selectionBeanList = new ArrayList<>();

    String[] fields = {"Computer", "Information Technology", "Mechanical", "Civil", "Electrical", "Electronics"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_learning);


        listFields = (ListView) findViewById(R.id.list_fields);
        selectionAdapter = new SelectionAdapter(selectionBeanList, getApplicationContext() , false);
        listFields.setAdapter(selectionAdapter);

        SetAllFields();

    }

    private void SetAllFields() {

        for (int i = 0; i < fields.length; i++) {

            SelectionBean bean = new SelectionBean();

            bean.setName(fields[i]);

            selectionBeanList.add(bean);
        }


        selectionAdapter.notifyDataSetChanged();
    }
}
