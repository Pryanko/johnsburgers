package com.examle.libgo.johnsburgers.tools;

import android.content.Context;
import com.examle.libgo.johnsburgers.R;
import com.stfalcon.frescoimageviewer.ImageViewer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author libgo (31.12.2017)
 */

public class SoftImageViewer {

    public static void setImageView(Context context, String s, int position){

        List<String> hashMap = new ArrayList<>();
        hashMap.add(s);

        ImageViewer.Builder imageViewer = new ImageViewer.Builder(context, hashMap)
                .setContainerPaddingPx(16,0,16,0)
                .setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryTransparent))
                .setStartPosition(position)
                .hideStatusBar(false)
                .allowZooming(true)
                .allowSwipeToDismiss(true);
        imageViewer.show();
    }

}
