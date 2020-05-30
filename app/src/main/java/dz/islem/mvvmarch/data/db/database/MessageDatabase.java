package dz.islem.mvvmarch.data.db.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import dz.islem.mvvmarch.data.db.dao.MessageDAO;
import dz.islem.mvvmarch.data.db.entity.MessageClass;

@Database(entities = {MessageClass.class}, version = 2, exportSchema = true)
public abstract class MessageDatabase extends RoomDatabase {

    private static MessageDatabase mInstance;

    public abstract MessageDAO messageDao();

    private static MessageDatabase initialize(Context mContext){

        mInstance = Room.databaseBuilder(mContext
        ,MessageDatabase.class
        ,"Messages.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        return mInstance;
    }

    public static MessageDatabase getInstance(Context mContext){
        return mInstance == null ? initialize(mContext) : mInstance;
    }

    public static void destoryInstance(){
        mInstance = null;
    }


}
