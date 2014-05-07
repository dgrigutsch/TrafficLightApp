package de.edarling.net.app;


import android.app.Activity;
import android.util.Log;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.nicktate.projectile.Method;
import org.nicktate.projectile.Projectile;
import org.nicktate.projectile.StringListener;

public class AmpelData {

    PojoAmpelData data;
    Activity activity;
    OnDataListener onDataListener;

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
                .aim(activity.getString(de.edareling.net.ampeldarlingapp.app.R.string.static_url_host))
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
        Projectile.draw(activity).aim(activity.getString(de.edareling.net.ampeldarlingapp.app.R.string.static_url_host))
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

    interface OnDataListener {
        public void onResult(PojoAmpelData data);
    }


}
