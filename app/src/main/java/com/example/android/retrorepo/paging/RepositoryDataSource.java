package com.example.android.retrorepo.paging;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import com.example.android.retrorepo.remote.NetworkService;
import com.example.android.retrorepo.remote.data.Repository;

public class RepositoryDataSource extends PageKeyedDataSource<Integer, Repository> {

    private NetworkService networkService;


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Repository> callback) {

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Repository> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Repository> callback) {

    }
}
