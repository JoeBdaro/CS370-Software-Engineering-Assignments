package edu.qc.seclass.glm;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MyViewHolder extends RecyclerView.ViewHolder{

    public View myview;


    public MyViewHolder(View itemView) {
        super(itemView);
        myview=itemView;
    }

    // if decided to implement user press instead of FAB.
//    private MyViewHolder.ClickListener mClickListener;
//
//    // interface for callbacks
//    public interface ClickListener{
//        void onItemClick(View view, int position);
//        void onItemLongClick(View view, int position);
//    }
//
//    public void setOnClickListener(MyViewHolder.ClickListener clickListener){
//        mClickListener = clickListener;
//    }
//
//    public MyViewHolder(View itemView) {
//        super(itemView);
//        myview=itemView;
//
//        //on item click
//        itemView.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//                mClickListener.onItemClick(view, getAdapterPosition());
//            }
//        });
//    }



    public void setType(String type){
        TextView mType=myview.findViewById(R.id.type);
        mType.setText(type);
    }

    public void setNote(String note){
        TextView mNote=myview.findViewById(R.id.note);
        mNote.setText(note);
    }

    public void setDate(String date){
        TextView mDate=myview.findViewById(R.id.date);
        mDate.setText(date);
    }

    public void setSubtype(String subtype){

        TextView mSubtype=myview.findViewById(R.id.subtype);
        mSubtype.setText(subtype);

    }

//    public MyViewHolder OnCreateViewHolder(ViewGroup parent, int viewType){
//        MyViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
//    }


}