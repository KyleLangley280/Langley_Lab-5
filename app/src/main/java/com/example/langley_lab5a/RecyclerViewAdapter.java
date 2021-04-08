package com.example.langley_lab5a;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private List<Memo> data;
    public RecyclerViewAdapter(List<Memo> data){
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.memo_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setMemo(data.get(position));
        holder.bindData();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private Memo memo;
        //private TextView nameLabel;
        //private TextView addressLabel;
        private TextView memoLabel, idLabel;

        public ViewHolder(View itemView){
            super(itemView);
        }

        public Memo getMemo(){
            return memo;
        }

        public void setMemo(Memo memo){
            this.memo = memo;
        }

        public void bindData(){
            /*if (nameLabel == null){
                nameLabel = (TextView) itemView.findViewById(R.id.contactNameLabel);
            }
            if (addressLabel == null){
                addressLabel = (TextView) itemView.findViewById(R.id.contactAddressLabel);
            }
            nameLabel.setText(contact.getName());
            addressLabel.setText(contact.getAddress()); */
            if (idLabel == null) {
                idLabel = (TextView) itemView.findViewById(R.id.idLabel);
            }
            if (memoLabel == null) {
                memoLabel = (TextView) itemView.findViewById(R.id.memoLabel);
            }
            idLabel.setText(String.valueOf(memo.getId()));
            memoLabel.setText(memo.getMemo());
        }
    }
}
