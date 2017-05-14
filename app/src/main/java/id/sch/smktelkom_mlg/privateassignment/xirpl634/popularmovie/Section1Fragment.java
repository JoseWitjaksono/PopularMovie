package id.sch.smktelkom_mlg.privateassignment.xirpl634.popularmovie;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import id.sch.smktelkom_mlg.privateassignment.xirpl634.popularmovie.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl634.popularmovie.adapter.SourceAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl634.popularmovie.model.Source;
import id.sch.smktelkom_mlg.privateassignment.xirpl634.popularmovie.model.SourcesResponse;
import id.sch.smktelkom_mlg.privateassignment.xirpl634.popularmovie.service.GsonGetRequest;
import id.sch.smktelkom_mlg.privateassignment.xirpl634.popularmovie.service.VolleySingleton;

/**
 * A simple {@link Fragment} subclass.
 */
public class Section1Fragment extends Fragment implements SourceAdapter.ISourceAdapter{
	public static final String SOURCEID = "sourceId";
	public static final String SOURCENAME = "sourceName";
	ArrayList<Source> mList = new ArrayList<>();
	SourceAdapter mAdapter;

	public Section1Fragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View myView = inflater.inflate(R.layout.fragment_section1, container, false);

		RecyclerView recyclerView = (RecyclerView) myView.findViewById(R.id.recyclerView);
		StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
		recyclerView.setLayoutManager(layoutManager);
		mAdapter = new SourceAdapter(this, mList);
		recyclerView.setAdapter(mAdapter);
		downloadDataSources();

		return myView;
	}

	private void downloadDataSources()
	{
		String url = "https://api.themoviedb.org/3/movie/popular?api_key=ba8049249923de75707c1086afd6172d&language=en-US&page=1";

		GsonGetRequest<SourcesResponse> myRequest = new GsonGetRequest<SourcesResponse>
				(url, SourcesResponse.class, null, new Response.Listener<SourcesResponse>()
				{

					@Override
					public void onResponse(SourcesResponse response)
					{
						Log.d("FLOW", "onResponse: " + (new Gson().toJson(response)));
						if (response.page.equals("1"))
						{
							fillColor(response.results);
							mList.addAll(response.results);
							mAdapter.notifyDataSetChanged();
						}
					}

				}, new Response.ErrorListener()
				{

					@Override
					public void onErrorResponse(VolleyError error)
					{
						Log.e("FLOW", "onErrorResponse: ", error);
					}
				});
		VolleySingleton.getInstance(this.getActivity()).addToRequestQueue(myRequest);
	}

	private void fillColor(List<Source> sources)
	{
		for (int i = 0; i < sources.size(); i++)
			sources.get(i).color = ColorUtil.getRandomColor();
	}

	@Override
	public void showArticles(String id, String name)
	{
		Intent intent = new Intent(this.getActivity(), ArticlesActivity.class);
		intent.putExtra(SOURCEID, id);
		intent.putExtra(SOURCENAME, name);
		startActivity(intent);
	}

}
