package com.supinfo.geekquote.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.supinfo.geekquote.R;
import com.supinfo.geekquote.models.Quote;

public class QuoteFragment extends Fragment {
	
	private Quote quote;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		quote = (Quote) getArguments().getSerializable("quote");
		
		View view = inflater.inflate(R.layout.fragment_quote, null);
		TextView textView = (TextView) view.findViewById(R.id.quoteView);
		textView.setText(this.quote.getStrQuote());
		
		return view;
	}
}
