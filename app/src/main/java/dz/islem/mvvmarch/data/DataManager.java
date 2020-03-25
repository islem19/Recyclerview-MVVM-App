package dz.islem.mvvmarch.data;

import android.app.Application;

import dz.islem.mvvmarch.data.db.database.MessageDatabase;
import dz.islem.mvvmarch.data.network.services.RemoteService;

public class DataManager {

    private static DataManager mInstance;

    private DataManager(){}

    public static synchronized DataManager getInstance() {
        return mInstance == null ? mInstance = new DataManager() : mInstance;
    }

    public RemoteService getRemoteService(){
        return RemoteService.getInstance();
    }

    public MessageDatabase getMessageDatabase(Application app){ return MessageDatabase.getInstance(app); }

}
