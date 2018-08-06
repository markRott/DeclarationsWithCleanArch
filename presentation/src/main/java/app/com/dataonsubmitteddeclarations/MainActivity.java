package app.com.dataonsubmitteddeclarations;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import javax.inject.Inject;

import app.com.dataonsubmitteddeclarations.base.BackPressedContract;
import app.com.dataonsubmitteddeclarations.di.InjectHelper;
import app.com.dataonsubmitteddeclarations.managers.Router;

public class MainActivity extends AppCompatActivity {

    @Inject
    Router router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InjectHelper.getMainAppComponent().inject(this);
        if (savedInstanceState == null) {
            router.openSearchFragment(getSupportFragmentManager());
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_favorite:
                router.openFavoriteSearchFragment(getSupportFragmentManager());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
