package michael.vocabflash.Intergrate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

import michael.vocabflash.Model.Vocab;
import michael.vocabflash.R;

public class VocabAdapter extends RecyclerView.Adapter<VocabAdapter.VocabViewHolder> {
    private List<Vocab> vocabs;
    private IClickItemListener iClickItemListener;

    public VocabAdapter(List<Vocab> vocabs, IClickItemListener iClickItemListener) {
        this.vocabs = vocabs;
        this.iClickItemListener = iClickItemListener;
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
        holder.materialCardView.setOnClickListener(view -> {
            iClickItemListener.onClickItem(vocab);
        });
    }

    @Override
    public int getItemCount() {
        if (vocabs != null) {
            return vocabs.size();
        }
        return 0;
    }

    public static class VocabViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        LinearLayout layoutForeground;
        MaterialCardView materialCardView;

        public VocabViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.vocab_word);
            layoutForeground = itemView.findViewById(R.id.vocab_foreground);
            materialCardView = itemView.findViewById(R.id.vocab_card);
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
