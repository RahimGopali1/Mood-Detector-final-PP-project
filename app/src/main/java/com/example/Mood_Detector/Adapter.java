package com.example.Mood_Detector;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ItemViewHolder>{

    private Context context;
    private ArrayList <Items> itemsArrayList;

    public Adapter(Context context) {
        this.context = context;
    }

    //making function to ste data into adapter
    public void setDataToAdapter(ArrayList<Items> list)
    {
        itemsArrayList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Items items = itemsArrayList.get(position);
        holder.tvTitle.setText(items.getTitle());
        holder.tvContent.setText(items.getContent());
        holder.imageView.setImageResource(items.getImageView());
        holder.foldingCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.foldingCell.toggle(false);

            }
        });
    }

    @Override
    public int getItemCount() {
        return itemsArrayList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder
    {
        private FoldingCell foldingCell;
        private TextView tvTitle;
        private TextView tvContent;
        private ImageView imageView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            foldingCell = itemView.findViewById(R.id.folding_cell);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvContent = itemView.findViewById(R.id.tv_content);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
