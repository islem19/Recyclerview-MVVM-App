package dz.islem.mvvmarch.ui.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dz.islem.mvvmarch.data.db.dao.MessageDAO;
import dz.islem.mvvmarch.data.db.database.MessageDatabase;
import dz.islem.mvvmarch.data.db.entity.MessageClass;
import dz.islem.mvvmarch.data.network.model.Answer;
import dz.islem.mvvmarch.data.network.model.Message;
import dz.islem.mvvmarch.data.network.model.Question;
import dz.islem.mvvmarch.data.network.services.RemoteService;
import dz.islem.mvvmarch.ui.base.BaseViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends BaseViewModel {

    private final String TAG ="MainViewModel";
    private MutableLiveData<List<Answer>> answers;
    private MutableLiveData<List<Message>> savedMessage;
    private RemoteService mRemoteService;
    private MessageDAO messageDao;
    private ExecutorService mExecutorService;
    private MessageDatabase messageDb;


    MainViewModel(MessageDatabase messageDb, RemoteService mRemoteService){
        this.messageDb = messageDb;
        this.mRemoteService = mRemoteService;
        this.answers = new MutableLiveData<>();
        this.savedMessage = new MutableLiveData<>();
        initDb();
    }

    private void initDb(){
        messageDao = messageDb.messageDao();
        mExecutorService = Executors.newSingleThreadExecutor();
    }

    MutableLiveData<List<Message>> getSavedMessages() { return savedMessage; }

    public void loadDb (){
        List<Message> messages = new ArrayList<>();
        for (MessageClass m : messageDao.getAll()) {
            messages.add(m.getType() == Message.MESSAGE_SENT ? new Question(m.getMessage(), System.currentTimeMillis())
                        : new Answer(m.getMessage(),System.currentTimeMillis()));
        }
        savedMessage.postValue(messages);
    }

    public void clearDb(){
        messageDao.dropTable();
    }

    public void saveMessage(List<? extends Message> messages) {
        for (Message m : messages) {
            MessageClass msg = new MessageClass();
            if ( m.getType() == Message.MESSAGE_SENT){
                msg.setMessage(((Question) m).getMessage());
                msg.setDate("111");
            } else {
                msg.setMessage(((Answer) m).getMessage());
                msg.setDate("111");
            }
            msg.setType(m.getType());
            mExecutorService.execute(() -> messageDao.insert(msg));
        }
    }


    MutableLiveData<List<Answer>> getAnswers(){
        return answers;
    }

    private void setAnswers(List<Answer> answers){
        saveMessage(answers);
        this.answers.postValue(answers);
    }

    public void getRemoteAnswer(){
       // mRemoteService.getRestApi().getAnswer().enqueue(new AnswerCallback());
        mRemoteService.getRestApi().getAnswer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<Answer>>() {
                    @Override
                    public void onSuccess(List<Answer> answers) {
                        if (answers != null )
                            setAnswers(answers);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onFailure: "+ e );

                    }
                });
    }

    void loadLocalAnswers(){
        setAnswers(createLocalAnswers());
    }

    private List<Answer> createLocalAnswers(){
        String message = "hey there";
        long time = 123456;
        List<Answer> mList = new ArrayList<>();
        mList.add(new Answer(message,time));
        mList.add(new Answer(message,time));
        mList.add(new Answer(message,time));
        return mList;
    }


}
