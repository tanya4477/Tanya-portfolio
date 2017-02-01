package com.example.sam.numbersapp;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * This is a Custom View Holder Class
 * Created by tanya on 05.06.16.
 */
public class CustomViewHolder extends View_Holder {

    CustomViewHolder(final View itemView) {
        super(itemView);

        itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getActionMasked();
                if(action == MotionEvent.ACTION_DOWN){
                    // Change the background color to blue and text color to white
                    itemView.setBackgroundColor(Color.parseColor(TOUCHED_BACKGROUND_COLOR));
                    name.setTextColor(Color.parseColor(SELECTED_TEXT_COLOR));
                    return true;
                }
                else if(action == MotionEvent.ACTION_UP) {
                    itemView.setBackgroundColor(Color.parseColor(SELECTED_BACKGROUND_COLOR));
                    name.setTextColor(Color.parseColor(SELECTED_TEXT_COLOR));
                    Intent intent = new Intent(context,DetailActivity.class);
                    intent.putExtra("key_name", name.getText());
                    context.startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        itemView.setFocusable(true);
        itemView.setFocusableInTouchMode(true);
        itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //When in focus
                if(hasFocus){
                    // Change the background color to green and text color to white
                    itemView.setBackgroundColor(Color.parseColor(FOCUSED_BACKGROUND_COLOR));
                    name.setTextColor(Color.parseColor(SELECTED_TEXT_COLOR));
                }
            }
        });
    }
}
