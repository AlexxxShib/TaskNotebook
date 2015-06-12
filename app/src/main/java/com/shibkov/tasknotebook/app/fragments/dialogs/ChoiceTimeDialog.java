package com.shibkov.tasknotebook.app.fragments.dialogs;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import com.shibkov.tasknotebook.app.R;

import java.util.Calendar;

/**
 * Created by Alexey on 08.06.2015.
 */
public class ChoiceTimeDialog extends DialogFragment {

    private static final String ARG_TIME = "ARG_TIME";

    public interface ChoiceTimeListener {
        void timeSelected(long time);
    }

    public static ChoiceTimeDialog getInstance(long startTime, ChoiceTimeListener listener) {
        Bundle bundle = new Bundle();
        bundle.putLong(ARG_TIME, startTime);

        ChoiceTimeDialog dialog = new ChoiceTimeDialog();
        dialog.setArguments(bundle);
        dialog.setListener(listener);
        return dialog;
    }

    private ChoiceTimeListener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choice_time, container, false);
        getDialog().setTitle(R.string.abc_dialog_title_choice_time);

        final Calendar startTime = Calendar.getInstance();
        startTime.setTimeInMillis(getArguments().getLong(ARG_TIME));

        final TimePicker timePicker = (TimePicker) view.findViewById(R.id.time_picker);
        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(startTime.get(Calendar.HOUR));
        timePicker.setCurrentMinute(startTime.get(Calendar.MINUTE));

        view.findViewById(R.id.ok_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = getTime(timePicker.getCurrentHour(), timePicker.getCurrentMinute());
                calendar.set(Calendar.DAY_OF_YEAR, startTime.get(Calendar.DAY_OF_YEAR));
                long time = calendar.getTimeInMillis();
                if (listener != null) {
                    listener.timeSelected(time);
                }
                dismiss();
            }
        });

        return view;
    }

    public void setListener(ChoiceTimeListener listener) {
        this.listener = listener;
    }

    private static Calendar getTime(int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, hour);
        calendar.set(Calendar.MINUTE, minute);
        return calendar;
    }
}
