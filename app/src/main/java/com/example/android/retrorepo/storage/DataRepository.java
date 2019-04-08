package com.example.android.retrorepo.storage;

import android.arch.lifecycle.LiveData;
import com.example.android.retrorepo.storage.entities.SearchTermEntity;

import java.util.List;

public interface DataRepository {
    LiveData<List<SearchTermEntity>> getSearchTerms();

}
