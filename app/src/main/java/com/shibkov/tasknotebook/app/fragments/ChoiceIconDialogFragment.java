package com.shibkov.tasknotebook.app.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import com.shibkov.tasknotebook.app.R;
import com.shibkov.tasknotebook.app.views.adapters.IconsSelectAdapter;

/**
 * Created by alexxxshib
 */
public class ChoiceIconDialogFragment extends DialogFragment implements View.OnClickListener {

    private static final String[] ICONS = new String[]
            {"ic_events.png", "ic_home.png", "ic_mail.png", "ic_ring.png",
                    "ic_shop.png", "ic_time.png", "ic_timer.png", "ic_travel.png"};

    public interface OnFinishChoiceIconDialog {
        void selectedIcon(String iconPath);
    }

    public static ChoiceIconDialogFragment newInstance() {
        return new ChoiceIconDialogFragment();
    }

    private IconsSelectAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choice_icon, container, false);
        adapter = new IconsSelectAdapter(getActivity(), ICONS);
        ((GridView) view.findViewById(R.id.iconsGridView)).setAdapter(adapter);
        view.findViewById(R.id.selectIconButton).setOnClickListener(this);
        getDialog().setTitle(R.string.abc_title_choice_category_icon);
        return view;
    }

    @Override
    public void onClick(View v) {
        ((OnFinishChoiceIconDialog) getActivity()).selectedIcon(ICONS[adapter.getSelectedIcon()]);
        dismiss();
    }
}
