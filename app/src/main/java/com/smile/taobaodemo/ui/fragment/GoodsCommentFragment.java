package com.smile.taobaodemo.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.smile.taobaodemo.R;
import com.smile.taobaodemo.base.Type;
import com.smile.taobaodemo.model.entity.CommentBase;
import com.smile.taobaodemo.ui.adapter.GoodsCommentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsCommentFragment extends Fragment {
    public static final String ARGS_DATA = "args_data";
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private Context context;
    private Activity activity;
    private GoodsCommentAdapter adapter;
    private List<CommentBase> list = new ArrayList<>();
    private CommentBase footerItem = new CommentBase();

    public static GoodsCommentFragment newInstance(List<CommentBase> list) {
        GoodsCommentFragment fragment = new GoodsCommentFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARGS_DATA, (ArrayList<? extends Parcelable>) list);
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
        View view = inflater.inflate(R.layout.fragment_goods_comment, container, false);
        ButterKnife.bind(this, view);

        initView();
        return view;
    }

    public void initView() {
        activity = getActivity();
        context = activity.getApplicationContext();
        footerItem.setType(Type.TYPE_FOOTER_FULL);
        list.add(footerItem);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        adapter = new GoodsCommentAdapter(context, activity, list);
        recyclerView.setAdapter(adapter);
    }
}
