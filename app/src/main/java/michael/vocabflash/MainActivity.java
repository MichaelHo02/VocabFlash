package michael.vocabflash;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;

import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NavigationBarView navigationBarView;
    private WordsFragment wordsFragment = new WordsFragment();
    private LearningFragment learningFragment = new LearningFragment();
    private LibraryFragment libraryFragment = new LibraryFragment();

    private RecyclerView recyclerViewVocab;
    private VocabAdapter vocabAdapter;
    private List<Vocab> vocabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, wordsFragment).commit();
        navigationBarView = findViewById(R.id.bottom_navigation);
        navigationBarView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.words:
                    changeFragment(R.id.container, wordsFragment);
                    return true;
                case R.id.learning:
                    changeFragment(R.id.container, learningFragment);
                    return true;
                case R.id.library:
                    changeFragment(R.id.container, libraryFragment);
                    return true;
            }
            return false;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.custom_menu, menu);
        return true;
    }

    private void changeFragment(@IdRes int containerViewId, @NonNull androidx.fragment.app.Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(containerViewId, fragment).commit();
    }
}