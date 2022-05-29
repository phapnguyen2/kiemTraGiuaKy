package com.example.appmy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText EditName,EditEmail,EditPhone,EditAddress;
    Button btnThem,btnSua,btnXoa,btnXem;
    DbHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditName = findViewById(R.id.EditName);
        EditEmail = findViewById(R.id.EditEmail);
        EditPhone = findViewById(R.id.EditPhone);
        EditAddress = findViewById(R.id.EditAddress);

        btnThem = findViewById(R.id.btnThem);
        btnSua = findViewById(R.id.btnSua);
        btnXoa = findViewById(R.id.btnXoa);
        btnXem = findViewById(R.id.btnXem);

        DB = new DbHelper(this);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = EditName.getText().toString();
                String Email = EditEmail.getText().toString();
                String Phone = EditPhone.getText().toString();
                String Address = EditAddress.getText().toString();

                Boolean checkInsertData = DB.insertuserdata(Name,Email,Phone,Address);
                if (checkInsertData == true){
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = EditName.getText().toString();
                String Email = EditEmail.getText().toString();
                String Phone = EditPhone.getText().toString();
                String Address = EditAddress.getText().toString();

                Boolean checkUpdateData = DB.updateuserdata(Name,Email,Phone,Address);
                if (checkUpdateData == true){
                    Toast.makeText(MainActivity.this, "Entry Update", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "New Entry Not Update", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = EditName.getText().toString();

                Boolean checkDeleteData = DB.deleteuserdata(Name);
                if (checkDeleteData == true){
                    Toast.makeText(MainActivity.this, "Entry Delete", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "New Entry Not Delete", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnXem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getData();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer stringBuffer = new StringBuffer();
                while (res.moveToNext()){
                    stringBuffer.append("Họ và Tên : " + res.getString(0)+"\n");
                    stringBuffer.append("Email : " + res.getString(1)+"\n");
                    stringBuffer.append("Số Điện Thoại : " + res.getString(2)+"\n");
                    stringBuffer.append("Địa Chỉ : " + res.getString(3)+"\n\n");

                }
                AlertDialog.Builder builder = new  AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(stringBuffer.toString());
                builder.show();
            }
        });

    }
}