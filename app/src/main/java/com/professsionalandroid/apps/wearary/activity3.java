package com.professsionalandroid.apps.wearary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class activity3 extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    Button btnAdd;

    List<Activity3_memo> memoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        memoList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity3.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerAdapter = new RecyclerAdapter(memoList);
        recyclerView.setAdapter(recyclerAdapter);

        btnAdd = findViewById(R.id.addButton);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity3.this, Activity3_addmemo.class);
                startActivityForResult(intent,0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0){
            String strMain = data.getStringExtra("main");
            String strSub = data.getStringExtra("sub");
            Activity3_memo memo = new Activity3_memo(strMain, strSub);
            recyclerAdapter.addItem(memo);
            recyclerAdapter.notifyDataSetChanged();

        }
    }

    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>{
        private List<Activity3_memo> datalist;

        public RecyclerAdapter(List<Activity3_memo> listdata){
            this.datalist = listdata;
        }

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity3_list,parent,false);
            return new ItemViewHolder(view);
        }

        @Override
        public int getItemCount() {
            return datalist.size();
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            Activity3_memo memo = datalist.get(position);

            holder.maintext.setText(memo.getMaintext());
            holder.subtext.setText((memo.getSubtext()));
        }

        void addItem(Activity3_memo memo){
            datalist.add(memo);
        }
        void removeItem(int i){
            datalist.remove(i);
        }

        class ItemViewHolder extends RecyclerView.ViewHolder{
            private TextView maintext;
            private TextView subtext;

            public ItemViewHolder(@NonNull View itemView){
                super(itemView);

                maintext=itemView.findViewById(R.id.activity3_maintext);
                subtext=itemView.findViewById(R.id.activity3_subtext);
                }
        }
    }
}