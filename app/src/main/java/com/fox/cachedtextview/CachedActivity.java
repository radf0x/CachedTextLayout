package com.fox.cachedtextview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.solver.Cache;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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

public class CachedActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    private RecyclerView recyclerView;

    private final int KEEP_ALIVE_TIME = 1;
    private final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
    private final int MAX_NUM_OF_CORES = Runtime.getRuntime().availableProcessors();
    private final BlockingQueue< Runnable > RUNNABLES = new LinkedBlockingDeque<>();
    private final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor( 1, MAX_NUM_OF_CORES, KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, RUNNABLES );

    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_example );
        recyclerView = ViewUtils.findViewById( this, R.id.recycler_view );
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
        recyclerView.setAdapter( new ExampleAdapter() );
        EXECUTOR.execute( () -> CacheLayoutManager.getInstance().initLayout( this, "cached layout number : " ) );
    }
}
