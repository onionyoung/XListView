package com.example.xlistview;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.xlistview.XListView.IXListViewListener;

import android.app.Activity;
import android.content.ClipData.Item;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
public class MainActivity extends Activity implements IXListViewListener{
	private XListView listView;
	private Handler mHandler;
	private SimpleAdapter adapter;
	private List<Item> items;
	private LinearLayout placeHolder;
	private ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
	
	private int num=10;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView =(XListView) findViewById(R.id.listView1);
		data(num);
		adapter = new SimpleAdapter( 
				this, 
				list,
				R.layout.listview,
				new String[] { "food" ,"place"},
				new int[] {R.id.textView1, R.id.textView2} );
		getLayoutInflater().inflate(R.layout.listview, placeHolder);
		listView.setAdapter( adapter );
		listView.setPullRefreshEnable(true);
	    listView.setPullLoadEnable(true);
		listView.setXListViewListener(this);
		mHandler = new Handler();
	}
	private void data(int i){
		list.removeAll(list);
		for(int count=0; count<i; count++){
		    HashMap<String,Object> item = new HashMap<String,Object>();
		    item.put( "food", count);
		    item.put( "place","*****" );
		    list.add( item );
		}
	}
	private void onLoad() {
		listView.stopRefresh();
		listView.stopLoadMore();
		listView.setRefreshTime("刚刚");
	}
	
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				num=num+5;
				data(num);
				adapter.notifyDataSetChanged();
				onLoad();
			}
		}, 2000);
	}
}
