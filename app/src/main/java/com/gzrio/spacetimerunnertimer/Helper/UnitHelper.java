package com.gzrio.spacetimerunnertimer.Helper;

import android.content.Context;
import android.util.TypedValue;

public class UnitHelper {
    Context context;

    public UnitHelper(Context context) {
        this.context = context;
    }

    public float DPToPixel(float dpAmount) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpAmount, context.getResources().getDisplayMetrics());
    }
}
