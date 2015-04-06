package com.shibkov.tasknotebook.app.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import com.shibkov.tasknotebook.app.R;
import com.shibkov.tasknotebook.app.utils.Logger;
import com.shibkov.tasknotebook.app.views.adapters.IconsSelectAdapter;

/**
 * Created by alexxxshib
 */
public class ChoiceIconDialogFragment extends DialogFragment implements AdapterView.OnItemClickListener {

    private static final String[] ICONS = new String[]
            {"ic_events.png", "ic_home.png", "ic_mail.png", "ic_ring.png",
                    "ic_shop.png", "ic_time.png", "ic_timer.png", "ic_travel.png"};

    private GridView iconsGridView;

    public static ChoiceIconDialogFragment newInstance() {
        return new ChoiceIconDialogFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choice_icon, container, false);
        iconsGridView = (GridView) view.findViewById(R.id.iconsGridView);
        iconsGridView.setAdapter(new IconsSelectAdapter(getActivity(), ICONS));
        iconsGridView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //todo
        Logger.info(position + " choised icon");
        iconsGridView.requestFocusFromTouch();
    }
}
