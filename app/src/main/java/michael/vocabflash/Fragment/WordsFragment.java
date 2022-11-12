package michael.vocabflash.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import michael.vocabflash.Activity.CreationActivity;
import michael.vocabflash.Activity.DetailActivity;
import michael.vocabflash.Intergrate.IClickItemListener;
import michael.vocabflash.Intergrate.ItemTouchHelperListener;
import michael.vocabflash.R;
import michael.vocabflash.Intergrate.RecycleViewItemTouchHelper;
import michael.vocabflash.Model.Vocab;
import michael.vocabflash.Intergrate.VocabAdapter;

public class WordsFragment extends Fragment implements ItemTouchHelperListener {

    private RecyclerView recyclerView;
    private VocabAdapter vocabAdapter;
    private List<Vocab> vocabList;
    private View view;
    private FloatingActionButton floatingActionButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_words, container, false);

        recyclerView = view.findViewById(R.id.words_recycle_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        vocabList = getVocabList();
        vocabAdapter = new VocabAdapter(vocabList, this::onClickGoToDetail);
        recyclerView.setAdapter(vocabAdapter);

        ItemTouchHelper.SimpleCallback simpleCallback = new RecycleViewItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);

        floatingActionButton = view.findViewById(R.id.words_add_btn);
        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), CreationActivity.class);
            startActivityForResult(intent, 100);
        });

        return view;
    }

    private List<Vocab> getVocabList() {
        List<Vocab> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new Vocab("Name " + (i + 1)));
        }
        return list;
    }

    @Override
    public void onSwipe(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof VocabAdapter.VocabViewHolder) {
            String itemDel = vocabList.get(viewHolder.getAdapterPosition()).getWord();
            final Vocab vocab = vocabList.get(viewHolder.getAdapterPosition());
            final int idxDel = viewHolder.getAdapterPosition();

            vocabAdapter.removeItem(idxDel);
            Snackbar snackbar = Snackbar.make(view, "You just delete an item " + itemDel, Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", view -> {
                vocabAdapter.undoItem(vocab, idxDel);
            });
            snackbar.setAnchorView(view.findViewById(R.id.words_add_btn));
            snackbar.show();
        }
    }

    private void onClickGoToDetail(Vocab vocab) {
        Intent intent = new Intent(view.getContext(), DetailActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("data", vocab);
        intent.putExtras(bundle);

        startActivityForResult(intent, 110);
    }
}