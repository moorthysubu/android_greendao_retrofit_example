package droidtute.greendao.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import droidtute.greendao.R;
import droidtute.greendao.database.NewsItem;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MovieViewHolder> {

    private List<NewsItem> movies;


    public NewsAdapter(List<NewsItem> movies) {
        this.movies = movies;
    }

    @Override
    public NewsAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        holder.title.setText(movies.get(position).getTitle());
        holder.authorName.setText(movies.get(position).getAuthor());
        holder.description.setText(movies.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView authorName;
        TextView description;


        public MovieViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
            authorName = (TextView) v.findViewById(R.id.author);
            description = (TextView) v.findViewById(R.id.description);
        }
    }
}