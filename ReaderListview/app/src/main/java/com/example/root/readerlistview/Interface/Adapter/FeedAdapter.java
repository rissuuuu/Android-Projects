package com.example.root.readerlistview.Interface.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.root.readerlistview.Interface.ItemClickListener;
import com.example.root.readerlistview.R;
import com.example.root.readerlistview.model.RssObject;

class FeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{

    public TextView txttitle,txtpubdate,txtcontent;
    private ItemClickListener itemClickListener;

       public FeedViewHolder(View itemView) {

        super(itemView);
        txttitle=(TextView )itemView.findViewById(R.id.texttitle);
        txtpubdate=(TextView)itemView.findViewById(R.id.textpubdate);
        txtcontent=(TextView)itemView.findViewById(R.id.textContent);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
    itemClickListener.onClick(v,getAdapterPosition(),false);
    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),true);
        return true;
    }
}
public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder>{
    public RssObject rssObject;
    public Context mContext;
    public LayoutInflater inflater;

    public FeedAdapter(RssObject rssObject, Context mContext, LayoutInflater inflater) {
        this.rssObject = rssObject;
        this.mContext = mContext;
       inflater=LayoutInflater.from(mContext);
    }

    public FeedAdapter(RssObject rssObject, Context baseContext) {
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView=inflater.inflate(R.layout.row,parent,false);
                return new FeedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FeedViewHolder holder, int position) {
        holder.txttitle.setText(rssObject.getItems().get(position).getTitle());
        holder.txtpubdate.setText(rssObject.getItems().get(position).getPubDate());
        holder.txtcontent.setText(rssObject.getItems().get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
