package com.example.contact;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class Myadapter extends RecyclerView.Adapter<Myadapter.myclas> {

    MainActivity mainActivity;
    ArrayList<UserContact> contactlist;

    public Myadapter(MainActivity mainActivity, ArrayList<UserContact> contactlist) {
        this.mainActivity = mainActivity;
        this.contactlist = contactlist;
    }

    @NonNull
    @Override
    public Myadapter.myclas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mainActivity).inflate(R.layout.contact, parent, false);

        return new myclas(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myadapter.myclas holder, @SuppressLint("RecyclerView") int position) {

        UserContact bb = contactlist.get(position);

        holder.t1.setText(bb.getNam());
        holder.t2.setText(bb.getNum());

//        String nm = bb.getNam().substring(0, 1);
        holder.img.setText(bb.getNam().toUpperCase());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                PopupMenu popmenu = new PopupMenu(mainActivity, view);
                popmenu.getMenu().add(Menu.NONE, 0, 0, "Edit");
                popmenu.getMenu().add(Menu.NONE, 1, 1, "Delete");
                popmenu.show();
                popmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int i = menuItem.getItemId();

                        if (i == 0) {
                            Intent intent = new Intent(mainActivity,UpdateActivity.class);
                            intent.putExtra("nam",bb.getNam());
                            intent.putExtra("num",bb.getNum());
                            intent.putExtra("id",bb.getId());
                            mainActivity.startActivity(intent);
                        }
                        if (i == 1) {
                            Dbhelper dbhelper = new Dbhelper(mainActivity);
                            dbhelper.deletedata(bb.getId());
                            contactlist.remove(position);
                            notifyDataSetChanged();
                            Toast.makeText(mainActivity, "Contact Deleted", Toast.LENGTH_SHORT).show();
//                           view.getContext().startActivity(new Intent(mainActivity, MainActivity.class));
                        }
                        return true;
                    }
                });
                return true;
            }
        });
    }


    @Override
    public int getItemCount() {
        return contactlist.size();
    }

    public class myclas extends RecyclerView.ViewHolder {

        TextView t1, t2, img;

        public myclas(@NonNull View itemView) {
            super(itemView);

            t1 = itemView.findViewById(R.id.t1);
            t2 = itemView.findViewById(R.id.t2);
            img = itemView.findViewById(R.id.img);
        }
    }
}
