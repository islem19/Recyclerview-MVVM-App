package dz.islem.mvvmarch.data.network.model;

import java.io.Serializable;

public class Answer implements Serializable, Message {

    private String message;
    private long time;

    public Answer(String message, long time) {
        this.message = message;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public int getType() {
        return Message.MESSAGE_RECEIVED;
    }
}
