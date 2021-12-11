package com.bkacad.nnt.contactc01k10t;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvMainContact;
    private FloatingActionButton fbMainAdd;
    private MyAdapter myAdapter;
    private List<Contact> dataContact;
    private ContactDialog contactDialog;


    // Alert Dialog xoá contact
    private AlertDialog alertDialog;

    private void initUI(){
        lvMainContact = findViewById(R.id.lvMainContact);
        fbMainAdd = findViewById(R.id.fbMainAdd);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        dataContact = new ArrayList<>();
        dataContact.add(new Contact(1,"Contact 1", "0918001001","https://www.nicepng.com/png/detail/804-8049853_med-boukrima-specialist-webmaster-php-e-commerce-web.png"));
        dataContact.add(new Contact(1,"Contact 2", "0918001002","https://www.nicepng.com/png/detail/804-8049853_med-boukrima-specialist-webmaster-php-e-commerce-web.png"));
        dataContact.add(new Contact(1,"Contact 3", "0918001003","https://www.nicepng.com/png/detail/804-8049853_med-boukrima-specialist-webmaster-php-e-commerce-web.png"));
        dataContact.add(new Contact(1,"Contact 4", "0918001004","https://www.nicepng.com/png/detail/804-8049853_med-boukrima-specialist-webmaster-php-e-commerce-web.png"));
        myAdapter = new MyAdapter(this, dataContact);

        lvMainContact.setAdapter(myAdapter);

        // Tạo dialog trước
        contactDialog = new ContactDialog(this) {
            @Override
            protected void callBack(Contact contact) {
                dataContact.add(contact);
                myAdapter.notifyDataSetChanged();
                // Thêm thành công -> notification (xử lý sau)
            }
        };


        // Click vào để xem chi tiêt
        lvMainContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("contact", dataContact.get(position));
                startActivity(intent);
            }
        });

        // Nhấn giữ lâu 1 contact => hiện aleert xoá

        lvMainContact.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                alertDialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Cảnh báo")
                        .setMessage("Bạn có muốn xoá liên hệ ?")
                        .setCancelable(false)
                        .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dataContact.remove(position);
                                myAdapter.notifyDataSetChanged();
                                Toast.makeText(MainActivity.this, "Đã xoá", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "Huỷ xoá", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();
                alertDialog.show();

                return false;
            }
        });




        fbMainAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị dialog, cho phép nhập dữ liệu
                contactDialog.show();
            }
        });

    }
}