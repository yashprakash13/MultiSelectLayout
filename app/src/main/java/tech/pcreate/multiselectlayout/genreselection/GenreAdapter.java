package tech.pcreate.multiselectlayout.genreselection;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;
import tech.pcreate.multiselectlayout.R;

public class GenreAdapter extends RecyclerView.Adapter<GenreViewHolder> {

    private List<Genre> genreList;
    private Context context;

    private int lastSelectedPosition = 0;
    private int countSelected = 0;

    public GenreAdapter(Context context, List<Genre> genrelist) {

        this.genreList = genrelist;
        this.context = context;
    }

    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.genre_single_layout, null);
        return new GenreViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder holder, int i) {

        Genre genre = genreList.get(i);
        holder.eachGenre.setText(genre.getName());

        if (genre.isSelected()) {
            holder.relativeLayout.setBackgroundResource(android.R.color.white);
            holder.eachGenre.setTextColor(context.getResources().getColor(R.color.colorAccent));
        } else {
            holder.relativeLayout.setBackgroundResource(R.color.colorAccent);
            holder.eachGenre.setTextColor(context.getResources().getColor(android.R.color.white));
        }

        holder.relativeLayout.setOnClickListener(view -> {

            lastSelectedPosition = holder.getAdapterPosition();
            //Log.e("Pos clicked = ", String.valueOf(lastSelectedPosition));
            if (!genreList.get(lastSelectedPosition).isSelected()) {
                if (countSelected < 4) {
                    ++countSelected;
                    genreList.get(lastSelectedPosition).setSelected(true);
                    holder.relativeLayout.setBackgroundResource(android.R.color.white);
                    holder.eachGenre.setTextColor(context.getResources().getColor(R.color.colorAccent));
                    //Log.e("Count = ", String.valueOf(countSelected));
                }
                if (countSelected >= 4) {
                    ++countSelected;
                    genreList.get(lastSelectedPosition).setSelected(true);
                    holder.relativeLayout.setBackgroundResource(android.R.color.white);
                    holder.eachGenre.setTextColor(context.getResources().getColor(R.color.colorAccent));

                    sendBroadcast(true);
                }
            } else if (genreList.get(lastSelectedPosition).isSelected()) {
                //Log.e("Pos  = ", String.valueOf(lastSelectedPosition));
                if (countSelected > 0) --countSelected;
                genreList.get(lastSelectedPosition).setSelected(false);
                holder.relativeLayout.setBackgroundResource(R.color.colorAccent);
                holder.eachGenre.setTextColor(context.getResources().getColor(android.R.color.white));
                if(countSelected >= 4) sendBroadcast(true);
                else sendBroadcast(false);

            }


        });


    }

    private void sendBroadcast(boolean b) {
        if (b) {
            Intent intent = new Intent("genresMessage");
            intent.putExtra("getGenres", "true");
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        } else {
            Intent intent = new Intent("genresMessage");
            intent.putExtra("getGenres", "false");
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }


    }

    @Override
    public int getItemCount() {
        return this.genreList.size();
    }

}