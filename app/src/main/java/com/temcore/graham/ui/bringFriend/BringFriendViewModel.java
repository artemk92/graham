package com.temcore.graham.ui.bringFriend;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BringFriendViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public BringFriendViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}