package id.sch.smktelkom_mlg.privateassignment.xirpl634.popularmovie;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl634.popularmovie.DatabaseHelper;
import id.sch.smktelkom_mlg.privateassignment.xirpl634.popularmovie.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl634.popularmovie.adapter.LocalAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl634.popularmovie.model.Local;

/**
 * A simple {@link Fragment} subclass.
 */
public class Section2Fragment extends Fragment {
	private RecyclerView rv;
	private DatabaseHelper db;
	private LocalAdapter savedAdapter;
	private ArrayList<Local> saved = new ArrayList<>();

	public Section2Fragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_section2, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		db = new DatabaseHelper(getContext());
		rv = (RecyclerView) getView().findViewById(R.id.recyclerViewSaved);
		rv.setLayoutManager(new LinearLayoutManager(getActivity()));
		savedAdapter = new LocalAdapter(getActivity(), saved);
		fillData();
	}

	private void fillData() {
		saved.clear();
		Cursor c = db.getLocal();
		while (c.moveToNext()) {
			String title = c.getString(1);
			String desc = c.getString(2);

			Local local = new Local(title, desc);
			saved.add(local);

			if (!(saved.size() < 1)) {
				rv.setAdapter(savedAdapter);
			} else {
				rv.setVisibility(View.GONE);
			}
		}
	}

}
