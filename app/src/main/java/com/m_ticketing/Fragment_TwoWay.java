package com.m_ticketing;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
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
public class Fragment_TwoWay extends Fragment {
    private int mYear, mMonth, mDay, mHour, mMinute;
    EditText dptDate, dptTime, arvDate, arvTime;
    NumberPicker tickets;
    Spinner train_class, stations;
    Button pay;
    private int fare;
    private String depDate;
    private String depTime;
    private String arDate;
    private String arTime;

    private int tiko;
    private int TrainClass;
    private int station;

    public Fragment_TwoWay() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.two_way_ticketting, container, false);
        dptDate = (EditText) view.findViewById(R.id.dptDate);
        dptTime = (EditText) view.findViewById(R.id.dptTime);
        arvDate = (EditText) view.findViewById(R.id.arvDate);
        arvTime = (EditText) view.findViewById(R.id.arvTime);
        tickets = (NumberPicker) view.findViewById(R.id.no_of_tickets);
        train_class = (Spinner) view.findViewById(R.id.train_class);
        stations = (Spinner) view.findViewById(R.id.stations);
        pay = (Button) view.findViewById(R.id.pay);

        tickets.setMaxValue(1000);
        tickets.setMinValue(1);


        dptTime.setOnClickListener(new View.OnClickListener() {
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

                                dptTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }

        });
        arvTime.setOnClickListener(new View.OnClickListener() {
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

                                arvTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }

        });

        dptDate.setOnClickListener(new View.OnClickListener() {
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

                                dptDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        arvDate.setOnClickListener(new View.OnClickListener() {
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

                                arvDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                depDate = dptDate.getText().toString();
                depTime = dptTime.getText().toString();
                arDate  = arvDate.getText().toString();
                arTime  = arvTime.getText().toString();

                if (depDate.isEmpty() || depTime.isEmpty() || arDate.isEmpty() || arTime.isEmpty()) {
                    dptDate.setError("select the depature date");
                    dptTime.setError("select the depature time");
                    arvDate.setError("select the return date");
                    arvTime.setError("select the return time");
                } else if (depDate.equals(arDate)) {
                    dptDate.setError("Ensure the dates are correct");

                } else if (depTime.equals(arTime)) {
                    dptTime.setError("Ensure the time is selected correctly");

                } else {
                    dptTime.setError(null);
                    dptDate.setError(null);
                    dptDate.setError(null);
                    dptTime.setError(null);
                    arvDate.setError(null);
                    arvTime.setError(null);
                    TwoWayPayment();

                    dptTime.setText("");
                    dptDate.setText("");
                    dptDate.setText("");
                    dptTime.setText("");
                    arvDate.setText("");
                    arvTime.setText("");
                }


            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    private void TwoWayPayment() {
        tiko        = tickets.getValue();
        TrainClass  = train_class.getSelectedItemPosition();
        station     = stations.getSelectedItemPosition();
/*when train class is business class*/

        if (TrainClass == 0 && station == 0) {

            fare = 700 * 2 * tiko * 2;


        } else if (TrainClass == 0 && station == 1) {
            fare = 300 * 2 * tiko * 2;
        } else if (TrainClass == 0 && station == 2) {
            fare = 200 * 2 * tiko;
        } else if (TrainClass == 0 && station == 3) {
            fare = 200 * 2 * tiko * 2;
        } else if (TrainClass == 0 && station == 4) {
            fare = 700 * 2 * tiko * 2;
        } else if (TrainClass == 0 && station == 5) {
            fare = 100 * 2 * tiko * 2;
        } else if (TrainClass == 0 && station == 6) {
            fare = 100 * 2 * tiko * 2;
        } else if (TrainClass == 0 && station == 7) {
            fare = 100 * 2 * tiko * 2;
        }
//        when trainclass is economy class

        else if (TrainClass == 1 && station == 0) {
            fare = 700 * tiko * 2;
        } else if (TrainClass == 1 && station == 1) {
            fare = 300 * tiko * 2;
        } else if (TrainClass == 1 && station == 2) {
            fare = 200 * tiko * 2;
        } else if (TrainClass == 1 && station == 3) {
            fare = 200 * tiko * 2;
        } else if (TrainClass == 1 && station == 4) {
            fare = 700 * tiko * 2;

        } else if (TrainClass == 1 && station == 6) {
            fare = 100 * tiko * 2;
        } else if (TrainClass == 1 && station == 7)

        {
            fare = 100 * tiko * 2;
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
                .setBodyParameter("action", "savetwoway")
                .setBodyParameter("trainStation", ""+station)
                .setBodyParameter("departure_date", depDate)
                .setBodyParameter("departure_time", depTime)
                .setBodyParameter("return_date", arDate)
                .setBodyParameter("return_time", arTime)
                .setBodyParameter("no_of_tickets", ""+tiko)
                .setBodyParameter("class0", "" + TrainClass)
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
