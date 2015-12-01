/*
 * Copyright 2014 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.caij.codehub.ui.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.caij.codehub.Constant;
import com.caij.codehub.R;
import com.caij.codehub.ui.fragment.SearchRepositoriesFragment;


public class SearchActivity extends BaseCodeHubToolBarActivity {

    private String mQuery;
    private SearchRepositoriesFragment mSearchRepositoriesFragment;

    @Override
    protected void handleIntent(Intent intent) {
        setTitle(getString(R.string.description_search));
        mQuery = intent.getStringExtra(Constant.REPO_SEARCH_Q);
        mSearchRepositoriesFragment =  new SearchRepositoriesFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.base_code_hub_container, mSearchRepositoriesFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.search, menu);
        final MenuItem searchItem = menu.findItem(R.id.menu_search);
        if (searchItem != null) {
            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            final SearchView view = (SearchView) searchItem.getActionView();
            if (view != null) {
                view.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
                view.setIconified(false);
                view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        mSearchRepositoriesFragment.search(s);
                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        return false;
                    }

                });

                view.setOnCloseListener(new SearchView.OnCloseListener() {
                    @Override
                    public boolean onClose() {
                        finish();
                        return false;
                    }
                });

                if (!TextUtils.isEmpty(mQuery)) {
                    view.setQuery(mQuery, true);
                }
            }
        }
        return true;
    }

    @Override
    protected int getAttachLayoutId() {
        return 0;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
