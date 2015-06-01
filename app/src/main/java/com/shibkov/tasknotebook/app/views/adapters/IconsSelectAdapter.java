package com.shibkov.tasknotebook.app.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.shibkov.tasknotebook.app.R;
import com.shibkov.tasknotebook.app.utils.Logger;
import com.shibkov.tasknotebook.app.utils.NotebookAssetManager;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by alexxxshib
 */
public class IconsSelectAdapter extends ArrayAdapter<String> {

    public static final int NOT_SELECTED = -1;

    private int selectedIcon = NOT_SELECTED;

    public IconsSelectAdapter(Context context, List<String> objects) {
        super(context, R.layout.item_cell_icon, objects);
    }

    public int getSelectedIcon() {
        return selectedIcon;
    }

    public void setSelectedIcon(int pos) {
        if (pos >= 0 || pos < getCount()) {
            selectedIcon = pos;
            notifyDataSetChanged();
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(getContext(), R.layout.item_cell_icon, null);
        }
        final ImageView iconView = (ImageView) convertView.findViewById(R.id.iconImage);

        if (position == selectedIcon) {
            iconView.setBackgroundResource(R.drawable.selected_icon_shape);
        } else {
            iconView.setBackgroundResource(0);
        }

        iconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedIcon = position;
                notifyDataSetChanged();
            }
        });

        String imagePath = getItem(position);
        Picasso.with(getContext())
                .load("file:///android_asset/" + NotebookAssetManager.ICONS_PATH + "/" + imagePath)
                .centerCrop()
                .resize(150, 150)
                .into(iconView);
        return convertView;
    }
}
