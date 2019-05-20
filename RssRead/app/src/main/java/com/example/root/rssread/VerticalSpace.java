package com.example.root.rssread;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by root on 8/27/17.
 */

public class VerticalSpace extends RecyclerView.ItemDecoration {
    int Space;

    public VerticalSpace(int space) {
        this.Space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left=Space;
        outRect.bottom=Space;
        outRect.right=Space;
        if(parent.getChildLayoutPosition(view)==0){
            outRect.top=Space;
        }
    }
}
