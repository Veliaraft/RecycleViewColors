package com.example.recyclerviewcolors;

import android.content.Context;
import android.view.LayoutInflater;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.xmlpull.v1.XmlPullParser;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final Context context;
    private final List<Collor> colors;

    ColorAdapter(Context context, List<Collor> colors) {
        this.colors = colors;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Collor c = colors.get(position);
        holder.nameView.setText(c.getName());
        String colorName = c.getName();
        try {
            XmlPullParser parser = context.getResources().getXml(R.xml.my_colors);
            while (parser.getEventType()!= XmlPullParser.END_DOCUMENT) {
                if (parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals("color")) {
                    if(parser.getAttributeValue(null, "name").equals(colorName))
                        colorName = parser.nextText();
                }
                parser.next();
            }
        } catch (Throwable ignored) {
        }
        holder.nameView.setBackgroundColor(Color.parseColor(colorName));
        holder.nameView.setTextColor(Color.BLACK - Color.parseColor(colorName) -1);
    }

    @Override
    public int getItemCount() {
        return colors.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView;
        ViewHolder(View view){
            super(view);
            nameView = view.findViewById(R.id.name);
        }
    }
}
