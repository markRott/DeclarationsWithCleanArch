package app.com.dataonsubmitteddeclarations.managers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import app.com.domain.models.PersonModel;

public final class RouterData {

    private Fragment fragment;
    private PersonModel personModel;
    private FragmentManager fragmentManager;

    public Fragment getFragment() {
        return fragment;
    }

    public PersonModel getPersonModel() {
        return personModel;
    }

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public static final class RouterDataBuilder {
        private Fragment fragment;
        private PersonModel personModel;
        private FragmentManager fragmentManager;

        private RouterDataBuilder() {
        }

        public static RouterDataBuilder builder() {
            return new RouterDataBuilder();
        }

        public RouterDataBuilder setFragment(Fragment fragment) {
            this.fragment = fragment;
            return this;
        }

        public RouterDataBuilder setPersonModel(PersonModel personModel) {
            this.personModel = personModel;
            return this;
        }

        public RouterDataBuilder setFragmentManager(FragmentManager fragmentManager) {
            this.fragmentManager = fragmentManager;
            return this;
        }

        public RouterData build() {
            RouterData routerData = new RouterData();
            routerData.personModel = this.personModel;
            routerData.fragmentManager = this.fragmentManager;
            routerData.fragment = this.fragment;
            return routerData;
        }
    }
}
