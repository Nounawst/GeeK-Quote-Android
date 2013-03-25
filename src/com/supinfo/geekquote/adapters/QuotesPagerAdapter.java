package com.supinfo.geekquote.adapters;

import java.util.ArrayList;
import java.util.List;

import com.supinfo.geekquote.fragments.QuoteFragment;
import com.supinfo.geekquote.fragments.QuotesListFragment;
import com.supinfo.geekquote.models.Quote;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class QuotesPagerAdapter extends FragmentPagerAdapter {
	
	private List<Quote> quotes;

	public QuotesPagerAdapter(FragmentManager fm) {
		super(fm);
	}
	
	public QuotesPagerAdapter(FragmentManager fm, List<Quote> quotes) {
		this(fm);
		
		this.quotes = quotes;
	}
	

	@Override
	public Fragment getItem(int pos) {
		Bundle args = new Bundle();
		Fragment frag;
		
		if(pos == 0) {
			args.putSerializable("quotes", (ArrayList<Quote>) this.quotes);
			
			frag = new QuotesListFragment();
		}
		else {
			args.putSerializable("quote", this.quotes.get(pos - 1));
	
			frag = new QuoteFragment();
		}
		frag.setArguments(args);
		
		return frag;
	}
	

	@Override
	public int getCount() {
		return this.quotes.size() + 1;
	}
	
	

	@Override
	public CharSequence getPageTitle(int pos) {
		
		return (pos == 0 ? "Quotes" : this.quotes.get(pos - 1).getStrQuote());
	}

}
