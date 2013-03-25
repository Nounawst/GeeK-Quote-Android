package com.supinfo.geekquote.adapters;

import java.util.List;

import com.supinfo.geekquote.models.Quote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class QuotesListAdapter extends ArrayAdapter<Quote> {

	public QuotesListAdapter(Context context, int textViewResourceId,
			List<Quote> objects) {
		super(context, textViewResourceId, objects);
	}

}
