package com.example.precious_lord.travelapp;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BookingStatus extends AppCompatActivity {

    EditText ticket_no_prompt;
    Button view_details;

    TextView booking_status_tv;
    TextView departure_terminal;
    TextView arrival_terminal;
    TextView departure_date;
    TextView no_of_travelers;

    TextView price;

    Button okay;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_status);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Booking Status");

        // Initialize views
        ticket_no_prompt = findViewById(R.id.ticket_no_prompt);
        view_details = findViewById(R.id.view_details);

        booking_status_tv = findViewById(R.id.booking_status_tv);
        departure_terminal = findViewById(R.id.departure_terminal);
        arrival_terminal = findViewById(R.id.arrival_terminal);
        departure_date = findViewById(R.id.departure_date);
        no_of_travelers = findViewById(R.id.no_of_travelers);
        price = findViewById(R.id.price);
        okay = findViewById(R.id.okay);

        // Initialize objects
        databaseHelper = new DatabaseHelper(this);

        // Initialize Listeners
        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticket_no_prompt.setText(null);
                booking_status_tv.setText(null);
                departure_terminal.setText(null);
                arrival_terminal.setText(null);
                departure_date.setText(null);
                no_of_travelers.setText(null);
                price.setText(null);
            }
        });

        view_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//      if(profileDetails[0] == null){
//            Toast.makeText(Profile_Activity.this,  "Nothing", Toast.LENGTH_LONG).show();
//        }else{
//          Toast.makeText(Profile_Activity.this,  profileDetails[0], Toast.LENGTH_LONG).show();
//        }

                if (databaseHelper.checkTicketNo(ticket_no_prompt.getText().toString())){

                    Snackbar.make(v, "Ticket number exists", Snackbar.LENGTH_SHORT).show();

                    String[] profileDetails = databaseHelper.bookingProfile(ticket_no_prompt.getText().toString());
                    booking_status_tv.setText(profileDetails[0]);
                    departure_terminal.setText(profileDetails[1]);
                    arrival_terminal.setText(profileDetails[2]);
                    departure_date.setText(profileDetails[3]);
                    no_of_travelers.setText(profileDetails[4]);
                    price.setText(profileDetails[5]);
                }else{
                    Snackbar.make(v, "Ticket number does not exists", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

//        Intent intent = getIntent();
//        String user_email = intent.getExtras().getString("email");
//


    }
}
