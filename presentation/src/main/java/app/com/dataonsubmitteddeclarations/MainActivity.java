package app.com.dataonsubmitteddeclarations;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import app.com.dataonsubmitteddeclarations.search.SearchFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openSearchFragment();
    }

    private void openSearchFragment() {
        final String tag = SearchFragment.class.getSimpleName();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, SearchFragment.newInstance(), tag)
                .commit();
    }
}
