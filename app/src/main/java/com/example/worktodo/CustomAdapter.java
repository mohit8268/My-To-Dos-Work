package com.example.worktodo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public  class CustomAdapter extends RecyclerView.Adapter<viewHolder> {



    ListActivity listActivity;
    List<Model>modelList;
    Context context;

    public CustomAdapter(ListActivity listActivity, List<Model> modelList) {
        this.listActivity = listActivity;
        this.modelList = modelList;
       ;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView= LayoutInflater.from(parent.getContext())
        .inflate(R.layout.model_layout,parent,false);

        viewHolder viewHolder=new viewHolder(itemView);
        viewHolder.setOnClickListener(new viewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String todo = modelList.get(position).getTodo();
                String Due = modelList.get(position).getDue();
                String Notes = modelList.get(position).getNotes();
                String done = modelList.get(position).getDone();
                Toast.makeText(listActivity, todo+"\n"+done, Toast.LENGTH_SHORT).show();
                Toast.makeText(listActivity, Notes+"\n"+Due, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, final int position) {

                AlertDialog.Builder builder=new AlertDialog.Builder(listActivity);
                String[]options={"Update","Delete"};
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {

                            String id=modelList.get(position).getId();
                            String todo=modelList.get(position).getTodo();
                            String notes=modelList.get(position).getNotes();
                            String due=modelList.get(position).getDue();
                            String done=modelList.get(position).getDone();

                            Intent intent=new Intent(listActivity,MainActivity.class);
                            intent.putExtra("pId",id);
                            intent.putExtra("pTodo",todo);
                            intent.putExtra("pDue",due);
                            intent.putExtra("pNotes",notes);
                            intent.putExtra("pDone",done);
                            listActivity.startActivity(intent);


                        }
                        if (which == 1) {

                            listActivity.deleteData(position);

                        }
                    }
                }).create().show();



            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.Todo.setText(modelList.get(position).getTodo());
        holder.Due.setText(modelList.get(position).due);
        holder.Notes.setText(modelList.get(position).notes);
        holder.Done.setText(modelList.get(position).done);

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}