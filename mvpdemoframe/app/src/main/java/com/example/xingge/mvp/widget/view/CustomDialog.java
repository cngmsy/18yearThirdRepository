package com.example.xingge.mvp.widget.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;

import com.example.xingge.mvp.R;

/**
 * Created by xingge on 2017/7/26.
 */

public class CustomDialog extends Dialog {

    private static CustomDialog dialog;
    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    public CustomDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    public static CustomDialog show(Context context){
        dialog = new CustomDialog(context, R.style.CustomDialog);
        dialog.setContentView(R.layout.customdialog);
        AnimationDrawable anim = (AnimationDrawable) dialog.findViewById(R.id.loaddingImg).getBackground();
        anim.start();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        dialog.show();
        return dialog;
    }

    public static void dimiss(){
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }
}
