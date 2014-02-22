package info.u250.c2d.tests.android;

import info.u250.c2d.tests.C2dTests;

import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SubsActivity extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.subs);
       
        
        Bundle extras = getIntent().getExtras();
		int id = Integer.parseInt(extras.get("id").toString());
		List<Map<String,Object>> subs = (List<Map<String,Object>>)C2dTests.tests.get(id).get("subs");
		
       
        SimpleAdapter adapter = new SimpleAdapter(this, subs, R.layout.item,
        		new String[] { "title", "desc", "image" },
        		new int[] { R.id.item_title, R.id.item_description, R.id.item_image });
        setListAdapter(adapter);
        
        setTitle(C2dTests.tests.get(id).get("title").toString());
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
    	Map<?, ?> item = (Map<?, ?>)getListAdapter().getItem(position);
    	Bundle bundle = new Bundle();
		bundle.putString("test", item.get("cls").toString());
		Intent intent = new Intent(this, C2dTestActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
	}
	

}
