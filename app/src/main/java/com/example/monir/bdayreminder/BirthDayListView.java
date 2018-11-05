package com.example.monir.bdayreminder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;

public class BirthDayListView extends AppCompatActivity {

    private ListView birthdayListview;
    private AlertDialog.Builder alertDialogBuilder;
    private  BirthDayDataBaseAdapter birthDayDataBaseAdapter;
    private  BirthDayCustomAdapter customAdapter;
    private Intent intent;
    private BirthDayModel birthDayModel2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birth_day_list_view);

        birthdayListview=findViewById(R.id.listviewId);
        ArrayList<BirthDayModel> birthdayList=new ArrayList<BirthDayModel>();
         birthDayDataBaseAdapter=new BirthDayDataBaseAdapter(this);

        birthdayList=birthDayDataBaseAdapter.getAllBirthdayRecords();

        customAdapter=new BirthDayCustomAdapter(this,birthdayList);

        birthdayListview.setAdapter(customAdapter);

        registerForContextMenu(birthdayListview);

    }

    @Override

    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu,v,menuInfo);

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.context_menu,menu);
        menu.setHeaderTitle("Select Action");

    }

    @Override
    public  boolean onContextItemSelected(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int pos=info.position;
        birthDayModel2= (BirthDayModel) customAdapter.getItem(pos);
        final int did=birthDayModel2.getId();

        switch (item.getItemId())
         {
             case R.id.update:
                // Toast.makeText(this,"Edit is selected",Toast.LENGTH_LONG).show();
                  intent=new Intent(getApplicationContext(),EditPopupWindow.class);
                  intent.putExtra("name",birthDayModel2.getName());
                  intent.putExtra("bday",birthDayModel2.getDate());
                  intent.putExtra("id",birthDayModel2.getId());
                  startActivity(intent);
                 return true;
             case R.id.delete:

                 //Toast.makeText(this,"Delete is selected"+did,Toast.LENGTH_LONG).show();
                 alertDialogBuilder=new AlertDialog.Builder(this);
                 //set title
                 alertDialogBuilder.setTitle("Delete");
                 //set message
                 alertDialogBuilder.setMessage("Are you sure you want to delete Birthday Record ???");
                 //set icon
                 alertDialogBuilder.setIcon(R.drawable.question);
                 //set positive button
                 alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         birthDayDataBaseAdapter.DeleteBirthDayInfo(did);
                     }
                 });
                  //set negative button
                 alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         dialog.cancel();
                     }
                 });
                 AlertDialog alertDialog=alertDialogBuilder.create();
                 alertDialog.show();

                 //birthDayDataBaseAdapter.DeleteBirthDayInfo(2);
                 return true;

             case R.id.copy:
                 Toast.makeText(this,"Copy is selected",Toast.LENGTH_LONG).show();
                 return true;
             case R.id.setmessage:
                 Toast.makeText(this,"Send Message is selected",Toast.LENGTH_LONG).show();
                 return true;
             default:
                 return super.onContextItemSelected(item);


         }
    }
}
