package soexample.umeng.com.jindong.Activity;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import soexample.umeng.com.jindong.ActivityPersenter.Sheupthepersenter;
import soexample.umeng.com.jindong.R;


public class SelectImagePopupWindow implements View.OnClickListener {
    private Context mContext = null;
    private PopupWindow popupWindow = null;
    private OnSelectPictureListener listener = null;
    public SelectImagePopupWindow(Context mContext, View parentView, OnSelectPictureListener listener) {
        this.mContext = mContext;
        this.listener = listener;
        View view = LayoutInflater.from(mContext).inflate(R.layout.popup_select_imgage, null);
        initView(view, parentView);
    }

    private void initView(View view, View parentView) {
        popupWindow = new PopupWindow(parentView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setContentView(view);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);//show()
        view.findViewById(R.id.popup_select_take_photo).setOnClickListener(this);
        view.findViewById(R.id.popup_select_take_picture).setOnClickListener(this);
        view.findViewById(R.id.popup_select_take_cancel).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.popup_select_take_photo) {
            listener.onTakePhoto();

        } else if (i == R.id.popup_select_take_picture) {
            listener.onSelectPicture();

        } else if (i == R.id.popup_select_take_cancel) {
            listener.onCancel();

        }
        popupWindow.dismiss();
    }

    public interface OnSelectPictureListener {
        /**
         * 拍摄
         */
        void onTakePhoto();

        /**
         * 从相册选择
         */
        void onSelectPicture();

        /**
         * 取消
         */
        void onCancel();
    }

}
