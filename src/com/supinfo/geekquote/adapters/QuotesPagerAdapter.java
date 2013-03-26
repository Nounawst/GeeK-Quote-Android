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
import android.util.Log;
import android.widget.ArrayAdapter;

public class QuotesPagerAdapter extends FragmentPagerAdapter {
	
	private List<Quote> quotes;
	private QuotesListFragment quoteListFragment;
	private List<QuoteFragment> quoteFragments;

	public QuotesPagerAdapter(FragmentManager fm) {
		super(fm);
		this.quoteFragments = new ArrayList<QuoteFragment>();
	}
	
	public QuotesPagerAdapter(FragmentManager fm, List<Quote> quotes) {
		this(fm);
		
		this.quotes = quotes;
	}
	

	@Override
	public Fragment getItem(int pos) {
		Fragment frag;
		
		Log.d("Pouet", "QuotesPagerAdapter getItem : " + pos);
		
		if(this.quoteFragments.size() <= pos || this.quoteFragments.get(pos) == null) {
			Bundle args = new Bundle();
			
			if(pos == 0) {
				args.putSerializable("quotes", (ArrayList<Quote>) this.quotes);
				
				quoteListFragment = new QuotesListFragment();
				frag = quoteListFragment;
			}
			else {
				args.putSerializable("quote", this.quotes.get(pos - 1));
		
				frag = new QuoteFragment();
				this.quoteFragments.add((QuoteFragment) frag);
			}
			frag.setArguments(args);
		}
		else {
			frag = this.quoteFragments.get(pos);
		}
		
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

	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
		
		((ArrayAdapter<Quote>) this.quoteListFragment.getQuotesListView().getAdapter())
			.notifyDataSetChanged();
	}

}
