package teo.ram.css.anagramanimations.fragments;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;
import teo.ram.css.anagramanimations.R;

/**
 * Created by css on 12/25/14.
 */
public class MainListFragment extends android.support.v4.app.ListFragment {
    @InjectView(R.id.textViewID) TextView textView;
    @Optional @InjectView(R.id.toolbar) android.support.v7.widget.Toolbar toolbar;

    private static final String BUNDLE_TAG_ITEMS = "BUNDLE_TAG_ITEMS";


    public static MainListFragment newInstance(ArrayList<String> items) {
        MainListFragment mainListFragment = new MainListFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(BUNDLE_TAG_ITEMS, items);
        mainListFragment.setArguments(args);
        return mainListFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_list, container, false);
        ButterKnife.inject(this, rootView);

        ( (ActionBarActivity) getActivity() ).setSupportActionBar(toolbar);        //  set Toolbar

        textView.setText("Dynamically Added Text");
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView.setTextSize(22);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<String> items = new ArrayList<String>();
        items = getArguments().getStringArrayList(BUNDLE_TAG_ITEMS);
        setListAdapter(new ArrayAdapter<String>( getActivity(), android.R.layout.simple_list_item_1, items ));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

//        Toast.makeText(getActivity(), "CLICKED ZERO", Toast.LENGTH_LONG).show();

        switch (position) {
            case 0:
                String title = "AnimationFragment Title";
                AnimationFragment animationFragment = AnimationFragment.newInstance(title);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, animationFragment)
                        .addToBackStack(null)
                        .commit();
            case 1:

        }

    }



}
