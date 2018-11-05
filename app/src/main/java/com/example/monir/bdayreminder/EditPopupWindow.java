package com.example.monir.bdayreminder;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditPopupWindow extends AppCompatActivity {

    private Button no,save;
    private EditText editname,editbday;
    private BirthDayDataBaseAdapter birthDayDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_popup_window);

        birthDayDataBaseAdapter=new BirthDayDataBaseAdapter(getApplicationContext());
        Intent intent=getIntent();

        BirthDayModel birthDayModel=intent.getParcelableExtra("myobject");

        editname=findViewById(R.id.popupEditName);
        String name=intent.getStringExtra("name");
        editname.setText(name);
        editbday=findViewById(R.id.popupEditBday);
        String bday=intent.getStringExtra("bday");
        editbday.setText(bday);
        final int id=intent.getIntExtra("id",0);


        no=findViewById(R.id.popupcancleButton);
        save=findViewById(R.id.popupsaveButton);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=editname.getText().toString();
                String bday=editbday.getText().toString();

                BirthDayModel birthDayModel=new BirthDayModel();

                birthDayModel.setName(name);
                birthDayModel.setDate(bday);

                birthDayDataBaseAdapter.UpdateBirthDayInfo(birthDayModel,id);

                Intent intent=new Intent(getApplicationContext(),BirthDayListView.class);
                startActivity(intent);

                //Toast.makeText(getApplicationContext(),"info"+name+bday,Toast.LENGTH_LONG).show();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(getApplicationContext(),BirthDayListView.class);
               startActivity(intent);
            }
        });


    }
}
