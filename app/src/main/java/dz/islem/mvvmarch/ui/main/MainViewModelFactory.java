package dz.islem.mvvmarch.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import dz.islem.mvvmarch.data.db.database.MessageDatabase;
import dz.islem.mvvmarch.data.network.services.RemoteService;

public class MainViewModelFactory implements ViewModelProvider.Factory {

    private final RemoteService mRemoteService;
    private final MessageDatabase messageDb;

    public MainViewModelFactory(MessageDatabase messageDb, RemoteService mRemoteService){
        this.messageDb = messageDb;
        this.mRemoteService = mRemoteService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class))
            return (T) new MainViewModel(messageDb, mRemoteService);
        throw new IllegalArgumentException("unknown ViewModel Class");
    }
}
