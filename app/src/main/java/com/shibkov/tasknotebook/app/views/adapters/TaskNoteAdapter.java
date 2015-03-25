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
public class TaskNoteAdapter extends RecyclerSwipeAdapter<TaskNoteAdapter.ViewHolder> {

    public interface TaskListListener {
        void onItemClicked(TaskNote taskNote);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        SwipeLayout swipeLayout;
        TextView number;
        TextView header;
        TextView body;
        Button buttonDelete;

        TaskNote taskNote;

        public ViewHolder(View itemView) {
            super(itemView);

            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);
            number = (TextView) itemView.findViewById(R.id.numberRow);
            header = (TextView) itemView.findViewById(R.id.headerRow);
            body   = (TextView) itemView.findViewById(R.id.descriptionRow);
            buttonDelete = (Button) itemView.findViewById(R.id.delete);

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
    protected SwipeItemRecyclerMangerImpl mItemManger = new SwipeItemRecyclerMangerImpl(this);

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
    public void onBindViewHolder(final TaskNoteAdapter.ViewHolder viewHolder, final int position) {
        TaskNote taskNote = mTaskNoteList.get(position);

        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        viewHolder.swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {
//                YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.trash));
            }
        });
        viewHolder.swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
            @Override
            public void onDoubleClick(SwipeLayout layout, boolean surface) {
//                Toast.makeText(, "DoubleClick", Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemManger.removeShownLayouts(viewHolder.swipeLayout);
                mTaskNoteList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mTaskNoteList.size());
                mItemManger.closeAllItems();
                Toast.makeText(view.getContext(), "Deleted " + viewHolder.number.getText().toString() + "!", Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.number.setText(String.format("%d.", position + 1));
        viewHolder.header.setText(taskNote.getHeader());
        viewHolder.body.setText(taskNote.getBody());
        viewHolder.taskNote = taskNote;
        mItemManger.bindView(viewHolder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return mTaskNoteList.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int i) {
        return R.id.swipe;
    }
}
