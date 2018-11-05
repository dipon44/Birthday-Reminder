package com.example.monir.bdayreminder;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button save,show;
    TextInputLayout nameInputWrapper,bdayInputWrapper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInputWrapper=findViewById(R.id.nameLayoutwrapper);
        bdayInputWrapper=findViewById(R.id.bdayLayoutwrapper);

        save=findViewById(R.id.saveButton);
        show=findViewById(R.id.showButton);

         final BirthDayDataBaseAdapter birthDayDataBaseAdapter=new BirthDayDataBaseAdapter(getApplicationContext());
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=nameInputWrapper.getEditText().getText().toString();
                String bday=bdayInputWrapper.getEditText().getText().toString();

                BirthDayModel birthDayModel=new BirthDayModel();

                birthDayModel.setName(name);
                birthDayModel.setDate(bday);

                birthDayDataBaseAdapter.insertBirthDay(birthDayModel);

                //Toast.makeText(getApplicationContext(),"Data"+name+bday,Toast.LENGTH_LONG).show();
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<BirthDayModel> birthdayList=new ArrayList<BirthDayModel>();
                birthdayList=birthDayDataBaseAdapter.getAllBirthdayRecords();
                Intent intent=new Intent(getApplicationContext(),BirthDayListView.class);

                startActivity(intent);


                //------------------for debugging-----------------------
                for(int i=0;i<birthdayList.size();i++)
                {
                    Log.d("Name",birthdayList.get(i).getName()+"Birthday:"+birthdayList.get(i).getDate());
                }
            }
        });
    }
}
