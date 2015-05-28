package com.shibkov.tasknotebook.app.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import com.shibkov.tasknotebook.app.R;
import com.shibkov.tasknotebook.app.utils.NotebookAssetManager;
import com.shibkov.tasknotebook.app.views.adapters.IconsSelectAdapter;

import java.util.List;

/**
 * Created by alexxxshib
 */
public class ChoiceIconDialogFragment extends DialogFragment implements View.OnClickListener {

    public interface OnFinishChoiceIconDialog {
        void selectedIcon(String iconPath);
    }

    private List<String> icons;

    public static ChoiceIconDialogFragment newInstance() {
        return new ChoiceIconDialogFragment();
    }

    private IconsSelectAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choice_icon, container, false);

        icons = NotebookAssetManager.getIconsList(getActivity());
        adapter = new IconsSelectAdapter(getActivity(), icons);
        ((GridView) view.findViewById(R.id.iconsGridView)).setAdapter(adapter);

        view.findViewById(R.id.selectIconButton).setOnClickListener(this);
        getDialog().setTitle(R.string.abc_title_choice_category_icon);
        return view;
    }

    @Override
    public void onClick(View v) {
        ((OnFinishChoiceIconDialog) getActivity()).selectedIcon(icons.get(adapter.getSelectedIcon()));
        dismiss();
    }
}
