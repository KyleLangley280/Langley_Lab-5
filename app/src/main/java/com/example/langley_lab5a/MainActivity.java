package com.example.langley_lab5a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView output, inputText, deleteText;
    private Contact contact;
    DatabaseHandler db = new DatabaseHandler(this,null,null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = (TextView) findViewById(R.id.output);
        getAllContacts();
    }
    public void getAllContacts(){
        String contacts = db.getAllContacts();
        output.setText(contacts);
    }

    public void addMemo(View v){
        inputText = (TextView) findViewById(R.id.inputText);
        String input = inputText.getText().toString();
        if(!input.isEmpty()){
            contact = new Contact(input);
            db.addContact(contact);
            getAllContacts();
        }
    }
    public void deleteMemo(View v){
        deleteText = (TextView) findViewById(R.id.deleteText);
        String deletion = deleteText.getText().toString();
        if (!deletion.isEmpty() && isNumeric(deletion)) {
            db.deleteMemo(Integer.parseInt(deletion));
        }
        getAllContacts();
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