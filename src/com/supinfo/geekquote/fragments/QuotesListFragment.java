package com.supinfo.geekquote.fragments;

import java.util.List;

import com.supinfo.geekquote.R;
import com.supinfo.geekquote.adapters.QuotesListAdapter;
import com.supinfo.geekquote.models.Quote;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class QuotesListFragment extends Fragment {
	
	private List<Quote> quotes;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		this.quotes = (List<Quote>) getArguments().getSerializable("quotes");
		
		View view = inflater.inflate(
				R.layout.fragment_quotes_list, 
				null);
		ListView quotesList = (ListView) view.findViewById(
				R.id.quotesList);
		
//		quotesList.setAdapter(
//			new ArrayAdapter<Quote>(
//				getActivity(), 
//				android.R.layout.simple_list_item_1,
//				this.quotes
//			)
//		);
		
		quotesList.setAdapter(
				new QuotesListAdapter(getActivity(), 0, quotes)
		);
		
		
		return view;
	}

}
