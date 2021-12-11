package com.bkacad.nnt.contactc01k10t;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private List<Contact> data;
    private PopupMenu popupMenu;

    public MyAdapter(Context context, List<Contact> data) {
        this.context = context;
        this.data = data;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false);
        }
        // Khai báo view -> gán các giá trị tương ứng
        TextView tvName = convertView.findViewById(R.id.tvItemName);
        TextView tvPhone = convertView.findViewById(R.id.tvItemPhone);
        ImageView imgAvatar = convertView.findViewById(R.id.imgItemAvatar);

        Contact currentContact = data.get(position);
        tvName.setText(currentContact.getName());
        tvPhone.setText(currentContact.getPhone());

        if (currentContact.getUrlAvatar() != null && currentContact.getUrlAvatar().isEmpty()) {
            Glide.with(convertView).load(currentContact.getUrlAvatar()).into(imgAvatar);
        }

        ImageView imgMenu = convertView.findViewById(R.id.imgItemMenu);

        // Tạo popup menu

        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Vấn đề làm thế nào để hiển thị cái popup lên
//                Toast.makeText(context, "Click "+position, Toast.LENGTH_SHORT).show();

                popupMenu = new PopupMenu(context, imgAvatar);
                popupMenu.getMenuInflater().inflate(R.menu.item_menu, popupMenu.getMenu());

                // Xử lý khi click vào từng item menu view
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item_popup_details:
                                Intent intent = new Intent(context, DetailActivity.class);
                                intent.putExtra("contact", data.get(position));
                                context.startActivity(intent);
                                break;
                            case R.id.item_popup_edit:
                                // Tạo 1 custom dialg để cập nhật

                                break;
                            case R.id.item_popup_delete:
                                data.remove(position);
                                notifyDataSetChanged();
                                break;
                        }
                        return false;
                    }
                });

                // SHow menu lên
                popupMenu.show();
            }
        });

        return convertView;
    }
}
