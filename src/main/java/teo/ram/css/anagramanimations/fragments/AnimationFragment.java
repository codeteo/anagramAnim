package teo.ram.css.anagramanimations.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;
import de.greenrobot.event.EventBus;
import teo.ram.css.anagramanimations.R;
import teo.ram.css.anagramanimations.bus.MoveToFragmentEvent;

import static teo.ram.css.anagramanimations.utils.Utils.generateViewId;

/**
 * Created by css on 12/26/14.
 */
public class AnimationFragment extends android.support.v4.app.Fragment {
    @InjectView(R.id.textviewAnimFragmentID) TextView textView;
    @Optional
    @InjectView(R.id.toolbar) android.support.v7.widget.Toolbar toolbar;
    @InjectView(R.id.container_textdrawableID) LinearLayout textdrawableContainer;


    @InjectView(R.id.move_fragment_btn) Button button;

    private static final String BUNDLE_STRING = "BUNDLE_STRING";


    public static AnimationFragment newInstance(String title) {
        AnimationFragment animationFragment = new AnimationFragment();
        Bundle args = new Bundle();
        args.putString(BUNDLE_STRING, title);
        animationFragment.setArguments(args);
        return animationFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_animationfragment, null);
        ButterKnife.inject(this, view);

        ( (ActionBarActivity) getActivity() ).setSupportActionBar(toolbar);        //  set Toolbar
        toolbar.setTitle("AnimationFragment");

        Bundle bundle = getArguments();
        if(bundle != null){
            String detail = bundle.getString(BUNDLE_STRING, "Default Value");
            textView.setText(detail);
            textView.animate().setStartDelay(1000).setDuration(2000).alpha(0);
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        for (int i = 1; i < 5; i++) {
            final ImageView imageView = new ImageView(getActivity());
            TextDrawable drawable = TextDrawable.builder()
                    .buildRect("A", Color.RED);
            imageView.setImageDrawable(drawable);

            final int id = generateViewId();
            imageView.setId(id);
            imageView.setVisibility(View.INVISIBLE);

            final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dpToPx(60),
                    dpToPx(60));
            layoutParams.setMargins(0, 0, 5, 0);
            textdrawableContainer.addView(imageView,layoutParams);


            float endValue = (i-1) * 88f;       // first view animates to x=0f, second to x=88f
            ValueAnimator translateAnim = ObjectAnimator.ofFloat(imageView, "x", -88f, endValue);
            translateAnim.setDuration(500);
            translateAnim.setStartDelay((i-1)*500);
            final int finalI = i-1;
            translateAnim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    Log.i("AnimationListener", "onAnimationEND  finalI=== " + finalI );
                    Log.i("AnimationListener", "BEFORE  XXXX ===== " + imageView.getX() );
                    textdrawableContainer.removeView(imageView);
                    textdrawableContainer.addView(imageView, finalI, layoutParams);          //add View to container
                    Log.i("AnimationListener", "AFTER XXXX ===== " + imageView.getX() );
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    imageView.setVisibility(View.VISIBLE);
                }
            });
            translateAnim.start();

        }

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MoveToFragmentEvent(new EndGameFragment()));
            }
        });

    }


    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }


}
