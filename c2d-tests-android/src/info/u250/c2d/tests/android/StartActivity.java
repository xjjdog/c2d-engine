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

public class AndroidTestStarter extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //process
        
        for (Map<String,Object> group : C2dTests.tests) {
        	{
        		if(group.get("image") instanceof String){
		        	String image = group.get("image").toString();
		        	group.remove("image");
		        	group.put("image", getResources().getIdentifier(image, null, getPackageName()));
        		}
        	}
        	
			for (Map<String, Object> item : (List<Map<String,Object>>)group.get("subs")) {
				{
					if(item.get("image") instanceof String){
			        	String image = item.get("image").toString();
			        	item.remove("image");
			        	item.put("image", getResources().getIdentifier(image, null, getPackageName()));
					}
	        	}
			}
		}
        SimpleAdapter adapter = new SimpleAdapter(this, C2dTests.tests, R.layout.item,
        		new String[] { "title", "desc", "image" },
        		new int[] { R.id.item_title, R.id.item_description, R.id.item_image });
        setListAdapter(adapter);
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
    	Map<?, ?> item = (Map<?, ?>)getListAdapter().getItem(position);
		System.out.println(item.get("title"));
		
		Bundle bundle = new Bundle();
		bundle.putInt("id", position);
		Intent intent = new Intent(this, SubsActivity.class);
		intent.putExtras(bundle);

		startActivity(intent);
	}
}
