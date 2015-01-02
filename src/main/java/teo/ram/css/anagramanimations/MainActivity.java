package teo.ram.css.anagramanimations;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;
import de.greenrobot.event.EventBus;
import teo.ram.css.anagramanimations.bus.MoveToFragmentEvent;
import teo.ram.css.anagramanimations.fragments.EndGameFragment;
import teo.ram.css.anagramanimations.fragments.MainListFragment;


public class MainActivity extends ActionBarActivity {
    @InjectView(R.id.container) FrameLayout frameLayoutContainer;

    private static final String[] items = { "animations 1", "animations 2", "animations 3" };
    private int time=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);


        if (savedInstanceState == null) {
            ArrayList<String> arry=new ArrayList<String>();
            arry.add("First");
            arry.add("Second");
            arry.add("Third");
            MainListFragment listFragment = MainListFragment.newInstance(arry);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, listFragment)
//                    .addToBackStack(null)
                    .commit();
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }


    public void onEvent(MoveToFragmentEvent e) {
        if (e.getFragment() instanceof EndGameFragment) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, e.getFragment())
                    .commit();
        }
    }





    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends android.support.v4.app.Fragment {
        @InjectView(R.id.textViewID) TextView textView;
        @Optional @InjectView(R.id.toolbar) android.support.v7.widget.Toolbar toolbar;


        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            ButterKnife.inject(this, rootView);

            ((ActionBarActivity)getActivity()).setSupportActionBar(toolbar);        //  set Toolbar

            textView.setText("Dynamically Added Text");
            textView.setTextSize(22);
            return rootView;
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            ButterKnife.reset(this);
        }


    }//Fragment Ends


}
