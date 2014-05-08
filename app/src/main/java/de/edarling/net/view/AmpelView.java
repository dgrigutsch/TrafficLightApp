package de.edarling.net.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import de.edarling.net.ampel.app.R;

public class AmpelView extends RelativeLayout implements View.OnClickListener{

    int paddingHorizontal;
    int paddingVertical;
    View red_signal;
    View yellow_signal;
    View green_signal;

    OnLightListener onLightListener;

    public static final String RED = "red";
    public static final String YELLOW = "yellow";
    public static final String GREEN = "green";
    public static final String ALL_ON = "on";
    public static final String ALL_OFF = "off";

    String currentLightColor = "";
    Context cx;

    public AmpelView(Context context) {
        this(context,null);
    }

    public AmpelView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public AmpelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        cx = context;
        init();
        setAllOn();
    }

    void init(){

        LayoutInflater layoutInflater = LayoutInflater.from(cx);
        View v = layoutInflater.inflate(R.layout.ampel_layout, this, true);
        paddingHorizontal = 0;
        paddingVertical = 0;

        red_signal = v.findViewById(R.id.signal_red);
        yellow_signal = v.findViewById(R.id.signal_yellow);
        green_signal = v.findViewById(R.id.signal_green);

        red_signal.setOnClickListener(this);
        yellow_signal.setOnClickListener(this);
        green_signal.setOnClickListener(this);
    }

    public OnLightListener getOnLightListener() {
        return onLightListener;
    }

    public void setOnLightListener(OnLightListener onLightListener) {
        this.onLightListener = onLightListener;
    }


    boolean viewsNotNull(){
        if(red_signal == null)
            return false;
        if(yellow_signal == null)
            return false;
        if(green_signal == null)
            return false;

        return true;

    }

    void setAllOn(){
        if(viewsNotNull()){
            green_signal.setBackgroundColor(getResources().getColor(R.color.green));
            yellow_signal.setBackgroundColor(getResources().getColor(R.color.yellow));
            red_signal.setBackgroundColor(getResources().getColor(R.color.red));
        }
        if(onLightListener != null)
            onLightListener.onClicked(ALL_ON);

        currentLightColor = ALL_ON;
    }

    void setAllOff(){
        if(viewsNotNull()){
            green_signal.setBackgroundColor(getResources().getColor(R.color.black));
            yellow_signal.setBackgroundColor(getResources().getColor(R.color.black));
            red_signal.setBackgroundColor(getResources().getColor(R.color.black));
        }
        if(onLightListener != null)
            onLightListener.onClicked(ALL_OFF);

        currentLightColor = ALL_OFF;
    }

    void setRed(){
        if(viewsNotNull()){
            green_signal.setBackgroundColor(getResources().getColor(R.color.black));
            yellow_signal.setBackgroundColor(getResources().getColor(R.color.black));
            red_signal.setBackgroundColor(getResources().getColor(R.color.red));
        }
        if(onLightListener != null)
            onLightListener.onClicked(RED);

        currentLightColor = RED;
    }
    void setYellow(){
        if(viewsNotNull()){
            green_signal.setBackgroundColor(getResources().getColor(R.color.black));
            yellow_signal.setBackgroundColor(getResources().getColor(R.color.yellow));
            red_signal.setBackgroundColor(getResources().getColor(R.color.black));
        }
        if(onLightListener != null)
            onLightListener.onClicked(YELLOW);

        currentLightColor = YELLOW;
    }
    void setGreen(){
        if(viewsNotNull()){
            green_signal.setBackgroundColor(getResources().getColor(R.color.green));
            yellow_signal.setBackgroundColor(getResources().getColor(R.color.black));
            red_signal.setBackgroundColor(getResources().getColor(R.color.black));
        }
        if(onLightListener != null)
            onLightListener.onClicked(GREEN);

        currentLightColor = GREEN;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.signal_red :

                break;
            case R.id.signal_yellow :

                break;
            case R.id.signal_green :

                break;
        }
    }


    public interface OnLightListener {
        public void onClicked(String color);
    }
}