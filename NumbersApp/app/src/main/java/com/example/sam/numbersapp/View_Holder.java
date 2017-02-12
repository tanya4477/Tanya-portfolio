package com.example.sam.numbersapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by tanya on 03.06.16.
 */
public class View_Holder extends RecyclerView.ViewHolder {
    public static final String DEFAULT_TEXT_COLOR = "#000000";//Black
    public static final String SELECTED_BACKGROUND_COLOR = "#FF0000";//Red
    public static final String SELECTED_TEXT_COLOR = "#FFFFFF";//White
    public static final String TOUCHED_BACKGROUND_COLOR = "#0000FF";//Blue
    public static final String FOCUSED_BACKGROUND_COLOR = "#00FF00";//Green

    /**
     * Variables declared here
     */
    CardView cv;
    TextView name;
    ImageView imageView;
    Context context;

    /**
     * Parameterized Constructor
     *
     * @param itemView
     */
    View_Holder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        cv = (CardView) itemView.findViewById(R.id.cardView);
        name = (TextView) itemView.findViewById(R.id.name);
        name.setTextColor(Color.parseColor(DEFAULT_TEXT_COLOR));
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
    }
}
