package teo.ram.css.anagramanimations.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import teo.ram.css.anagramanimations.R;

/**
 * Created by css on 1/2/15.
 */
public class EndGameFragment extends android.support.v4.app.Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_endgame, null);
        ButterKnife.inject(this, view);

        return  view;
    }


}
