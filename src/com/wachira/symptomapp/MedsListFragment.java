package com.wachira.symptomapp;

import java.util.ArrayList;
import java.util.List;

import com.wachira.symptomapp.db.DbOpenHelper;

import android.app.ListFragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MedsListFragment extends ListFragment {
	private List<String> meds = new ArrayList<String>();
	private RemoteServer rserver;
	private String TAG = "MedsList";
	private DbOpenHelper mDbHelper;
	private String username;
	private String password;
	private Context context;
	
	 @Override  
	  public View onCreateView(LayoutInflater inflater, ViewGroup container,  
	    Bundle savedInstanceState) {  
		 context = getActivity();
		 username = CheckinActivity.username;
		 password = CheckinActivity.password;
		 rserver = new RemoteServer(context, null, username, password);
		 mDbHelper = new DbOpenHelper(context);
		 Cursor cursor = mDbHelper.getReadableDatabase()
				 .query(DbOpenHelper.MEDS_TABLE_NAME, DbOpenHelper.MEDS_COLUMNS, 
						 null, null, null, null, null);
		 @SuppressWarnings("deprecation")
		SimpleCursorAdapter adapter = new SimpleCursorAdapter
				 (context, android.R.layout.simple_list_item_1, cursor, 
						 new String[]{DbOpenHelper.MED_NAME}, new int[]{android.R.id.text1});
				  
				 setListAdapter(adapter);  
				return super.onCreateView(inflater, container, savedInstanceState);  

	 }

	@Override
	public void onListItemClick(ListView listView, View view, int position, long id) {
		super.onListItemClick(listView, view, position, id);
		((CheckinActivity) context).onMedSelected(listView,view,position, id);
		
	}
	 
}
