package dz.islem.mvvmarch.data.network.model;

public interface Message {
    int MESSAGE_SENT = 0;
    int MESSAGE_RECEIVED = 1;

    int getType();
}
