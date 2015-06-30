package com.shibkov.tasknotebook.app.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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

    public TaskNoteListAdapter(Context context, List<TaskNote> objects) {
        super(context, R.layout.item_row_task, objects);
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
        holder.setModel(position);
        return convertView;
    }

    private class ViewHolder {
        TextView number;
        TextView header;
        TextView body;

        public ViewHolder(View itemView) {
            number = (TextView) itemView.findViewById(R.id.numberRow);
            header = (TextView) itemView.findViewById(R.id.headerRow);
            body   = (TextView) itemView.findViewById(R.id.descriptionRow);
        }

        public void setModel(int position) {
            TaskNote note = getItem(position);
            number.setText(String.format("%d.", position + 1));
            header.setText(note.getHeader());

            String bodyStr = note.getBody();
            if (bodyStr == null || bodyStr.trim().isEmpty()) {
                body.setVisibility(View.GONE);
            } else {
                body.setText(bodyStr);
            }
        }
    }
}
