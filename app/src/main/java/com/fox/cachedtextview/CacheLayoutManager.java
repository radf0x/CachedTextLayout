package com.fox.cachedtextview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.text.Layout;
import android.text.SpannableString;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import android.view.WindowManager;

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


public class CacheLayoutManager {

    private final String TAG = this.getClass().getSimpleName();

    private static CacheLayoutManager INSTANCE = null;

    private StaticLayout[] mLayouts = new StaticLayout[ 1000 ]; // upper bound for test.

    private TextPaint mTextPaint;
    private Layout.Alignment mLayoutAlignment;

    private Canvas mCanvas;

    private int screenWidth;

    public static CacheLayoutManager getInstance() {
        synchronized ( CacheLayoutManager.class ) {
            if ( null == INSTANCE ) {
                INSTANCE = new CacheLayoutManager();
            }
        }
        return INSTANCE;

    }

    public void initLayout( Context context, CharSequence input ) {
        mTextPaint = new TextPaint( Paint.ANTI_ALIAS_FLAG );
        mTextPaint.density = context.getResources().getDisplayMetrics().density;
        mTextPaint.setTextSize( dpToPx( context, 25 ) );
        mLayoutAlignment = Layout.Alignment.ALIGN_NORMAL;
        screenWidth = getScreenWidth( context );
        mCanvas = new Canvas();

        for ( int i = 0; i < mLayouts.length; i++ ) {
            mLayouts[ i ] = new StaticLayout( new SpannableString( new StringBuilder( input ).append( i ) ), mTextPaint, screenWidth, mLayoutAlignment, 1.0f, 0f, true );
            mLayouts[ i ].draw( mCanvas );
        }
    }

    public int getLayoutCount() {
        return mLayouts.length;
    }

    public StaticLayout getStaticLayout( int index ) {
        return mLayouts[ index ];
    }

    private static int getScreenWidth( Context context ) {
        WindowManager manager = ( WindowManager ) context.getSystemService( Context.WINDOW_SERVICE );

        Point size = new Point();
        manager.getDefaultDisplay().getSize( size );

        return size.x;
    }

    private static float dpToPx( Context context, int dp ) {
        return context.getResources().getDisplayMetrics().density * dp;
    }
}
