package com.shibkov.tasknotebook.app.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.daimajia.swipe.implments.SwipeItemRecyclerMangerImpl;
import com.shibkov.tasknotebook.app.R;
import com.shibkov.tasknotebook.app.models.TaskNote;

import java.util.List;

/**
 * Created by alexxxshib
 */
public class TaskNoteAdapter extends RecyclerView.Adapter<TaskNoteAdapter.ViewHolder> {

    private final List<TaskNote> mTaskNoteList;

    public TaskNoteAdapter(List<TaskNote> taskNotes) {
        mTaskNoteList = taskNotes;
    }

    @Override
    public TaskNoteAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_row_task, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(final TaskNoteAdapter.ViewHolder viewHolder, final int position) {
        TaskNote taskNote = mTaskNoteList.get(position);

        viewHolder.number.setText(String.format("%d.", position + 1));
        viewHolder.header.setText(taskNote.getHeader());
        viewHolder.body.setText(taskNote.getBody());
        viewHolder.taskNote = taskNote;
    }

    @Override
    public int getItemCount() {
        return mTaskNoteList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        TextView number;
        TextView header;
        TextView body;

        TaskNote taskNote;

        public ViewHolder(View itemView) {
            super(itemView);

            number = (TextView) itemView.findViewById(R.id.numberRow);
            header = (TextView) itemView.findViewById(R.id.headerRow);
            body   = (TextView) itemView.findViewById(R.id.descriptionRow);
        }
    }
}
