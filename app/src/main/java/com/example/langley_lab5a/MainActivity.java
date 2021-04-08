package com.example.langley_lab5a;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView inputText, deleteText;
    private Memo memo;
    DatabaseHandler db;
    private RecyclerView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = (RecyclerView) findViewById(R.id.output);
        db = new DatabaseHandler(this,null,null,1);
        updateRecyclerView();
    }
    public void getAllMemos(){
        String allMemos = db.getAllMemos();
        updateRecyclerView();
    }

    public void addMemo(View v){
        inputText = (TextView) findViewById(R.id.inputText);
        String input = inputText.getText().toString();
        if(!input.isEmpty()){
            memo = new Memo(input);
            db.addMemo(memo);
            //getAllMemos();
            updateRecyclerView();
        }
    }
    public void deleteMemo(View v){
        deleteText = (TextView) findViewById(R.id.deleteText);
        String deletion = deleteText.getText().toString();
        if (!deletion.isEmpty() && isNumeric(deletion)) {
            db.deleteMemo(Integer.parseInt(deletion));
        }
        getAllMemos();
    }

    private void updateRecyclerView(){
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(db.getAllMemosAsList());
        output.setHasFixedSize(true);
        output.setLayoutManager(new LinearLayoutManager(this));
        output.setAdapter(adapter);
    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}