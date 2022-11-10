package michael.vocabflash;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VocabAdapter extends RecyclerView.Adapter<VocabAdapter.VocabViewHolder> {
    private List<Vocab> vocabs;

    public VocabAdapter(List<Vocab> vocabs) {
        this.vocabs = vocabs;
    }

    @NonNull
    @Override
    public VocabViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vocab_item, parent, false);
        return new VocabViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VocabViewHolder holder, int position) {
        Vocab vocab = vocabs.get(position);
        if (vocab == null) {
            return;
        }
        holder.textView.setText(vocab.getWord());
    }

    @Override
    public int getItemCount() {
        if (vocabs != null) {
            return vocabs.size();
        }
        return 0;
    }

    public class VocabViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        LinearLayout layoutForeground;

        public VocabViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.vocab_word);
            layoutForeground = itemView.findViewById(R.id.vocab_foreground);
        }
    }

    public void removeItem(int idx) {
        vocabs.remove(idx);
        notifyItemRemoved(idx);
    }

    public void undoItem(Vocab vocab, int idx) {
        vocabs.add(idx, vocab);
        notifyItemInserted(idx);
    }
}
