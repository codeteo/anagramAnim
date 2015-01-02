package teo.ram.css.anagramanimations.bus;

import android.support.v4.app.Fragment;

/**
 * Created by css on 1/2/15.
 */
public class MoveToFragmentEvent {
    private android.support.v4.app.Fragment  fragment;

    public MoveToFragmentEvent(Fragment fragment) {
        this.fragment = fragment;
    }

    public Fragment getFragment() {
        return fragment;
    }

}
