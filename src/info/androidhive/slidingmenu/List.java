package info.androidhive.slidingmenu;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class List extends Activity
{

	 ArrayList<String> array = new ArrayList<String>();
     public void onCreate(Bundle saveInstanceState)
     {
             super.onCreate(saveInstanceState);
             setContentView(R.layout.list);
        
             ListView List=(ListView)findViewById(R.id.listView);
            
            

              getNames();
              // Create The Adapter with passing ArrayList as 3rd parameter
              ArrayAdapter<String> arrayAdapter =     
              new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, array);
              // Set The Adapter
              List.setAdapter(arrayAdapter);
             
              // register onClickListener to handle click events on each item
              List.setOnItemClickListener(new OnItemClickListener()
                 {
                          // argument position gives the index of item which is clicked
                         public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3)
                         {
                            
                                 String selectedActivity=array.get(position);
                                 Toast.makeText(getApplicationContext(), "Activity Selected : "+selectedActivity,   Toast.LENGTH_LONG).show();
                             
                                 /*if(selectedActivity=="LATEST")
                                 {
                                	Toast.makeText(getApplicationContext(), "Latest Activity Selected : ",   Toast.LENGTH_LONG).show();
                                 }*/
                         
                         }
                 });
     }
    
     void getNames()
     {
         array.add("LATEST");
         array.add("COMPLETED");
         array.add("INPROCESS");
         array.add("OVERDUE");
         
        
        
     }
}
