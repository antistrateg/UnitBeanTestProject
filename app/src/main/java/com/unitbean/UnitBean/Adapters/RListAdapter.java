package com.unitbean.UnitBean.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.unitbean.UnitBean.R;
import static com.unitbean.UnitBean.Utils.Auxiliary.firstLetterToUpperCase;
import static com.unitbean.UnitBean.Utils.Auxiliary.stringAdjust;
import static com.unitbean.UnitBean.Utils.Auxiliary.stringCut;

public class RListAdapter extends RecyclerView.Adapter<RListAdapter.ListViewHolder>{

    private static int viewHolderCount;
    private int numberItem;

    private static recyclerViewItemClickeble rvItemClickeble;

    private String[] postIdArray;
    private String[] titleArray;
    private String[] contentArray;

    private Context context;

    public RListAdapter(int numberOfItems, String[] postIdArray, String[] titleArray, String[] contentArray, Context context){
        numberItem = numberOfItems;
        viewHolderCount = 0;
        this.postIdArray = postIdArray;
        this.titleArray = titleArray;
        this.contentArray = contentArray;
        this.context = context;
    }

    public interface recyclerViewItemClickeble {
        public void onClickRecyclerViewItem(String postId, String title, String content);
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.item_rlist;
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

        TextView tvTitle;
        TextView tvDate;
        TextView tvContent;

        public ListViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvContent = itemView.findViewById(R.id.tvContent);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int positionIndex = getAdapterPosition();
                    rvItemClickeble = (recyclerViewItemClickeble)  context;
                    String postId = postIdArray[positionIndex];
                    String title = titleArray[positionIndex];
                    String content = contentArray[positionIndex];
                    rvItemClickeble.onClickRecyclerViewItem(postId, title, content);
                }
            });

        }

        void bind(int position){
            tvTitle.setText(firstLetterToUpperCase(titleArray[position]));
            //tvDate.setText("");
            String content = stringAdjust(contentArray[position]);
            content = stringCut(content,150);
            tvContent.setText(firstLetterToUpperCase(content));
        }
    }


}
