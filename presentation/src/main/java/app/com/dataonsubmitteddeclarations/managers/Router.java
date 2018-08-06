package app.com.dataonsubmitteddeclarations.managers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import org.jetbrains.annotations.NotNull;

import app.com.dataonsubmitteddeclarations.R;
import app.com.dataonsubmitteddeclarations.favorite.FavoriteFragment;
import app.com.dataonsubmitteddeclarations.pdf.PdfViewerFragment;
import app.com.dataonsubmitteddeclarations.search.FavoriteDialogFragment;
import app.com.dataonsubmitteddeclarations.search.SearchFragment;

public class Router {

    public static final int FAVORITE_REQUEST_CODE = 1139;

    private int getContainerId() {
        return R.id.main_container;
    }

    public void openSearchFragment(final FragmentManager fragmentManager) {
        final SearchFragment fragment = SearchFragment.newInstance();
        final FragmentTransaction transaction = common(fragmentManager, fragment);
        transaction.commit();
    }

    public void openFavoriteSearchFragment(final FragmentManager fragmentManager) {
        final FavoriteFragment fragment = FavoriteFragment.newInstance();
        final FragmentTransaction transaction = common(fragmentManager, fragment);
        transaction.addToBackStack(fragment.getClass().getSimpleName()).commit();
    }

    public void openPdfFragment(final RouterData routerData) {
        final PdfViewerFragment fragment = PdfViewerFragment
                .newInstance(routerData.getPersonModel().getLinkPdf());
        final String tag = fragment.getClass().getSimpleName();
        final FragmentTransaction transaction = common(routerData.getFragmentManager(), fragment);
        transaction.addToBackStack(tag).commit();
    }

    public void openFavoriteDialog(final RouterData routerData) {
        final FavoriteDialogFragment dialog =
                FavoriteDialogFragment.newInstance(routerData.getPersonModel());
        dialog.setTargetFragment(routerData.getFragment(), FAVORITE_REQUEST_CODE);
        dialog.show(routerData.getFragmentManager(), dialog.getClass().getSimpleName());
    }

    @NotNull
    private FragmentTransaction common(final FragmentManager fragmentManager, final Fragment fragment) {
        final String tag = fragment.getClass().getSimpleName();
        return fragmentManager
                .beginTransaction()
                .add(getContainerId(), fragment, tag);
    }
}
