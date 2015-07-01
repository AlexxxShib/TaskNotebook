package com.shibkov.tasknotebook.app.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.shibkov.tasknotebook.app.R;
import com.shibkov.tasknotebook.app.models.TaskNote;

import java.util.List;

/**
 * Created by Alexey Shibkov on 30.06.2015.
 */
public class TaskNoteListAdapter extends ArrayAdapter<TaskNote> {

    private int expandablePos = -1;

    public TaskNoteListAdapter(Context context, List<TaskNote> objects) {
        super(context, R.layout.item_row_task, objects);
    }

    public void action(int pos) {
        expandablePos = pos;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_row_task, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.showByPos(position);
        return convertView;
    }

    private class ViewHolder {
        TextView number;
        TextView header;
        TextView body;

        View buttonsRoot;
        View editBtn;
        View removeBtn;

        public ViewHolder(View itemView) {
            number = (TextView) itemView.findViewById(R.id.numberRow);
            header = (TextView) itemView.findViewById(R.id.headerRow);
            body   = (TextView) itemView.findViewById(R.id.descriptionRow);

            buttonsRoot = itemView.findViewById(R.id.root_buttons);
            editBtn     = itemView.findViewById(R.id.edit);
            removeBtn   = itemView.findViewById(R.id.remove);
        }

        public void showByPos(int position) {
            final TaskNote note = getItem(position);
            number.setText(String.format("%d.", position + 1));
            header.setText(note.getHeader());

            String bodyStr = note.getBody();
            if (bodyStr == null || bodyStr.trim().isEmpty()) {
                body.setVisibility(View.GONE);
            } else {
                body.setText(bodyStr);
            }

            boolean isExpandable = position == expandablePos;

            body.setSingleLine(!isExpandable);
            buttonsRoot.setVisibility(isExpandable ? View.VISIBLE : View.GONE);
        }
    }
}
