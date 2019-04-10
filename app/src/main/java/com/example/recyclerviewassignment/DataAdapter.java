package com.example.recyclerviewassignment;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private Context context;
    private List<Data> dataList;
    private IItemClick iItemClick;

    public DataAdapter(Context context, List<Data> dataList) {
        this.context = context;
        this.dataList = dataList;
        this.iItemClick = (IItemClick) context;
    }

    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.ViewHolder viewHolder, int i) {
        final Data data = dataList.get(i);
        viewHolder.textView.setText(data.getName());
        viewHolder.checkBox.setChecked(data.isSelected());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iItemClick.onItemClick(data.getName());
            }
        });

        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(data.isSelected()) {
                    iItemClick.onItemUncheck(data.getName());
                    viewHolder.checkBox.setChecked(false);
                    dataList.get(i).setSelected(false);
                } else {
                    iItemClick.onItemCheck(data.getName());
                    viewHolder.checkBox.setChecked(true);
                    dataList.get(i).setSelected(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvEmpNames);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }
}
