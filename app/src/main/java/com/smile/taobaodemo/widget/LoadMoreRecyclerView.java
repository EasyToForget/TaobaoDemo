package com.smile.taobaodemo.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * @author Smile Wei
 * @since 2016.08.11
 */
public class LoadMoreRecyclerView extends RecyclerView {
    private OnLoadMoreListener loadMoreListener;

    private int page = 1;
    private int lastPage = 1;
    private boolean isLoading = false;

    public LoadMoreRecyclerView(Context context) {
        super(context);
    }

    public LoadMoreRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadMoreRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);

        int lastVisibleItem = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
        int totalItemCount = getLayoutManager().getItemCount();

        if (lastVisibleItem == totalItemCount - 1 && dy > 0) {
            if (!isLoading && page < lastPage) {
                isLoading = true;
                loadMoreListener.onLoadMore();
            }
        }
    }

    public void setLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }

    public void setPage(int page, int lastPage) {
        this.page = page;
        this.lastPage = lastPage;
    }

    public void setOnLoadMoreListener(final OnLoadMoreListener listener) {
        this.loadMoreListener = listener;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

}