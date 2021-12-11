package com.bkacad.nnt.contactc01k10t;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

public abstract class ContactDialog extends Dialog {

    public ContactDialog(@NonNull Context context) {
        super(context);
    }

    private Button btnSave, btnCancel;
    private EditText edtName, edtPhone, edtAddress;

    protected abstract void callBack(Contact contact);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_contact);
        setCancelable(false);
        edtAddress = findViewById(R.id.edtDialogAddress);
        edtName = findViewById(R.id.edtDialogName);
        edtPhone = findViewById(R.id.edtDialogPhone);

        btnCancel = findViewById(R.id.btnDialogCancel);
        btnSave = findViewById(R.id.btnDialogSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                if(name.isEmpty()){
                    edtName.setError("Hãy nhập tên");
                    return;
                }
                String phone = edtPhone.getText().toString();
                if(phone.isEmpty()){
                    edtPhone.setError("Hãy nhấp số điện thoại");
                    return;
                }
                String address = edtAddress.getText().toString();

                Contact item = new Contact();
                item.setName(name);
                item.setPhone(phone);
                item.setAddress(address);

                callBack(item);
                dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Thoát",Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
    }
}
