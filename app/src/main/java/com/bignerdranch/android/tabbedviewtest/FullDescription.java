package com.bignerdranch.android.tabbedviewtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FullDescription extends AppCompatActivity {

    private String mFullDescription;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_description);

        mTextView = (TextView) findViewById(R.id.full_description);

        // Get string of full desc
        Intent intent = getIntent();
        String fullDesc = intent.getStringExtra(Tab2Adapter.EXTRA_MESSAGE);
        mTextView.setText(fullDesc);

    }
}
