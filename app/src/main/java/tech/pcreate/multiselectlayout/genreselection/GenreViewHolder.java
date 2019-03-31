package tech.pcreate.multiselectlayout.genreselection;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import tech.pcreate.multiselectlayout.R;

public class GenreViewHolder extends RecyclerView.ViewHolder {

    View view;
    TextView eachGenre;
    RelativeLayout relativeLayout;

    public GenreViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;

        eachGenre = view.findViewById(R.id.eachgenre);
        relativeLayout = view.findViewById(R.id.colorchangebackground);
    }

}
