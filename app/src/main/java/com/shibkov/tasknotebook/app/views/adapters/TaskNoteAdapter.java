package com.shibkov.tasknotebook.app.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.shibkov.tasknotebook.app.R;
import com.shibkov.tasknotebook.app.models.TaskNote;

import java.util.List;

/**
 * Created by alexxxshib
 */
public class TaskNoteAdapter extends RecyclerView.Adapter<TaskNoteAdapter.ViewHolder>{

    public interface TaskListListener {
        void onItemClicked(TaskNote taskNote);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView number;
        TextView header;
        TextView body;

        TaskNote taskNote;

        public ViewHolder(View itemView) {
            super(itemView);

            number = (TextView) itemView.findViewById(R.id.numberRow);
            header = (TextView) itemView.findViewById(R.id.headerRow);
            body   = (TextView) itemView.findViewById(R.id.descriptionRow);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mTaskListListener.onItemClicked(taskNote);
                }
            });
        }
    }

    private final List<TaskNote> mTaskNoteList;
    private final TaskListListener mTaskListListener;

    public TaskNoteAdapter(List<TaskNote> taskNotes, TaskListListener listener) {
        mTaskNoteList = taskNotes;
        mTaskListListener = listener;
    }

    public void addItem(TaskNote taskNote) {
        mTaskNoteList.add(taskNote);
        notifyDataSetChanged();
    }

    @Override
    public TaskNoteAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_task, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(TaskNoteAdapter.ViewHolder viewHolder, int i) {

        TaskNote taskNote = mTaskNoteList.get(i);

        viewHolder.number.setText(String.format("%d.", i + 1));
        viewHolder.header.setText(taskNote.getHeader());
        viewHolder.body.setText(taskNote.getBody());
        viewHolder.taskNote = taskNote;
    }

    @Override
    public int getItemCount() {
        return mTaskNoteList.size();
    }
}
