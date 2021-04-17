package com.yusufpats.studentcounteractivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private int currentCount = 0;
    private TextView counterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        updateCounterTextView();
    }


    private void initViews() {
        // finding my views
        Button incrementButton = this.findViewById(R.id.increment_button);
        Button decrementButton = this.findViewById(R.id.decrement_button);
        counterTextView = this.findViewById(R.id.counter_text_view);


        // attaching events to my views
        View.OnClickListener incrementlistener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentCount++;
                updateCounterTextView();
            }
        };

        View.OnClickListener decrementListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentCount > 0) {
                    currentCount--;
                    updateCounterTextView();
                }
            }
        };

        incrementButton.setOnClickListener(incrementlistener);
        decrementButton.setOnClickListener(decrementListener);

    }

    private void updateCounterTextView() {

        int redColor = ContextCompat.getColor(this, R.color.red);
        int yellowColor = ContextCompat.getColor(this, R.color.yellow);
        int greenColor = ContextCompat.getColor(this, R.color.green);

        counterTextView.setText(String.valueOf(currentCount));

        if (currentCount >= 0 && currentCount <= 10) {
            counterTextView.setTextColor(redColor);
        } else if (currentCount > 10 && currentCount <= 15) {
            counterTextView.setTextColor(yellowColor);
        } else {
            counterTextView.setTextColor(greenColor);
        }
    }
}