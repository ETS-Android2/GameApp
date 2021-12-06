package com.example.gameapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gameapp.GameInfoActivity;
import com.example.gameapp.R;
import com.example.gameapp.model.GameInfo;

import org.parceler.Parcels;

import java.util.List;

public class GameInfoAdapter extends RecyclerView.Adapter<GameInfoAdapter.ViewHolder> {

    Context context;
    List<GameInfo> games;

    public GameInfoAdapter(Context context, List<GameInfo> games) {
        this.context = context;
        this.games = games;
    }

    @NonNull
    @Override
    public GameInfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GameInfoAdapter.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View game = inflater.inflate(R.layout.game, parent, false);

        viewHolder = new ViewHolder(game);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GameInfoAdapter.ViewHolder holder, int position) {
        GameInfo game = games.get(position);
        holder.bind(game);
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout container;
        TextView tvTitle;
        TextView tvGameRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvGameRating = itemView.findViewById(R.id.tvGameRating);
            container = itemView.findViewById(R.id.llGameOverview);
        }

        protected void setOnClickListener(Context context, GameInfo game) {
            container.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // navigate to a new activity on click
                            Intent i = new Intent(context, GameInfoActivity.class);
                            // pass data
                            i.putExtra("gameInfo", Parcels.wrap(game));

                            context.startActivity(i);
                        }
                    }
            );
        }

        public void bind(GameInfo game) {
            tvTitle.setText(game.getTitle());
            tvGameRating.setText(game.getRatingString());

            this.setOnClickListener(context, game);
        }
    }
}
