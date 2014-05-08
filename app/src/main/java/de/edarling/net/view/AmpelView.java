package de.edarling.net.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import de.edarling.net.ampel.app.R;

public class AmpelView extends RelativeLayout implements View.OnClickListener{

    View red_signal;
    View yellow_signal;
    View green_signal;
    OnLightListener onLightListener;

    public static final String RED = "red";
    public static final String YELLOW = "yellow";
    public static final String GREEN = "green";
    public static final String ALL_ON = "on";
    public static final String ALL_OFF = "off";

    String name_id = "";
    String currentLightColor;
    boolean currentState;
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

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.TrafficLight,
                0, 0);

        try {
           name_id = a.getString(R.styleable.TrafficLight_light_name);
        } finally {
            a.recycle();
        }
        init();
    }

    @Override
    public void setEnabled(boolean enabled) {
//        Log.e("dsf",String.valueOf(enabled));
        super.setEnabled(enabled);
        if(viewsNotNull()){
            red_signal.setEnabled(enabled);
            yellow_signal.setEnabled(enabled);
            green_signal.setEnabled(enabled);
        }

    }

    public String getTrafficLightName(){
        return name_id;
    }

    public void setTrafficLightName(String name){
        name_id = name;
    }

    public boolean getCurrentState(){
        return currentState;
    }

    public String getCurrentLightColor(){
        return currentLightColor;
    }

    void init(){

        LayoutInflater layoutInflater = LayoutInflater.from(cx);
        View v = layoutInflater.inflate(R.layout.ampel_layout, this, true);

        red_signal = v.findViewById(R.id.signal_red);
        yellow_signal = v.findViewById(R.id.signal_yellow);
        green_signal = v.findViewById(R.id.signal_green);

        setAllOn();

        red_signal.setTag(new LightViewHolder(false));
        yellow_signal.setTag(new LightViewHolder(false));
        green_signal.setTag(new LightViewHolder(false));

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

    void changeAllStates(View v, boolean isOn){
        if(v.getTag() instanceof LightViewHolder) {
            LightViewHolder lightViewHolder = (LightViewHolder) v.getTag();
            lightViewHolder.state = isOn;
        }
    }

    boolean changeState(View v, int resColor){

        if(v.getTag() instanceof LightViewHolder) {
            LightViewHolder lightViewHolder = (LightViewHolder) v.getTag();
            return changeState(v,resColor, lightViewHolder.state ? false : true);
        }else {
            return false;
        }
    }

    boolean changeState(View v, int resColor, boolean on){

        if(v.getTag() instanceof LightViewHolder) {

            LightViewHolder lightViewHolder = (LightViewHolder) v.getTag();
            lightViewHolder.state = on;

            if(lightViewHolder.state){
                v.setBackgroundColor(getResources().getColor(resColor));
            }else {
                v.setBackgroundColor(getResources().getColor(R.color.black));
            }
            v.setTag(lightViewHolder);

            return lightViewHolder.state;
        }
        return false;
    }

    void setAllOn(){
        if(viewsNotNull()){
            green_signal.setBackgroundColor(getResources().getColor(R.color.green));
            yellow_signal.setBackgroundColor(getResources().getColor(R.color.yellow));
            red_signal.setBackgroundColor(getResources().getColor(R.color.red));
            changeAllStates(green_signal, true);
            changeAllStates(yellow_signal, true);
            changeAllStates(red_signal,true);

            currentLightColor = ALL_ON;
            currentState = true;

            if(onLightListener != null)
                onLightListener.onClicked(this);
        }

    }

    void setAllOff(){
        if(viewsNotNull()){
            green_signal.setBackgroundColor(getResources().getColor(R.color.black));
            yellow_signal.setBackgroundColor(getResources().getColor(R.color.black));
            red_signal.setBackgroundColor(getResources().getColor(R.color.black));
            changeAllStates(green_signal,false);
            changeAllStates(yellow_signal,false);
            changeAllStates(red_signal,false);

            currentLightColor = ALL_ON;
            currentState = false;

            if(onLightListener != null)
                onLightListener.onClicked(this);
        }
    }


    void setRed(){
        if(viewsNotNull()){
            currentLightColor = RED;
            currentState = changeState(red_signal,R.color.red);
            if(onLightListener != null)
                onLightListener.onClicked(this);
        }
    }
    public void setRed(boolean on){
        if(viewsNotNull()){
            currentLightColor = RED;
            currentState = changeState(red_signal,R.color.red,on);
        }
    }
    void setYellow(){
        currentLightColor = YELLOW;
        currentState = changeState(yellow_signal,R.color.yellow);
        if(onLightListener != null)
            onLightListener.onClicked(this);
    }
    public void setYellow(boolean on){
        currentLightColor = YELLOW;
        currentState = changeState(yellow_signal,R.color.yellow,on);
    }
    void setGreen(){
        if(viewsNotNull()){
            currentLightColor = GREEN;
            currentState = changeState(green_signal,R.color.green);
            if(onLightListener != null)
                onLightListener.onClicked(this);
        }
    }
    public void setGreen(boolean on){
        if(viewsNotNull()){
            currentLightColor = GREEN;
            currentState = changeState(green_signal,R.color.green,on);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.signal_red :
                setRed();
                break;
            case R.id.signal_yellow :
                setYellow();
                break;
            case R.id.signal_green :
                setGreen();
                break;
        }
    }

    public interface OnLightListener {
        public void onClicked(AmpelView view);
    }

    class LightViewHolder {
        public boolean state;
        public LightViewHolder(boolean state){
            this.state = state;
        }
    }

    class MainViewHolder {

    }
}