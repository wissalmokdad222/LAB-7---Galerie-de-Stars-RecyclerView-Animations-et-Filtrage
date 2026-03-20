package com.example.starsgallery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.starsgallery.R;
import com.example.starsgallery.beans.Star;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class StarAdapter extends RecyclerView.Adapter<StarAdapter.ViewHolder> implements Filterable {

    private List<Star> list;
    private List<Star> listFilter;
    private Context context;

    public StarAdapter(Context context, List<Star> list) {
        this.context = context;
        this.list = list;
        this.listFilter = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.star_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Star s = listFilter.get(position);

        holder.name.setText(s.getName());
        holder.rating.setRating(s.getStar());
        holder.id.setText(String.valueOf(s.getId()));

        Glide.with(context)
                .load(s.getImg())
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return listFilter.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    listFilter = list;
                } else {
                    List<Star> filteredList = new ArrayList<>();
                    for (Star row : list) {
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    listFilter = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listFilter = (ArrayList<Star>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView id, name;
        RatingBar rating;
        CircleImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.ids);
            name = itemView.findViewById(R.id.name);
            rating = itemView.findViewById(R.id.stars);
            img = itemView.findViewById(R.id.img);
        }
    }
}
