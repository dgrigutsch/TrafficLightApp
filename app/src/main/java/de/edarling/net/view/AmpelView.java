package de.edarling.net.view;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.View;

import de.edareling.net.ampeldarlingapp.app.R;


public class AmpelView extends View {

    public AmpelView(Context context) {
        this(context, null);
    }

    public AmpelView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public AmpelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPurgeable = true;

//        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_ampel);
//        if (bm != null) {
            setBackgroundResource(R.drawable.ic_ampel);
//            setBackgroundDrawable(new BitmapDrawable(bm));
//        }
    }
}
