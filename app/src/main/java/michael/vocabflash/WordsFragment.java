package michael.vocabflash;

import android.graphics.Insets;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.divider.MaterialDividerItemDecoration;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.snackbar.SnackbarContentLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WordsFragment extends Fragment implements ItemTouchHelperListener {

    private RecyclerView recyclerView;
    private VocabAdapter vocabAdapter;
    private List<Vocab> vocabList;
    private View view;

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
        vocabAdapter = new VocabAdapter(vocabList);
        recyclerView.setAdapter(vocabAdapter);

        MaterialDividerItemDecoration decoration = new MaterialDividerItemDecoration(view.getContext(), MaterialDividerItemDecoration.VERTICAL);
        decoration.setDividerInsetStart(16);
        decoration.setDividerInsetEnd(16);
        decoration.setLastItemDecorated(false);

//        recyclerView.addItemDecoration(decoration);

        ItemTouchHelper.SimpleCallback simpleCallback = new RecycleViewItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);

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
            snackbar.setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE);
            snackbar.setAnchorView(view.getRootView().findViewById(R.id.bottom_navigation));
            snackbar.show();
        }
    }
}