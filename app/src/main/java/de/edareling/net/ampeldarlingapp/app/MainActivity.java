package de.edareling.net.ampeldarlingapp.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.devadvance.circularseekbar.CircularSeekBar;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        @InjectView(R.id.getData)
        Button mGetData;
        @InjectView(R.id.circularSeekBar1)
        CircularSeekBar mCircularSeekBar1;
        @InjectView(R.id.tv_seekBarGate)
        TextView mTvSeekBarGate;

        int state = 0;
        AmpelData ampelData;

        public PlaceholderFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            ButterKnife.inject(this, rootView);
            mCircularSeekBar1.setOnSeekBarChangeListener(onCircularSeekBarChangeListener);
            return rootView;
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            ampelData = new AmpelData(getActivity());
            ampelData.setOnDataListener(onDataListener);
        }

        AmpelData.OnDataListener onDataListener = new AmpelData.OnDataListener() {
            @Override
            public void onResult(PojoAmpelData data) {

                mCircularSeekBar1.setDraggingEnabled(true);
                mCircularSeekBar1.setProgress(100-data.getGATE().getP());
                mTvSeekBarGate.setText(String.format("%d", data.getGATE().getP()));
            }
        };


        CircularSeekBar.OnCircularSeekBarChangeListener onCircularSeekBarChangeListener = new CircularSeekBar.OnCircularSeekBarChangeListener() {

            @Override
            public void onProgressChanged(CircularSeekBar circularSeekBar, int progress, boolean fromUser) {

                state = 100-progress;
                mTvSeekBarGate.setText(String.format("%d", state));
            }

            @Override
            public void onStopTrackingTouch(CircularSeekBar seekBar) {
                mCircularSeekBar1.setDraggingEnabled(false);
                ampelData.sendGateState(state);
            }

            @Override
            public void onStartTrackingTouch(CircularSeekBar seekBar) {

            }
        };
    }

}
