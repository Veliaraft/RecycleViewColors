package com.example.recyclerviewcolors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Collor> colors = new ArrayList<>();
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.list);
        setClrs(this);
        ColorAdapter adapter = new ColorAdapter(this, colors);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration (new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    private void setClrs(Context context){
        try {
            XmlPullParser parser = context.getResources().getXml(R.xml.my_colors);
            while (parser.getEventType()!= XmlPullParser.END_DOCUMENT) {
                if (parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals("color")) {
                    colors.add(new Collor(parser.getAttributeValue(null, "name")));
                }
                parser.next();
            }
        } catch (Throwable ignored){ }
    }
}