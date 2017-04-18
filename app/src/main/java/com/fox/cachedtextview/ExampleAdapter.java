package com.fox.cachedtextview;

import android.support.v7.widget.RecyclerView;
import android.text.StaticLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ravic on 18/4/2017.
 */

public class ExampleAdapter extends RecyclerView.Adapter< RecyclerView.ViewHolder > {

    public ExampleAdapter() {
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType ) {
        return new ItemHolder( LayoutInflater.from( parent.getContext() ).inflate( R.layout.example_item, parent, false ) );
    }

    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder holder, int position ) {
        if ( holder instanceof ItemHolder ) {
            ( ( ItemHolder ) holder ).bindData( position );
        }
    }

    @Override
    public int getItemCount() {
        return CacheLayoutManager.getInstance().getLayoutCount();
    }

    private class ItemHolder extends RecyclerView.ViewHolder {
        private CachedTextView cachedTextView;

        public ItemHolder( View itemView ) {
            super( itemView );
            cachedTextView = ViewUtils.findViewById( itemView, R.id.cached_text_view );
        }

        private void bindData( int position ) {
            cachedTextView.setLayout( CacheLayoutManager.getInstance().getStaticLayout( position ) );
        }
    }
}
