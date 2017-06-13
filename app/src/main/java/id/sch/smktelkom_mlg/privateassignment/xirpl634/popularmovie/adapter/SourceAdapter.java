package id.sch.smktelkom_mlg.privateassignment.xirpl634.popularmovie.adapter;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl634.popularmovie.DatabaseHelper;
import id.sch.smktelkom_mlg.privateassignment.xirpl634.popularmovie.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl634.popularmovie.Section1Fragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl634.popularmovie.model.Source;

/**
 * Created by hyuam on 12/10/2016.
 */

public class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.ViewHolder>
{
    ArrayList<Source> list;
    ISourceAdapter mISourceAdapter;
    Section1Fragment context;
    Context ctx;
    
    public SourceAdapter(Context ctx,Section1Fragment context, ArrayList<Source> list)
    {
        this.list = list;
        this.context = context;
        this.ctx = ctx;
        mISourceAdapter = (ISourceAdapter) context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.source_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)
    {
        final Source source = list.get(position);
        String iurl = "http://image.tmdb.org/t/p/w500/"+source.backdrop_path;
        holder.tvName.setText(source.title);
        holder.tvDesc.setText(source.overview);
        holder.itemView.setBackgroundColor(source.color);
        Glide.with(context).load(iurl)
                .crossFade()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(holder.ivSource);
    }
    
    @Override
    public int getItemCount()
    {
        if (list != null)
            return list.size();
        return 0;
    }
    
    public interface ISourceAdapter
    {
        void showArticles(String id, String title);
    }
    
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvName;
        TextView tvDesc;
        ImageView ivSource;
        
        public ViewHolder(View itemView)
        {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.textViewName);
            tvDesc = (TextView) itemView.findViewById(R.id.textViewDesc);
            ivSource = (ImageView) itemView.findViewById(R.id.imageViewSource);
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Source source = list.get(getAdapterPosition());
                    String title = source.title;
                    String desc = source.overview;
                    DatabaseHelper db = new DatabaseHelper(ctx);
                    if (db.insertLocal(title, desc)) {
                        Toast.makeText(ctx, "Berhasil menyimpan", Toast.LENGTH_SHORT).show();
                    }
                    mISourceAdapter.showArticles(source.id, source.title);
                }
            });
        }
    }
}
