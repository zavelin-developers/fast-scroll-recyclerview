package com.zavierdev.fastscrollrecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.l4digital.fastscroll.FastScroller;

import java.util.ArrayList;

public class RvGenreAdapter extends RecyclerView.Adapter<RvGenreAdapter.ViewHolder> implements FastScroller.SectionIndexer {
    private ArrayList<GenresModel> listGenre = new ArrayList<>();

    public void setData(ArrayList listGenre) {
        this.listGenre = listGenre;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.genre_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GenresModel model = listGenre.get(position);
        holder.tvGenre.setText(model.getTitle());
    }

    @Override
    public int getItemCount() {
        return listGenre.size();
    }

    @Override
    public CharSequence getSectionText(int position) {
        GenresModel model = listGenre.get(position);
        return model.getTitle().substring(0, 1).toUpperCase();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvGenre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGenre = itemView.findViewById(R.id.tv_genre);
        }
    }
}
