package com.example.infs3634individualassignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;



public class MainActivity extends AppCompatActivity {
    private Object pauseLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button twelveTime = findViewById(R.id.twelveTime);
        Button twentyFourTime = findViewById(R.id.twentyFourTime);

        // The thread below allows for the app to update the city times in real-time by refreshing every second
        // However the thread is interrupted upon the clicking of any of the two buttons to switch time formats (12-24)


        final Thread initialThread = new Thread() {

            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            public void run() {
                                updateTwelveTime();
                            }
                        });
                    }
                } catch (InterruptedException e) {

                }

            }
        };


        setImages();

        initialThread.start();


        // These methods set click actions for the buttons to switch between 12 and 24 hr time formats. It interrupts the initial thread and therefore
        // stops the real-time updating of time. This is because I could not figure out a way to pause and resume threads with concepts
        // that I could understand. So these buttons will update the time to a current value without real-time updating

        twelveTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    if (initialThread.isAlive()) {
                        initialThread.interrupt();
                        initialThread.join();
                    }
                }catch (InterruptedException e){

                }
                updateTwelveTime();
            }
        });

        twentyFourTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    if (initialThread.isAlive()) {
                        initialThread.interrupt();
                        initialThread.join();
                    }
                }catch (InterruptedException e) {

                }
                updateTwoFourTime();
            }
        });

    }


// Method for obtaining the current time in a particular timezone, such as Europe/London. Returns a ZonedDateTime variable
    public ZonedDateTime currentTime(String zone) {
        Instant now = Instant.now();
        ZonedDateTime currentTime = now.atZone(ZoneId.of(zone));
        return currentTime;
    }

    // Method for converting current time in a particular timezone to 12hr Format
    public String twelveFormatter(ZonedDateTime time) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("hh:mm:ss");
        String currentTime = time.format(dateFormat);
        return currentTime;
    }

    // Method for converting current time in a particular timezone to 24hr Format
    public String twoFourFormatter(ZonedDateTime time) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        String currentTime = time.format(dateFormat);
        return currentTime;
    }

    // Method for setting the images of each city

    public void setImages() {
        LinearLayout sydney = findViewById(R.id.sydney);
        ImageView sydneyImage = sydney.findViewById(R.id.cityImage);
        sydneyImage.setImageResource(R.drawable.harbourbridge);

        LinearLayout london = findViewById(R.id.london);
        ImageView londonImage = london.findViewById(R.id.cityImage);
        londonImage.setImageResource(R.drawable.londonbridge);

        LinearLayout shanghai = findViewById(R.id.shanghai);
        ImageView shanghaiImage = shanghai.findViewById(R.id.cityImage);
        shanghaiImage.setImageResource(R.drawable.shanghaitower);

        LinearLayout tokyo = findViewById(R.id.tokyo);
        ImageView tokyoImage = tokyo.findViewById(R.id.cityImage);
        tokyoImage.setImageResource(R.drawable.tokyotower);

        LinearLayout newYork = findViewById(R.id.newYork);
        ImageView newYorkImage = newYork.findViewById(R.id.cityImage);
        newYorkImage.setImageResource(R.drawable.statueofliberty);
    }


    // Method that sets each city's name and current time, with 12hr Format
    public void updateTwelveTime() {

        LinearLayout sydney = findViewById(R.id.sydney);
        TextView sydneyTime = sydney.findViewById(R.id.timeNow);
        TextView sydneyName = sydney.findViewById(R.id.cityName);
        sydneyName.setText(R.string.location1);
        sydneyTime.setText(twelveFormatter(currentTime("Australia/Sydney")));

        LinearLayout london = findViewById(R.id.london);
        TextView londonTime = london.findViewById(R.id.timeNow);
        TextView londonName = london.findViewById(R.id.cityName);
        londonName.setText(R.string.location2);
        londonTime.setText(twelveFormatter(currentTime("Europe/London")));

        LinearLayout shanghai = findViewById(R.id.shanghai);
        TextView shanghaiTime = shanghai.findViewById(R.id.timeNow);
        TextView shanghaiName = shanghai.findViewById(R.id.cityName);
        shanghaiName.setText(R.string.location3);
        shanghaiTime.setText(twelveFormatter(currentTime("Asia/Shanghai")));

        LinearLayout tokyo = findViewById(R.id.tokyo);
        TextView tokyoTime = tokyo.findViewById(R.id.timeNow);
        TextView tokyoName = tokyo.findViewById(R.id.cityName);
        tokyoName.setText(R.string.location4);
        tokyoTime.setText(twelveFormatter(currentTime("Asia/Tokyo")));

        LinearLayout newYork = findViewById(R.id.newYork);
        TextView newYorkTime = newYork.findViewById(R.id.timeNow);
        TextView newYorkName = newYork.findViewById(R.id.cityName);
        newYorkName.setText(R.string.location5);
        newYorkTime.setText(twelveFormatter(currentTime("America/New_York")));


    }

    // Method that sets each city's name and current time, with 24hr Format
    public void updateTwoFourTime() {

        LinearLayout sydney = findViewById(R.id.sydney);
        TextView sydneyTime = sydney.findViewById(R.id.timeNow);
        TextView sydneyName = sydney.findViewById(R.id.cityName);
        sydneyName.setText(R.string.location1);
        sydneyTime.setText(twoFourFormatter(currentTime("Australia/Sydney")));

        LinearLayout london = findViewById(R.id.london);
        TextView londonTime = london.findViewById(R.id.timeNow);
        TextView londonName = london.findViewById(R.id.cityName);
        londonName.setText(R.string.location2);
        londonTime.setText(twoFourFormatter(currentTime("Europe/London")));

        LinearLayout shanghai = findViewById(R.id.shanghai);
        TextView shanghaiTime = shanghai.findViewById(R.id.timeNow);
        TextView shanghaiName = shanghai.findViewById(R.id.cityName);
        shanghaiName.setText(R.string.location3);
        shanghaiTime.setText(twoFourFormatter(currentTime("Asia/Shanghai")));

        LinearLayout tokyo = findViewById(R.id.tokyo);
        TextView tokyoTime = tokyo.findViewById(R.id.timeNow);
        TextView tokyoName = tokyo.findViewById(R.id.cityName);
        tokyoName.setText(R.string.location4);
        tokyoTime.setText(twoFourFormatter(currentTime("Asia/Tokyo")));

        LinearLayout newYork = findViewById(R.id.newYork);
        TextView newYorkTime = newYork.findViewById(R.id.timeNow);
        TextView newYorkName = newYork.findViewById(R.id.cityName);
        newYorkName.setText(R.string.location5);
        newYorkTime.setText(twoFourFormatter(currentTime("America/New_York")));


    }
}

