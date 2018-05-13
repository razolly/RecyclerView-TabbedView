package com.bignerdranch.android.tabbedviewtest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Tab2Adapter extends RecyclerView.Adapter<Tab2Adapter.ViewHolder> {

    private List<Article> mArticleDataSet;
    private Context context;

    public static final String EXTRA_MESSAGE = "com.example.TabbedViewTest.MESSAGE";
    private static final String TAG = "Tab2Adapter";

//    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTitleView;
        public TextView mShortDescView;

        public ViewHolder(View v) {
            super(v);
            mTitleView = v.findViewById(R.id.t2_title);
            mShortDescView = v.findViewById(R.id.t2_description);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
//    public Tab2Adapter(List<Article> mArticleDataSet, Context context) {
    public Tab2Adapter(List<Article> mArticleDataSet, Context context) {
        this.mArticleDataSet = mArticleDataSet;
        this.context = context;
    }

    // Create new views holders (invoked by the layout manager)
    @Override
    public Tab2Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tab2_view_recycler, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.mTitleView.setText(mArticleDataSet.get(position).getTitle());
        holder.mShortDescView.setText(mArticleDataSet.get(position).getShortDescription());

        // Open new activity to show full description
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, FullDescription.class);
                    String fullDesc = mArticleDataSet.get(holder.getAdapterPosition()).getFullDescription();
                    i.putExtra(EXTRA_MESSAGE, fullDesc);
                    context.startActivity(i);
                    Log.d(TAG, "onClick: clicked");
                }
            });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mArticleDataSet.size();
    }

}
