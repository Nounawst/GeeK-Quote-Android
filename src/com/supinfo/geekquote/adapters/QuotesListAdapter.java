package com.supinfo.geekquote.adapters;

import java.util.List;

import com.supinfo.geekquote.R;
import com.supinfo.geekquote.models.Quote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class QuotesListAdapter extends ArrayAdapter<Quote> {

	public QuotesListAdapter(Context context, int textViewResourceId,
			List<Quote> objects) {
		super(context, textViewResourceId, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(getContext());
		
		View view = inflater.inflate(R.layout.quote_list_item, null);
		
		TextView textView = (TextView) view.findViewById(R.id.quoteItemText);
		textView.setText(this.getItem(position).getStrQuote());
		
		return view;
	}

}
