package com.example.weston.beerlogger;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;


public class ReviewActivity extends ActionBarActivity {
    private ReviewsDataSource dataSource;
    private Review review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        Intent intent = getIntent();
        long reviewId = intent.getLongExtra(ReviewsList.REVIEW_ID,0);
        dataSource = new ReviewsDataSource(this);
        dataSource.open();
        review = dataSource.getReview(reviewId);
        fillLayout();
    }

    public void editMode(View view){
        EditText editText = (EditText) findViewById(R.id.review_name_edit);
        TextView textView = (TextView) findViewById(R.id.review_name);
        editText.setText(textView.getText().toString());
        textView.setVisibility(View.INVISIBLE);
        editText.setVisibility(View.VISIBLE);

        editText = (EditText) findViewById(R.id.review_notes_edit);
        textView = (TextView) findViewById(R.id.review_notes);
        editText.setText(textView.getText().toString());
        textView.setVisibility(View.INVISIBLE);
        editText.setVisibility(View.VISIBLE);

        editText = (EditText) findViewById(R.id.review_price_edit);
        textView = (TextView) findViewById(R.id.review_price);
        editText.setText(textView.getText().toString());
        textView.setVisibility(View.INVISIBLE);
        editText.setVisibility(View.VISIBLE);

    }

    private void fillLayout(){
        TextView textView = (TextView) findViewById(R.id.review_name);
        textView.setText(review.getName());
        textView = (TextView) findViewById(R.id.review_price);
        textView.setText(String.format("%.2f", review.getPrice()));
        textView = (TextView) findViewById(R.id.review_notes);
        textView.setText(review.getReview());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_review, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
