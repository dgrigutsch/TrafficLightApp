package de.edarling.net.app;


import android.app.Activity;
import android.util.Log;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.nicktate.projectile.Method;
import org.nicktate.projectile.Projectile;
import org.nicktate.projectile.StringListener;

import java.util.HashMap;
import java.util.Map;

import de.edarling.net.ampel.app.R;
import de.edarling.net.view.AmpelView;

public class AmpelData {

    PojoAmpelData data;
    Activity activity;
    OnDataListener onDataListener;


    public static final String TRAFFIC_LIGHT_L1 = "L1";
    public static final String TRAFFIC_LIGHT_L2 = "L2";
    public static final String TRAFFIC_LIGHT_L3 = "L3";

    public static final String TRAFFIC_LIGHT_RED = "R";
    public static final String TRAFFIC_LIGHT_YELLOW = "Y";
    public static final String TRAFFIC_LIGHT_GREEN = "G";

    public AmpelData(Activity activity){
        this(activity,null);
    }

    public AmpelData(Activity activity, OnDataListener listener){
        this.activity = activity;
        this.onDataListener = listener;
        initData();
    }

    public OnDataListener getOnDataListener() {
        return onDataListener;
    }

    public void setOnDataListener(OnDataListener onDataListener) {
        this.onDataListener = onDataListener;
    }

    public void setAmpelData(PojoAmpelData data){
        this.data = data;
    }

    public PojoAmpelData getAmpelData(PojoAmpelData data){
        return this.data;
    }

    void initData() {

        Projectile.useOkHttp(true);
        Projectile.draw(activity)
                .aim(activity.getString(R.string.static_url_host))
                .method(Method.GET)
                .fire(new StringListener() {
                    @Override
                    public void onResponse(String s) {
                        sendResult(s);
                    }

                    @Override
                    public void onError(VolleyError error) {
                        Log.e(MainActivity.class.getName(), "Error:", error);
                        sendResult(null);
                    }
                });
    }

    void sendResult(String data){

        PojoAmpelData newData = null;
        try {
            newData = new Gson().fromJson(data, PojoAmpelData.class);
        }finally {
            if(newData != null)
                this.data = newData;
        }

        if(onDataListener != null)
            onDataListener.onResult(newData);
    }


    void sendGateState(int state) {

        Projectile.useOkHttp(true);
        Projectile.draw(activity).aim(activity.getString(R.string.static_url_host))
                .method(Method.GET)
                .addParam("GP", String.valueOf(state))
                .fire(new StringListener() {
                    @Override
                    public void onResponse(String s) {
                        Log.e(MainActivity.class.getName(), "Response: " + s);

                        sendResult(s);
                    }

                    @Override
                    public void onError(VolleyError error) {
                        Log.e(MainActivity.class.getName(), "Error:", error);
                        sendResult(null);
                    }
                });
    }

    void sendTrafficLightState(String trafficLight,String color,boolean isOn){

        Log.e(AmpelData.class.getName(),
                String.format("L: %s, Color: %s, State: %b",trafficLight,color,isOn));

        Map<String,String> params = new HashMap<String, String>();

        if(color.equals(AmpelView.RED)){
            params.put(String.format("%s_R",trafficLight),String.format("%d",isOn?1:0));
        }
        else if(color.equals(AmpelView.YELLOW)){
            params.put(String.format("%s_Y",trafficLight),String.format("%d",isOn?1:0));
        }
        else if(color.equals(AmpelView.GREEN)){
            params.put(String.format("%s_G",trafficLight),String.format("%d",isOn?1:0));
        }
        else if(color.equals(AmpelView.ALL_OFF)){
            params.put(String.format("%s_R",trafficLight),String.format("%d",0));
            params.put(String.format("%s_Y",trafficLight),String.format("%d",0));
            params.put(String.format("%s_G",trafficLight),String.format("%d",0));
        }else if(color.equals(AmpelView.ALL_ON)){
            params.put(String.format("%s_R",trafficLight),String.format("%d",1));
            params.put(String.format("%s_Y",trafficLight),String.format("%d",1));
            params.put(String.format("%s_G",trafficLight),String.format("%d",1));
        }

        Log.e(AmpelData.class.getName(),"Params"+params.toString());

        Projectile.useOkHttp(true);
        Projectile.draw(activity).aim(activity.getString(R.string.static_url_host))
                .method(Method.GET)
                .addParams(params)
                .fire(new StringListener() {
                    @Override
                    public void onResponse(String s) {
                        Log.e(MainActivity.class.getName(), "Response: " + s);
                        sendResult(s);
                    }

                    @Override
                    public void onError(VolleyError error) {
                        Log.e(MainActivity.class.getName(), "Error:", error);
                        sendResult(null);
                    }
                });
    }

    interface OnDataListener {
        public void onResult(PojoAmpelData data);
    }
}
