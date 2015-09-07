package com.wyc.mediaplayerdemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class TVListActivity extends Activity {
	ListView listView;
	List<TVBean> list;
	SelectionLocalAdapter adapter;

	// TVB1
	// private String path =
	// "http://ktv002.cdnak.ds.kylintv.net/nlds/kylin/tvbj1/as/live/tvbj1_4.m3u8";
	// TVBE
	// private String path =
	// "http://ktv032.cdnak.ds.kylintv.net/nlds/kylin/tvbent/as/live/tvbent_4.m3u8";
	// TVB2
	// private String path =
	// "http://ktv028.cdnak.ds.kylintv.net/nlds/kylin/tvbj2/as/live/tvbj2_4.m3u8";
	// 郑州
	// private String path = "rtmp://218.28.177.199:1935/live/300k";
	// 香港
	// private String path = "rtmp://live.hkstv.hk.lxdns.com/live/hks";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		listView = (ListView) findViewById(R.id.listView);
		list = new ArrayList<TVBean>();
		adapter = new SelectionLocalAdapter(this);
		listView.setAdapter(adapter);
		initData();
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(TVListActivity.this, MainActivity.class);
				intent.putExtra("url", list.get(arg2).getUrl());
				startActivity(intent);
			}
		});
	}

	private void initData() {
		TVBean b1 = new TVBean();
		b1.setName("TVB1");
		b1.setUrl("http://ktv002.cdnak.ds.kylintv.net/nlds/kylin/tvbj1/as/live/tvbj1_4.m3u8");
		list.add(b1);

		TVBean b2 = new TVBean();
		b2.setName("TVBE");
		b2.setUrl("http://ktv032.cdnak.ds.kylintv.net/nlds/kylin/tvbent/as/live/tvbent_4.m3u8");
		list.add(b2);

		TVBean b3 = new TVBean();
		b3.setName("TVB2");
		b3.setUrl("http://ktv028.cdnak.ds.kylintv.net/nlds/kylin/tvbj2/as/live/tvbj2_4.m3u8");
		list.add(b3);

		TVBean b4 = new TVBean();
		b4.setName("郑州TV");
		b4.setUrl("rtmp://218.28.177.199:1935/live/300k");
		list.add(b4);

		TVBean b5 = new TVBean();
		b5.setName("香港TV");
		b5.setUrl("rtmp://live.hkstv.hk.lxdns.com/live/hks");
		list.add(b5);
		
		
		TVBean b6 = new TVBean();
		b6.setName("光明网");
		b6.setUrl("rtmp://wms.gmw.cn:1936/live/gmw");
		list.add(b6);
		
		
		TVBean b7 = new TVBean();
		b7.setName("test");
		b7.setUrl("http://vod.cdn2.cmvideo.cn/699061/20150417/16/2202098254/83325115/ds0417gq14nr_55.mp4.m3u8?msisdn=15083384654&mdspid=&spid=699061&netType=5&sid=2202098254&pid=2028595375&timestamp=20150906195300&Channel_ID=0109_04090200-99000-305800010030001&ProgramID=602108307&ParentNodeID=10664018&client_ip=124.193.167.50&assertID=2202098254&encrypt=a59e6d9cb1fa77451a3431065d2fd756");
		list.add(b7);
		
		
		
		adapter.refresh(list);
		
	}
}
