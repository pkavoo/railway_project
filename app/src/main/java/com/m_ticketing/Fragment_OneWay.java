package com.m_ticketing;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.Calendar;

import static com.m_ticketing.ini.Constants.Server.PROJECT;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_OneWay extends DialogFragment {

    Button btnDatePicker, btnTimePicker;
    NumberPicker no_of_tickets;
    Spinner stations, train_class;
    TimePicker trainTime;
    Button setTime, pay;
    TicketModel ticketModel;
    AlertDialog payment;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;
    private String format = "";
    private int fare;
    private PackageManager pac;
    private int tickets;
    private int TrainClass;
    private int station;
    private String startDate;
    private String startTime;

    public Fragment_OneWay() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.one_way_ticketting, container, false);

        btnDatePicker = (Button) view.findViewById(R.id.btn_date);
        no_of_tickets = (NumberPicker) view.findViewById(R.id.no_of_tickets);
        stations = (Spinner) view.findViewById(R.id.stations);
        train_class = (Spinner) view.findViewById(R.id.train_class);
        /*trainTime = (TimePicker) view.findViewById(R.id.train_time);*/
        btnTimePicker = (Button) view.findViewById(R.id.btn_time);
        pay = (Button) view.findViewById(R.id.pay);
        calendar = Calendar.getInstance();
        txtDate = (EditText) view.findViewById(R.id.txtDate);
        txtTime = (EditText) view.findViewById(R.id.txtTime);


        no_of_tickets.setMaxValue(1000);
        no_of_tickets.setMinValue(1);


        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                txtTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }

        });


        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDate = txtDate.getText().toString();
                startTime = txtTime.getText().toString();
                if (startDate.isEmpty() || startTime.isEmpty()) {
                    txtDate.setError("Select travel date");
                    txtTime.setError("Select travel time");
                } else {
                    txtDate.setError(null);
                    txtTime.setError(null);
                    CalculatePayment();
                    txtDate.setText("");
                    txtTime.setText("");
                }


            }
        });

        // Inflate the layout for this fragment
        return view;
    }


    private void CalculatePayment() {
        tickets = no_of_tickets.getValue();
        TrainClass = train_class.getSelectedItemPosition();
        station = stations.getSelectedItemPosition();
/*when train class is business class*/


        if (TrainClass == 0 && station == 0) {

            fare = 700 * 2 * tickets;


        } else if (TrainClass == 0 && station == 1) {
            fare = 300 * 2 * tickets;
        } else if (TrainClass == 0 && station == 2) {
            fare = 200 * 2 * tickets;
        } else if (TrainClass == 0 && station == 3) {
            fare = 200 * 2 * tickets;
        } else if (TrainClass == 0 && station == 4) {
            fare = 700 * 2 * tickets;
        } else if (TrainClass == 0 && station == 5) {
            fare = 100 * 2 * tickets;
        } else if (TrainClass == 0 && station == 6) {
            fare = 100 * 2 * tickets;
        } else if (TrainClass == 0 && station == 7) {
            fare = 100 * 2 * tickets;
        }
//        when trainclass is economy class

        else if (TrainClass == 1 && station == 0) {
            fare = 700 * tickets;
        } else if (TrainClass == 1 && station == 1) {
            fare = 300 * tickets;
        } else if (TrainClass == 1 && station == 2) {
            fare = 200 * tickets;
        } else if (TrainClass == 1 && station == 3) {
            fare = 200 * tickets;
        } else if (TrainClass == 1 && station == 4) {
            fare = 700 * tickets;

        } else if (TrainClass == 1 && station == 6) {
            fare = 100 * tickets;
        } else if (TrainClass == 1 && station == 7)

        {
            fare = 100 * tickets;
        }
        takeonline();
        new AlertDialog.Builder(getActivity())
                .setTitle("Payment Details")
                .setMessage("Pay sh." + fare + " to the paybill number  231456")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .show();
    }

    private void takeonline() {

        Ion.with(this)
                .load(PROJECT)
                .setBodyParameter("action", "saveoneway")
                .setBodyParameter("startTime", startTime)
                .setBodyParameter("startDate", startDate)
                .setBodyParameter("noOfTicket", "" + tickets)
                .setBodyParameter("trainClass", "" + TrainClass)
                .setBodyParameter("station", "" + station)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if (e == null)

                            Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
                    }
                });
    }


}