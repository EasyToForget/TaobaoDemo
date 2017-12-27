package com.smile.taobaodemo.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smile.taobaodemo.R;
import com.smile.taobaodemo.model.entity.DescBase;
import com.smile.taobaodemo.ui.adapter.GoodsDescAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsDescFragment extends Fragment {
    public static final String ARGS_DATA = "args_data";
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private List<DescBase> list = new ArrayList<>();

    public static GoodsDescFragment newInstance(List<DescBase> bean) {
        GoodsDescFragment fragment = new GoodsDescFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARGS_DATA, (ArrayList<? extends Parcelable>) bean);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = getArguments().getParcelableArrayList(ARGS_DATA);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods_desc, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        Activity activity = getActivity();

        recyclerView.setLayoutManager(new GridLayoutManager(activity, 1));
        recyclerView.setAdapter(new GoodsDescAdapter(activity.getApplicationContext(), activity, list));
    }

}
