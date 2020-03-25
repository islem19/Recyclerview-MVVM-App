package dz.islem.mvvmarch.data.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import dz.islem.mvvmarch.data.db.entity.MessageClass;

@Dao
public interface MessageDAO {

    @Query("SELECT * FROM MessageClass")
    List<MessageClass> getAll();

    @Query("DELETE FROM MessageClass")
    void dropTable();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<MessageClass> messages);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MessageClass message);

    @Update
    void update(MessageClass message);

    @Delete
    void delete(MessageClass message);

}
