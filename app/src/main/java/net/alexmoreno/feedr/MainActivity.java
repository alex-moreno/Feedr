package net.alexmoreno.feedr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.text.SpannedString;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    Button mainButton;
    ListView mainListView;
    ArrayAdapter<Spanned> mArrayAdapter;
    ArrayList<Spanned> mNameList = new ArrayList<Spanned>();

    // Current active element.
    Integer activeElement = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainButton = (Button) findViewById(R.id.main_button);
        mainButton.setOnClickListener((View.OnClickListener) this);

        // Access the ListView
        mainListView = (ListView) findViewById(R.id.main_listview);
        mNameList.add(new SpannedString("Test"));

        // Create an ArrayAdapter for the ListView.
        mArrayAdapter = new ArrayAdapter<Spanned>(
                this,
                android.R.layout.simple_list_item_1,
                mNameList
        );
        // Set the ListView to use the ArrayAdapter
        mainListView.setAdapter(mArrayAdapter);
        // 5. Set this activity to react to list items being pressed
        mainListView.setOnItemClickListener(this);

        // Toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        // Also add that value to the list shown in the ListView
        mNameList.add(new SpannedString("Test"));
        mArrayAdapter.notifyDataSetChanged();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String txt = "Lorem ipsum <strong>dolor</strong> sit amet, consectetur adipiscing elit. Cras euismod eros in leo imperdiet aliquet. " +
                 "Pellentesque molestie pretium porta. Aenean id dui non augue consectetur interdum.\n";

        mNameList.set(position, Html.fromHtml(txt));

        // We close the previous element.
        if (activeElement != null) {
            mNameList.set(activeElement, new SpannedString("Test"));
        }
        Log.d("msg", "Active " + activeElement);

        // and keep the current active element to close it in the next click.
        activeElement = position;

        mArrayAdapter.notifyDataSetChanged();
    }
}
