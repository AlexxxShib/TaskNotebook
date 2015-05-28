package com.shibkov.tasknotebook.app.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.shibkov.tasknotebook.app.R;
import com.shibkov.tasknotebook.app.models.Category;

import java.util.List;

/**
 * Created by alexxxshib
 */
public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.ViewHolder> {

//    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final String DEFAULT_ICON = "ic_calendar";

    public interface MenuClickListener {
        void onItemClicked(Category category);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        int holderId;

        TextView header;
        TextView description;
        ImageView imageView;

        Category category;

        public ViewHolder(View itemView, int ViewType) {
            super(itemView);

            if (ViewType == TYPE_ITEM) {
                header = (TextView) itemView.findViewById(R.id.headerRow);
                description = (TextView) itemView.findViewById(R.id.descriptionRow);
                imageView = (ImageView) itemView.findViewById(R.id.rowIcon);
                holderId = 1;
            }
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mMenuListener.onItemClicked(category);
        }
    }

    private final Context mContext;
    private final List<Category> mCategories;
    private final MenuClickListener mMenuListener;

    public MainMenuAdapter(Context context, List<Category> categories, MenuClickListener listener) {
        mContext = context;
        mCategories = categories;
        mMenuListener = listener;
    }

    @Override
    public MainMenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_menu, parent, false);
            return new ViewHolder(v, viewType);
        }
        return null;

    }

    @Override
    public void onBindViewHolder(MainMenuAdapter.ViewHolder holder, int position) {

        if (holder.holderId == 1) {
            Category category = mCategories.get(position);
            String iconName = category.getIconName();

            if (iconName == null) iconName = DEFAULT_ICON;
            int imageResId = mContext.getResources().getIdentifier(iconName, "drawable", mContext.getPackageName());

            holder.header.setText(category.getValue());
            holder.imageView.setImageResource(imageResId);
            holder.description.setText(category.getDescription());
            holder.category = category;
        }
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }


    @Override
    public int getItemViewType(int position) {
//        if (isPositionHeader(position))
//            return TYPE_HEADER;
//
        return TYPE_ITEM;
    }

//    private boolean isPositionHeader(int position) {
//        return position == 0;
//    }

}
