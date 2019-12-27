/*
 * Copyright (c) 2017 [zhiye.wei@gmail.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.et.util.lifeful;


import android.content.Context;

import com.example.et.View.LoadDialog;
import com.example.et.util.PublicSwipeRefreshLayout.SwipeRefreshLayout;


//com.example.administrator.huijianzhi

/**
``
 */

public class OnLoadLifefulListener<T> extends OnLoadListener<T> {

    private LifefulGenerator<OnLoadListener<T>> lifefulGenerator;
    private Lifeful lifeful;
    private Context context;
    private SwipeRefreshLayout swipeRefreshLayout;

    public OnLoadLifefulListener(
            SwipeRefreshLayout swipeRefreshLayout, OnLoadListener<T> listener, Lifeful lifeful) {
        this.lifeful = lifeful;
        this.context = (Context) lifeful;
        this.swipeRefreshLayout = swipeRefreshLayout;
        if (this.swipeRefreshLayout == null) {
           // LoadDialog.show(context);
        }else if (this.swipeRefreshLayout!=null){
            if (!swipeRefreshLayout.isRefreshing()&&!swipeRefreshLayout.isLoading()){
              //  LoadDialog.show(context);
            }
        }
        lifefulGenerator = new DefaultLifefulGenerator<>(listener, lifeful);
    }

    @Override
    public void onSuccess(T success) {

        if (this.swipeRefreshLayout == null) {
           // LoadDialog.dismiss(context);
        }else if (this.swipeRefreshLayout!=null){

            if (!swipeRefreshLayout.isRefreshing()&&!swipeRefreshLayout.isLoading()){
               // LoadDialog.dismiss(context);
            }else {
                this.swipeRefreshLayout.setState();
            }
        }
        if (LifefulUtil.destroy(lifefulGenerator))
            return;
        lifefulGenerator.getCallback().onSuccess(success);
    }

    @Override
    public void onError(String error) {
        if (this.swipeRefreshLayout == null) {
            //LoadDialog.dismiss(context);
        }else if (this.swipeRefreshLayout!=null){
            if (!swipeRefreshLayout.isRefreshing()&&!swipeRefreshLayout.isLoading()){
               // LoadDialog.dismiss(context);
            }else {
                this.swipeRefreshLayout.setState();
            }
        }
        if (LifefulUtil.destroy(lifefulGenerator))
            return;
        lifefulGenerator.getCallback().onError(error);
    }
}
