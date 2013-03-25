package com.supinfo.geekquote.activities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.supinfo.geekquote.R;
import com.supinfo.geekquote.adapters.QuotesPagerAdapter;
import com.supinfo.geekquote.models.Quote;

public class QuoteListActivity extends FragmentActivity {

	private QuotesPagerAdapter quotesPagerAdapter;
	private ViewPager viewPager;
	
	private List<Quote> quotes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quote_list);		
		
		String[] strQuotes = getResources()
				.getStringArray(R.array.quotes);
		
		for(String s : strQuotes) {
			addQuote(s);
		}

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		quotesPagerAdapter = new QuotesPagerAdapter(
				getSupportFragmentManager(),
				this.quotes);

		// Set up the ViewPager with the sections adapter.
		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(quotesPagerAdapter);
	}
	
	public void addQuote(String strQuote) {
		if(this.quotes == null)
			quotes = new ArrayList<Quote>();
		
		Quote quote = new Quote(strQuote, 0, new Date());
		quotes.add(quote);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quote_list, menu);
		return true;
	}
}
