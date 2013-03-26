package com.supinfo.geekquote.activities;

import java.text.SimpleDateFormat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.supinfo.geekquote.R;
import com.supinfo.geekquote.models.Quote;

public class QuoteActivity extends FragmentActivity implements Dialog.OnClickListener {
	
	private Quote quote;
	private int quoteId;
	private TextView strQuoteView;
	private TextView dateQuoteView;
	private RatingBar ratingQuoteView;
	private Button buttonOk;
	private Button buttonCancel;
	private QuoteActivity act = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quote);

		/**
		 * Récupération de la citation et de son ID
		 */
		quote = (Quote) getIntent().getExtras()
			.getSerializable("quote");
		quoteId = getIntent().getExtras().getInt("quoteId");
		
		
		
		Bundle args = new Bundle();
		args.putSerializable("quote", quote);
		
		strQuoteView = (TextView) findViewById(R.id.quoteText);
		strQuoteView.setText(this.quote.getStrQuote());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		dateQuoteView = (TextView) findViewById(R.id.quoteDate);
		dateQuoteView.setText(sdf.format(this.quote.getCreationDate()));
		
		ratingQuoteView = (RatingBar) findViewById(R.id.quoteRating);
		ratingQuoteView.setRating(this.quote.getRating());
		
		buttonOk = (Button) findViewById(R.id.quoteButtonOk);
		buttonCancel = (Button) findViewById(R.id.quoteButtonCancel);
		
		
		
		/**
		 * Gestion des clicks sur les boutons OK et Cancel
		 */
		buttonOk.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				
				quote.setRating((int) ratingQuoteView.getRating());
				i.putExtra("quote", quote);
				i.putExtra("quoteId", quoteId);
				
				setResult(RESULT_OK, i);
				finish();
			}
		});
		
		buttonCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setResult(RESULT_CANCELED);
				
				finish();
			}
		});
		
		
		/**
		 * Gestion du click long sur le texte de la citation
		 */
		strQuoteView.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {

				LayoutInflater inflater = LayoutInflater.from(act);
				AlertDialog.Builder adb = new AlertDialog.Builder(act);
				View view = inflater.inflate(R.layout.alert_dialog_modify_quote, null);
				
				adb.setView(view);
				adb.setTitle("Modify quote");
				adb.setIcon(android.R.drawable.ic_menu_edit);
				
				((EditText) view.findViewById(R.id.modifyQuoteText)).setText(quote.getStrQuote());
				
				adb.setPositiveButton(
					android.R.string.ok, 
					act
				);
				
				adb.setNegativeButton(
					android.R.string.cancel, 
					new Dialog.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					}
				);
				
				adb.show();
				
				return true;
			}
		});
	}	


	@Override
	public void onClick(DialogInterface dialog, int which) {
		EditText text = (EditText) ((AlertDialog) dialog).findViewById(R.id.modifyQuoteText);
		
		this.quote.setStrQuote(text.getText().toString());
		this.strQuoteView.setText(this.quote.getStrQuote());
		
		dialog.dismiss();
	}


	public Quote getQuote() {
		return quote;
	}


	public void setQuote(Quote quote) {
		this.quote = quote;
	}
}
