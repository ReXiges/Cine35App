package cine35app.esteb.cine35app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.List;

/**
 * Created by AndroidBash on 09/05/2016.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private Context context;
    private List<Pelicula> movies;
    private String currentUser;

    public MoviesAdapter(Context context, List<Pelicula> movies, String user) {
        this.context = context;
        this.movies = movies;
        this.currentUser=user;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.movieName.setText(movies.get(position).getNombre());
        holder.movieGenre.setText(movies.get(position).getGenero());
        Glide.with(context).load(movies.get(position).getImagen()).into(holder.imageView);
    }



    @Override
    public int getItemCount() {
        return movies.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView movieName;
        public TextView movieGenre;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            movieName = (TextView) itemView.findViewById(R.id.moviename);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            movieGenre = (TextView) itemView.findViewById(R.id.genre);
            imageView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            showPopupMenu(v,position);
        }
    }

    private void showPopupMenu(View view, int poaition) {
        PopupMenu popup = new PopupMenu(context, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_context, popup.getMenu());
        popup.setOnMenuItemClickListener(new MenuClickListener(poaition));
        popup.show();
    }


    class MenuClickListener implements PopupMenu.OnMenuItemClickListener {
        Integer pos;
        public MenuClickListener(int pos) {
            this.pos=pos;
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            Queryphp q =  new Queryphp("");
            switch (menuItem.getItemId()) {
                case R.id.action_favourite:
                    Toast.makeText(context, movies.get(pos).getNombre()+" is added to favorite", Toast.LENGTH_SHORT).show();
                    q.setQuery("addfavorite.php?id="+movies.get(pos).getId()+"&user='"+currentUser+"'");
                    try {
                        q.returnRequest();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return true;
                case R.id.action_rate:
                    Toast.makeText(context, movies.get(pos).getNombre()+" is rated", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_comment:
                    Intent pIntent = new Intent(context, ComentsActivity.class);
                    pIntent.putExtra("idpelicula",movies.get(pos).getId());
                    pIntent.putExtra("user",currentUser);
                    context.startActivity(pIntent);
                    return true;
                case R.id.action_see_data:
                    Toast.makeText(context, "WIP "+movies.get(pos).getNombre(), Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }
}