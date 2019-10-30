package com.peace.searchablerecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.peace.searchablerecyclerview.extra.MyDividerItemDecoration;
import com.peace.searchablerecyclerview.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ContactAdapter.ContactAdapterListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private List<Contact> contactList;
    private ContactAdapter mAdapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        contactList = new ArrayList<>();

        contactList.add(new Contact("Alex","4321"));
        contactList.add(new Contact("Bernard","4321"));
        contactList.add(new Contact("Kynsai","1234"));
        contactList.add(new Contact("Kyrshan","4321"));
        contactList.add(new Contact("Benny","4321"));
        contactList.add(new Contact("Andy","4321"));
        contactList.add(new Contact("Bob","4321"));
        contactList.add(new Contact("Melvin","4321"));
        contactList.add(new Contact("Mebala","4321"));
        contactList.add(new Contact("Lucy","4321"));
        contactList.add(new Contact("Arelia","4321"));
        contactList.add(new Contact("Evan","4321"));
        contactList.add(new Contact("Saphi","4321"));
        contactList.add(new Contact("Melboreen","4321"));
        contactList.add(new Contact("Apki","4321"));


        mAdapter = new ContactAdapter(this, contactList, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onContactSelected(Contact contact) {
        Toast.makeText(getApplicationContext(), "Selected: " + contact.getName() + ", " + contact.getPhone(), Toast.LENGTH_LONG).show();
    }
}
