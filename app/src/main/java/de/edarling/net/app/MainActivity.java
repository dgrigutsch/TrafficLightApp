package de.edarling.net.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devadvance.circularseekbar.CircularSeekBar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.edarling.net.ampel.app.R;
import de.edarling.net.view.AmpelView;


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

        @InjectView(R.id.circularSeekBar1)
        CircularSeekBar mCircularSeekBar1;
        @InjectView(R.id.tv_seekBarGate)
        TextView mTvSeekBarGate;
        @InjectView(R.id.ampel_one)
        AmpelView mAmpelOne;
        @InjectView(R.id.ampel_two)
        AmpelView mAmpelTwo;
        @InjectView(R.id.ampel_three)
        AmpelView mAmpelThree;
        @InjectView(R.id.ampel_four)
        AmpelView mAmpelFour;


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
            mAmpelOne.setOnLightListener(onLightListener);
            mAmpelTwo.setOnLightListener(onLightListener);
            mAmpelThree.setOnLightListener(onLightListener);
            mAmpelFour.setOnLightListener(onLightListener);
        }

        AmpelView.OnLightListener onLightListener = new AmpelView.OnLightListener() {
            @Override
            public void onClicked(AmpelView view) {
                Log.e(MainActivity.class.getName(),"Light "+ view.getCurrentLightColor());
                view.setEnabled(false);
                ampelData.sendTrafficLightState(
                        view.getTrafficLightName(),
                        view.getCurrentLightColor(),
                        view.getCurrentState()
                );
            }
        };

        AmpelData.OnDataListener onDataListener = new AmpelData.OnDataListener() {
            @Override
            public void onResult(PojoAmpelData data) {

                if(mAmpelOne!=null){
                    mAmpelOne.setRed(data.getL1().getR()==0?false:true);
                    mAmpelOne.setYellow(data.getL1().getY()==0?false:true);
                    mAmpelOne.setGreen(data.getL1().getG()==0?false:true);
                    mAmpelOne.setEnabled(true);
                }

                if(mAmpelTwo!=null){
                    mAmpelTwo.setRed(data.getL2().getR()==0?false:true);
                    mAmpelTwo.setYellow(data.getL2().getY()==0?false:true);
                    mAmpelTwo.setGreen(data.getL2().getG()==0?false:true);
                    mAmpelTwo.setEnabled(true);
                }

                if(mAmpelThree!=null){
                    mAmpelThree.setRed(data.getL3().getR()==0?false:true);
                    mAmpelThree.setYellow(data.getL3().getY()==0?false:true);
                    mAmpelThree.setGreen(data.getL3().getG()==0?false:true);
                    mAmpelThree.setEnabled(true);
                }

                if(mAmpelFour!=null){
                    mAmpelFour.setRed(data.getL4().getR()==0?false:true);
                    mAmpelFour.setYellow(data.getL4().getY()==0?false:true);
                    mAmpelFour.setGreen(data.getL4().getG()==0?false:true);
                    mAmpelFour.setEnabled(true);
                }

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
