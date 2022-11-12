package michael.vocabflash.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;

import com.google.android.material.appbar.MaterialToolbar;

import michael.vocabflash.R;

public class CreationActivity extends AppCompatActivity {

    private MaterialToolbar materialToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation);

        materialToolbar = findViewById(R.id.activity_creation_menu);
        materialToolbar.setTitle("Hello");
        setSupportActionBar(materialToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.custom_menu, menu);
        return true;
    }
}