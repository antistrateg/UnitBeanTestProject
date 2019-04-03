package com.unitbean.UnitBean.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.unitbean.UnitBean.R;
import static com.unitbean.UnitBean.Utils.Auxiliary.getNameFromEmail;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ListViewHolder>{

    private static int viewHolderCount;
    private int numberItem;

    private String[] nameArray;
    private String[] commentArray;

    private Context context;

    public CommentAdapter(int numberOfItems, String[] nameArray, String[] commentArray, Context context){
        numberItem = numberOfItems;
        viewHolderCount = 0;
        this.nameArray = nameArray;
        this.commentArray = commentArray;
        this.context = context;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.item_comment;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        ListViewHolder viewHolder = new ListViewHolder(view);
        //viewHolder.tvTitle.setText("Hello");
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return numberItem;
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvComment;

        public ListViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvComment = itemView.findViewById(R.id.tvComment);

        }

        void bind(int position){
            tvName.setText(getNameFromEmail(nameArray[position]));
            tvComment.setText(commentArray[position]);
        }
    }


}
