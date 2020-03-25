package dz.islem.mvvmarch.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dz.islem.mvvmarch.R;
import dz.islem.mvvmarch.data.network.model.Answer;
import dz.islem.mvvmarch.data.network.model.Message;
import dz.islem.mvvmarch.data.network.model.Question;

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Message> messages;

    public MainAdapter(){
        messages = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = null;
        switch (viewType){
            case Message.MESSAGE_SENT:
                mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sent_message, parent, false);
                return new QuestionHolder(mView);
            case Message.MESSAGE_RECEIVED:
            default:
                mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_received_message, parent, false);
                return new AnswerHolder(mView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // bind the model with the viewholder by ViewType
        switch (holder.getItemViewType()){
            case Message.MESSAGE_SENT:
                ((QuestionHolder) holder).bind(position);
                break;
            case Message.MESSAGE_RECEIVED:
            default:
                ((AnswerHolder) holder).bind(position);
                break;
        }
    }

    public void clear(){
        this.messages.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return messages == null ? 0 : messages.size();
    }

    @Override
    public int getItemViewType(int position){
        return messages.get(position).getType();
    }

    private Message getItem(int position) {
        return messages.get(position);
    }

    public void setMessages(List<Message> messages){
        this.messages = messages;
        notifyDataSetChanged();
    }

    public void AddMessages(List<? extends Message> messages){
        this.messages.addAll(messages);
        notifyDataSetChanged();
    }

    public class QuestionHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textview_message)
        TextView textViewMessage;

        public QuestionHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        void bind(int position){
            Question message = (Question) getItem(position);
            setMessage(message.getMessage());
        }

        private void setMessage(String message){
            textViewMessage.setText(message);
        }
    }

    public class AnswerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textview_message)
        TextView textViewMessage;

        public AnswerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        void bind(int position){
            Answer message = (Answer) getItem(position);
            setMessage(message.getMessage());
        }

        private void setMessage(String message){
            textViewMessage.setText(message);
        }
    }
}
