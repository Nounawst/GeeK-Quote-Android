package com.supinfo.geekquote.fragments;

import java.util.List;

import com.supinfo.geekquote.R;
import com.supinfo.geekquote.activities.QuoteActivity;
import com.supinfo.geekquote.activities.QuoteListActivity;
import com.supinfo.geekquote.adapters.QuotesListAdapter;
import com.supinfo.geekquote.models.Quote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class QuotesListFragment extends Fragment {
	
	private List<Quote> quotes;
	private View view;
	private ListView quotesListView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		this.quotes = (List<Quote>) getArguments().getSerializable("quotes");
		
		view = inflater.inflate(
				R.layout.fragment_quotes_list, 
				null);
		
		Button addQuoteButton = (Button) view.findViewById(
				R.id.addQuoteButton);
		
		/**
		 *  Gestion du clic sur le bouton d'ajout d'une citation.
		 *  Récupère le contenu de l'EditText addQuoteInput, rajoute
		 *  la citation grâce à QuoteListActivity.addQuote(String),
		 *  et mets à jour la ListView et le ViewPager
		 */
		addQuoteButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				QuoteListActivity act = (QuoteListActivity) getActivity();
				String strQuote = ((EditText) view.findViewById(R.id.addQuoteInput))
						.getText().toString();
				((EditText) view.findViewById(R.id.addQuoteInput)).setText("");
				
				act.addQuote(strQuote);
				
				act.getQuotesPagerAdapter().notifyDataSetChanged();
			}
		});
		
		quotesListView = (ListView) view.findViewById(
				R.id.quotesList);
		
		quotesListView.setAdapter(
			new ArrayAdapter<Quote>(
				getActivity(), 
				android.R.layout.simple_list_item_1, 
				this.quotes)
		);
		
		quotesListView.setOnItemClickListener(
			new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int pos, long id) {
					
					Quote quote = quotes.get(pos);
					
					Intent intent = new Intent(
						getActivity(), 
						QuoteActivity.class
					);
					intent.putExtra("quote", quote);
					intent.putExtra("quoteId", pos);
					
					startActivityForResult(intent, 1);
				}
				
			}
		);
		
		
		return view;
	}

	public ListView getQuotesListView() {
		return quotesListView;
	}

	public void setQuotesListView(ListView quotesListView) {
		this.quotesListView = quotesListView;
	}

}
