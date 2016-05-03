package com.cremy.greenrobotutils.library.ui.recyclerview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;


/**
 * This is a inherited class from RecyclerView handling pagination
 * Compatible with: LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager
 * http://developer.android.com/reference/android/support/v7/widget/StaggeredGridLayoutManager.html
 */
public final class PaginatedRecyclerView extends RecyclerView {
    private final static String TAG = "PaginatedRecyclerView";

    //region LayoutManager types
    private final static int LAYOUT_MANAGER_LINEAR = 0;
    private final static int LAYOUT_MANAGER_GRID = 1;
    private final static int LAYOUT_MANAGER_STAGGERED_GRID = 2;

    private int currentLayoutManager = LAYOUT_MANAGER_LINEAR;
    //endregion

    //region Callback interface
    public interface IPaginatedRecyclerView {
        void OnNewPageNeeded();
    }
    //endregion

    private boolean isPaginationEnabled = true;
    private int itemPerPage = 0;
    private boolean isLoadingNewPage = false;
    private IPaginatedRecyclerView callback = null;

    public PaginatedRecyclerView(Context context) {
        super(context);
        this.setLinearLayoutManager(context);
        this.setScrollListenerForPagination();
    }

    public PaginatedRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setLinearLayoutManager(context);
        this.setScrollListenerForPagination();
    }

    public PaginatedRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setLinearLayoutManager(context);
        this.setScrollListenerForPagination();
    }

    /**
     * Allows to change the layout manager to LinearLayout
     * @param _context
     */
    public void setLinearLayoutManager(Context _context) {
        this.currentLayoutManager = LAYOUT_MANAGER_LINEAR;
        this.setLayoutManager(new LinearLayoutManager(_context));
    }

    /**
     * Allows to change the layout manager to GridLayout
     * @param _context
     * @param _spanCount
     */
    public void setGridLayoutManager(Context _context,
                                      final int _spanCount) {
        this.currentLayoutManager = LAYOUT_MANAGER_GRID;
        this.setLayoutManager(new GridLayoutManager(_context, _spanCount));
    }

    /**
     * Allows to change the layout manager to GridLayout
     * @param _context
     * @param _spanCount
     * @param _orientation (e.g. StaggeredGridLayoutManager.VERTICAL)
     */
    public void setStaggeredGridLayoutManager(Context _context,
                                     final int _spanCount,
                                     final int _orientation) {
        this.currentLayoutManager = LAYOUT_MANAGER_STAGGERED_GRID;
        this.setLayoutManager(new StaggeredGridLayoutManager(_spanCount, _orientation));
    }


    private void  setScrollListenerForPagination() {
        this.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = 0;
                int totalItemCount = 0;
                int[] firstVisibleItemPositions=null;

                if (currentLayoutManager == LAYOUT_MANAGER_LINEAR) {
                    LinearLayoutManager lm = (LinearLayoutManager) recyclerView.getLayoutManager();
                    visibleItemCount = lm.getChildCount();
                    totalItemCount = lm.getItemCount();
                    firstVisibleItemPositions = new int[1];
                    firstVisibleItemPositions[0] = lm.findFirstVisibleItemPosition();
                }
                else if (currentLayoutManager == LAYOUT_MANAGER_GRID) {
                    GridLayoutManager lm = (GridLayoutManager) recyclerView.getLayoutManager();
                    visibleItemCount = lm.getChildCount();
                    totalItemCount = lm.getItemCount();
                    firstVisibleItemPositions = new int[1];
                    firstVisibleItemPositions[0] = lm.findFirstVisibleItemPosition();
                }
                else if (currentLayoutManager == LAYOUT_MANAGER_STAGGERED_GRID) {
                    StaggeredGridLayoutManager lm = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
                    visibleItemCount = lm.getChildCount();
                    totalItemCount = lm.getItemCount();
                    // Important: The StaggeredGridLayout return MORE than one first visible item position
                    firstVisibleItemPositions = lm.findFirstVisibleItemPositions(firstVisibleItemPositions);
                }


                if (isPaginationEnabled && !isLoadingNewPage) {
                    final int firstVisibleItemPositionCount = firstVisibleItemPositions.length;
                    // LinearLayout and GridLayout scenarios
                    if (firstVisibleItemPositionCount==1) {
                        if ((visibleItemCount + firstVisibleItemPositions[0]) >= totalItemCount
                                && firstVisibleItemPositions[0] >= 0
                                && totalItemCount >= itemPerPage) {
                            askNewPage();
                        }
                    }
                    // StaggeredGrid scenario
                    else if (firstVisibleItemPositionCount>1) {
                        for (int i = 0; i < firstVisibleItemPositionCount; i++) {
                            if ((visibleItemCount + firstVisibleItemPositions[i]) >= totalItemCount
                                    && firstVisibleItemPositions[i] >= 0
                                    && totalItemCount >= itemPerPage) {
                                askNewPage();
                                break;
                            }
                        }
                    }

                }
            }
        });
    }

    private void askNewPage() {
        this.isLoadingNewPage = true;
        this.callback.OnNewPageNeeded();
    }


    public void setItemPerPage(int itemPerPage) {
        this.itemPerPage = itemPerPage;
    }

    public void setLoadingNewPage(boolean loadingNewPage) {
        isLoadingNewPage = loadingNewPage;
    }

    public void setCallback(IPaginatedRecyclerView _callback) {
        this.callback = _callback;
    }

    public void setPaginationEnabled(boolean paginationEnabled) {
        isPaginationEnabled = paginationEnabled;
    }
}