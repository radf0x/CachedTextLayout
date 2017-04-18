package com.fox.cachedtextview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
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

public class MainActivity extends AppCompatActivity {

    private Button btnCachedLayout;
    private Button btnNormalLayout;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        btnCachedLayout = ViewUtils.findViewById( this, R.id.btn_cached_layout );
        btnNormalLayout = ViewUtils.findViewById( this, R.id.btn_default_layout );

        btnCachedLayout.setOnClickListener( ( view ) -> {
            Intent intent = new Intent( MainActivity.this, CachedActivity.class );
            startActivity( intent );
        } );

        btnNormalLayout.setOnClickListener( ( view ) -> {
            Intent intent = new Intent( MainActivity.this, NormalActivity.class );
            startActivity( intent );
        } );
    }
}
