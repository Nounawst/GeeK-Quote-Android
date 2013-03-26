package com.supinfo.geekquote.fragments;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.supinfo.geekquote.R;
import com.supinfo.geekquote.models.Quote;

public class QuoteFragment extends Fragment {
	
	private Quote quote;
	private TextView strQuoteView;
	private TextView dateQuoteView;
	private RatingBar ratingQuoteView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		if(getArguments() != null)
			quote = (Quote) getArguments().getSerializable("quote");
		else
			quote = new Quote("Citation non trouvée", 0, new Date());
		
		View view = inflater.inflate(R.layout.fragment_quote, container, false);
		
		strQuoteView = (TextView) view.findViewById(R.id.quoteText);
		strQuoteView.setText(this.quote.getStrQuote());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		dateQuoteView = (TextView) view.findViewById(R.id.quoteDate);
		dateQuoteView.setText(sdf.format(this.quote.getCreationDate()));
		
		ratingQuoteView = (RatingBar) view.findViewById(R.id.quoteRating);	
		ratingQuoteView.setRating(this.quote.getRating());
		
		return view;
	}
}
