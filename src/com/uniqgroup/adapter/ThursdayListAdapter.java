package com.uniqgroup.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.uniqgroup.application.R;
import com.uniqgroup.application.UserEventListActivity;
import com.uniqgroup.database.DatabaseHandler;
import com.uniqgroup.pojo.WeekEvent;
import com.uniqgroup.utility.ImageProcessing;
import com.uniqgroup.utility.SelectModeStatic;

public class ThursdayListAdapter extends BaseAdapter {

	private ArrayList<WeekEvent> thursdayList;
	private Context ctx;
	private LayoutInflater inflater;
	private DatabaseHandler db;
	private ImageProcessing imgProc;

	public ThursdayListAdapter(Context context, ArrayList<WeekEvent> thursList) {
		thursdayList = thursList;
		ctx = context;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		db = new DatabaseHandler(ctx);
		imgProc = new ImageProcessing(ctx);
		System.out.println("MyAdapter constructor");
	}

	@Override
	public int getCount() {
		System.out.println("getCount method");
		return thursdayList.size();
	}

	@Override
	public Object getItem(int position) {
		System.out.println("getItem method");
		return null;
	}

	@Override
	public long getItemId(int position) {
		System.out.println("getItemId");
		return 0;
	}

	static class ViewHolder {

		ImageView ivAvatar;
	}
	
	public void changeData(ArrayList<WeekEvent> newList) {
		thursdayList = newList;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.week_view_row, null);
			holder.ivAvatar = (ImageView) convertView
					.findViewById(R.id.ivAvatar);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		imgProc.setImageWith_loader(holder.ivAvatar, thursdayList.get(position)
				.getEventLogo());
		
		imgProc.setImageWith_loader(holder.ivAvatar, thursdayList.get(position).getEventLogo());

		final int eventId = thursdayList.get(position).getEventId();
		final String eventTTS = thursdayList.get(position).getEventTTS();
		final int selectMode = thursdayList.get(position).get_select_mode();

		switch (selectMode) {
		case SelectModeStatic.notSelected:
			holder.ivAvatar.setBackgroundResource(R.drawable.round_corner_ash);
			break;
		case SelectModeStatic.singleSelected:
			holder.ivAvatar.setBackgroundResource(R.drawable.round_corner_purple);
			break;
		case SelectModeStatic.multiSelected:
			holder.ivAvatar.setBackgroundResource(R.drawable.round_corner_blue);
			break;
		}
		
		holder.ivAvatar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) 
			{
				UserEventListActivity uela = (UserEventListActivity) ctx;
				uela.selectionProcessWeekView(eventId, eventTTS, 0, position, 4);
			}
		});

		holder.ivAvatar.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) 
			{
				UserEventListActivity uela = (UserEventListActivity) ctx;
				uela.selectionProcessWeekView(eventId, eventTTS, 1,position, 4);
				return true;
			}
		});
		return convertView;
	}

}
