package com.fox.cachedtextview;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Copyright 2017 RavicPN
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


public class CachedTextView extends View {
    private Layout mLayout;
    private int width;
    private int height;

    public CachedTextView( Context context ) {
        super( context );
    }

    public CachedTextView( Context context, @Nullable AttributeSet attrs ) {
        super( context, attrs );
    }

    public CachedTextView( Context context, @Nullable AttributeSet attrs, int defStyleAttr ) {
        super( context, attrs, defStyleAttr );
    }

    public void setTextLayout( Layout layout ) {
        mLayout = layout;
    }

    public void setLayout( Layout layout ) {
        mLayout = layout;
        if ( mLayout.getWidth() != width || mLayout.getHeight() != height ) {
            width = mLayout.getWidth();
            height = mLayout.getHeight();
            requestLayout();
        }
    }

    @Override
    protected void onDraw( Canvas canvas ) {
        super.onDraw( canvas );
        canvas.save();

        if ( null != mLayout ) {
            mLayout.draw( canvas, null, null, 0 );
        }

        canvas.restore();
    }

    @Override
    protected void onLayout( boolean changed, int left, int top, int right, int bottom ) {
        super.onLayout( changed, left, top, right, bottom );
    }

    @Override
    protected void onMeasure( int widthMeasureSpec, int heightMeasureSpec ) {
        if ( null != mLayout ) {
            setMeasuredDimension( mLayout.getWidth(), mLayout.getHeight() );
        } else {
            super.onMeasure( widthMeasureSpec, heightMeasureSpec );
        }
    }
}
