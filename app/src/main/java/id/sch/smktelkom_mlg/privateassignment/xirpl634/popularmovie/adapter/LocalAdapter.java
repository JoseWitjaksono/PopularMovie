package id.sch.smktelkom_mlg.privateassignment.xirpl634.popularmovie.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl634.popularmovie.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl634.popularmovie.model.Local;

/**
 * Created by Jose Witjaksono on 13/06/2017.
 */

public class LocalAdapter extends RecyclerView.Adapter<LocalAdapter.ViewHolder> {
	Context context;
	ArrayList<Local> savedList;

	public LocalAdapter(Context context, ArrayList<Local> savedList) {
		this.context = context;
		this.savedList = savedList;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.local_list, parent, false);
		ViewHolder vh = new ViewHolder(v);
		return vh;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		holder.tvTitle.setText(savedList.get(position).getTitle());
		holder.tvDesc.setText(savedList.get(position).getDesc());
	}

	@Override
	public int getItemCount() {
		return savedList.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		TextView tvTitle, tvDesc;

		public ViewHolder(View itemView) {
			super(itemView);

			tvTitle = (TextView) itemView.findViewById(R.id.viewTitle);
			tvDesc = (TextView) itemView.findViewById(R.id.viewDesc);
		}
	}
}
