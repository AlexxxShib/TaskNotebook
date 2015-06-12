package com.shibkov.tasknotebook.app.fragments.dialogs;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.shibkov.tasknotebook.app.R;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Alexey on 08.06.2015.
 */
public class ChoiceDateDialog extends DialogFragment {

    private static final String ARG_TIME = "ARG_TIME";

    public interface ChoiceDateListener {
        void dateSelected(long date);
    }

    public static ChoiceDateDialog getInstance(long startTime, ChoiceDateListener listener) {
        Bundle bundle = new Bundle();
        bundle.putLong(ARG_TIME, startTime);

        ChoiceDateDialog dialog = new ChoiceDateDialog();
        dialog.setArguments(bundle);
        dialog.setListener(listener);
        return dialog;
    }

    private ChoiceDateListener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choice_date, container, false);
        getDialog().setTitle(R.string.abc_dialog_title_choice_date);

        final Calendar startDate = Calendar.getInstance();
        startDate.setTimeInMillis(getArguments().getLong(ARG_TIME));

        final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
        datePicker.init(startDate.get(Calendar.YEAR),
                startDate.get(Calendar.MONTH), startDate.get(Calendar.DAY_OF_MONTH), null);
        datePicker.setMinDate(System.currentTimeMillis());

        view.findViewById(R.id.ok_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long date = getDate(datePicker.getYear(),
                        datePicker.getMonth(), datePicker.getDayOfMonth());
                if (listener != null) {
                    listener.dateSelected(date);
                }
                dismiss();
            }
        });

        return view;
    }

    public void setListener(ChoiceDateListener listener) {
        this.listener = listener;
    }

    private static long getDate(int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        return calendar.getTimeInMillis();
    }
}
