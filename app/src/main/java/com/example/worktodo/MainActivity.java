package com.example.worktodo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    EditText Todos;
    EditText Dues;
    EditText Notes;
    EditText Done;
    Button Save;
    Button list_btn;
    ProgressDialog pd;
    FirebaseFirestore db;
    String pId,pTodo,pDue,pDone,pNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar=getSupportActionBar();
        Todos=findViewById(R.id.todo);
        Dues=findViewById(R.id.due);
        Notes=findViewById(R.id.notes);
        Done=findViewById(R.id.done);
        Save=findViewById(R.id.save);
        list_btn=findViewById(R.id.list);

        final Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            actionBar.setTitle("update Data");
            Save.setText("update ");
            pId =bundle.getString("pId");
            pTodo =bundle.getString("pTodo");
            pDone =bundle.getString("pDone");
            pDue =bundle.getString("pDue");
            pNotes=bundle.getString("pNotes");

            Todos.setText(pTodo);
            Notes.setText(pNotes);
            Done.setText(pDone);
            Dues.setText(pDue);

        }
        else{
            actionBar.setTitle("Add Data");
            Save.setText("Save");




        }
        pd=new ProgressDialog(this);
        db=FirebaseFirestore.getInstance();
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle1 =getIntent().getExtras();
                if(bundle !=null){
                    String id=pId;
                    String todo=Todos.getText().toString().trim();
                    String due=Dues.getText().toString().trim();
                    String notes=Notes.getText().toString().trim();
                    String done=Done.getText().toString().trim();
                    updateData(id,todo,due,done,notes);

                }
                else{
                    String todo=Todos.getText().toString().trim();
                    String due=Dues.getText().toString().trim();
                    String note=Notes.getText().toString().trim();
                    String done=Done.getText().toString().trim();

                    uploadData(todo,due,note,done);

                }

            }
        });
        list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ListActivity.class));
                finish();
            }
        });

    }

    private void updateData(String id, String todo, String due, String done, String notes) {
        pd.setTitle("Updating data to Firestore");
        pd.show();


        Map<String,Object> doc=new HashMap<>();
        doc.put("id",id);
        doc.put("todo",todo);
        doc.put("due",due);
        doc.put("notes",notes);
        doc.put("done",done);
        db.collection("Documents").document(id).set(doc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        pd.dismiss();
                        Toast.makeText(MainActivity.this, "Added Task.....", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(MainActivity.this,e.getMessage() , Toast.LENGTH_SHORT).show();

                    }
                });




        db.collection("Document").document(id)
                .update("todo",todo,"done",done,"notes",notes,"due",due)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        pd.dismiss();
                        Toast.makeText(MainActivity.this, "update", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });


    }

    private void uploadData(String todo, String due, String notes, String done) {
        pd.setTitle("Adding data .....");
        pd.show();
        String id= UUID.randomUUID().toString();
        Map<String,Object> doc=new HashMap<>();
        doc.put("id",id);
        doc.put("todo",todo);
        doc.put("due",due);
        doc.put("notes",notes);
        doc.put("done",done);
        db.collection("Documents").document(id).set(doc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        pd.dismiss();
                        Toast.makeText(MainActivity.this, "Added Task.....", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(MainActivity.this,e.getMessage() , Toast.LENGTH_SHORT).show();

                    }
                });

    }
}
