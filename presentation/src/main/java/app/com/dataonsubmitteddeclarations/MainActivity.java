package app.com.dataonsubmitteddeclarations;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import app.com.dataonsubmitteddeclarations.base.BackPressedContract;
import app.com.dataonsubmitteddeclarations.search.SearchFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            openSearchFragment(SearchFragment.newInstance());
        }
    }

    public void openSearchFragment(Fragment fragment) {
        final String tag = fragment.getClass().getSimpleName();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_container, fragment, tag)
                .commit();
    }

    public void openPdfFragment(Fragment fragment) {
        final String tag = fragment.getClass().getSimpleName();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_container, fragment, tag)
                .addToBackStack(tag)
                .commit();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_container);
        if (!(fragment instanceof BackPressedContract)) {
            super.onBackPressed();
        } else {
            ((BackPressedContract) fragment).onBackPressed();
        }
    }
}
