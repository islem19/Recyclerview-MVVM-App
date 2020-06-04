package dz.islem.mvvmarch.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import dz.islem.mvvmarch.data.db.dao.MessageDAO;
import dz.islem.mvvmarch.data.network.services.RemoteService;

public class MainViewModelFactory implements ViewModelProvider.Factory {

    private final RemoteService mRemoteService;
    private final MessageDAO messageDAO;

    public MainViewModelFactory(MessageDAO messageDAO, RemoteService mRemoteService){
        this.messageDAO = messageDAO;
        this.mRemoteService = mRemoteService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class))
            return (T) new MainViewModel(messageDAO, mRemoteService);
        throw new IllegalArgumentException("unknown ViewModel Class");
    }
}
