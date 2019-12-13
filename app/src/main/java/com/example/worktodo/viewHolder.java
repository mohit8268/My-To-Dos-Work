package com.example.worktodo;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public  class viewHolder extends RecyclerView.ViewHolder{


        TextView Todo,Due,Notes,Done;
        View view;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onItemClick(v,getAdapterPosition());

                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mClickListener.onItemLongClick(v,getAdapterPosition());
                    return true;
                }
            });

            Todo=itemView.findViewById(R.id.rtodo);
            Due=itemView.findViewById(R.id.rdue);
            Notes=itemView.findViewById(R.id.rNotes);
            Done=itemView.findViewById(R.id.rdone);

        }
         private  viewHolder.ClickListener mClickListener;

        public interface ClickListener{
            void onItemClick(View view,int position);
            void onItemLongClick(View view,int position);


        }

        public  void setOnClickListener(viewHolder.ClickListener clickListener){
            mClickListener=clickListener;

    }

}

