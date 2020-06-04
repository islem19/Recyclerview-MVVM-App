package dz.islem.mvvmarch.ui.main;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnEditorAction;
import dz.islem.mvvmarch.R;
import dz.islem.mvvmarch.data.DataManager;
import dz.islem.mvvmarch.data.network.model.Answer;
import dz.islem.mvvmarch.data.network.model.Question;
import dz.islem.mvvmarch.ui.base.BaseActivity;

public class MainActivity extends BaseActivity<MainViewModel> {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.edittext_message)
    EditText mEdittextMessage;
    private MainAdapter mainAdapter;

    @Override
    protected MainViewModel createViewModel() {
        MainViewModelFactory factory = new MainViewModelFactory(
                DataManager.getInstance().getMessageDatabase(getApplication()).messageDao()
                ,DataManager.getInstance().getRemoteService());

        return new ViewModelProvider(this, factory).get(MainViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupRecyclerView();
        viewModel.getAnswers().observe(this, new AnswerObserver());
        viewModel.getSavedMessages().observe(this ,  messages -> mainAdapter.setMessages(messages));
        viewModel.loadDb();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.clear) {
            mainAdapter.clear();
            viewModel.clearDb();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView(){
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mainAdapter = new MainAdapter();
        mRecyclerView.setAdapter(mainAdapter);
    }

    private class AnswerObserver implements Observer<List<Answer>> {
        @Override
        public void onChanged(List<Answer> answerList) {
            if (answerList == null ) return;
            mainAdapter.AddMessages(answerList);
            mRecyclerView.scrollToPosition(mainAdapter.getItemCount()-1);
        }
    }

    @OnEditorAction(R.id.edittext_message)
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if(actionId == EditorInfo.IME_ACTION_DONE){
            if ( mEdittextMessage.getText().length() > 0 ) {
                // create a new Question model to be sent
                Question question = new Question(String.valueOf(mEdittextMessage.getText()),System.currentTimeMillis());
                mainAdapter.AddMessages(Collections.singletonList(question));
                viewModel.saveMessage(Collections.singletonList(question));
                viewModel.loadLocalAnswers();
                // clear the text from the edit text
                mEdittextMessage.getText().clear();
            }
            return true;
        }
        return false;
    }
}
