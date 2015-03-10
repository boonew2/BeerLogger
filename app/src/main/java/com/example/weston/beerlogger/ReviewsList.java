package com.example.weston.beerlogger;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import java.util.List;


public class ReviewsList extends ListActivity {
    private ReviewsDataSource dataSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataSource = new ReviewsDataSource(this);
        dataSource.open();
        List<Review> reviews = dataSource.getAllReviews();
        ArrayAdapter<Review> adapter = new ArrayAdapter<Review>(this,android.R.layout.simple_list_item_1, reviews);
        setListAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reviews_list, menu);
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

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id){
        Review selection = (Review) listView.getItemAtPosition(position);
        Toast.makeText(this, selection.getName() +": "+selection.getId(),Toast.LENGTH_SHORT).show();
    }
}
