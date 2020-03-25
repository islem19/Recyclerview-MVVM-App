package dz.islem.mvvmarch.data.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MessageClass {

    @PrimaryKey(autoGenerate = true)
    private int _id;

    @ColumnInfo(name = "message")
    private String message;

    @ColumnInfo(name = "type")
    private int type;

    @ColumnInfo(name = "data")
    private String date;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
