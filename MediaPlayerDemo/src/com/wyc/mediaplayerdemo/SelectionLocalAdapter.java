package com.wyc.mediaplayerdemo;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author wangyc
 * @version create time:2014年12月16日_上午11:26:39
 * @Description TODO
 */
public class SelectionLocalAdapter extends BaseAdapter {
	private List<TVBean> list = new ArrayList<TVBean>();
	private LayoutInflater mInflater;
	private Context context;

	public SelectionLocalAdapter(Context context) {
		this.context = context;
		this.mInflater = LayoutInflater.from(context);
	}

	public void setData(List<TVBean> list) {
		this.list = list;

		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TVBean model = list.get(position);
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.update(model, position);
		return convertView;
	}

	public class ViewHolder {

		public TVBean data;
		private TextView title, date;
		private ImageView image;
		private ImageView image_line;

		public ViewHolder(View view) {

			title = (TextView) view.findViewById(R.id.text);

		}

		public void update(TVBean model, int position) {
			this.data = model;
			title.setText(model.getName());
		}
	}

	public void refresh(List<TVBean> arg0) {
		list.clear();
		list.addAll(arg0);
		notifyDataSetChanged();
	}

	public void addData(List<TVBean> mapList) {

		list.addAll(mapList);
		notifyDataSetChanged();

	}

}
