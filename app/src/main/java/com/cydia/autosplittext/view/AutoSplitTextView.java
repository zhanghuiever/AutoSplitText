package com.cydia.autosplittext.view;

import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by huizhangh on 2017/9/5.
 */

public class AutoSplitTextView extends TextView {

    private boolean mEnabled = true;

    public AutoSplitTextView(Context context) {
        super(context);
    }

    public AutoSplitTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoSplitTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setEnabled(boolean enabled) {
        this.mEnabled = enabled;
    }

    public boolean isEnabled() {
        return mEnabled;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY
                && getMeasuredWidth() > 0
                && getMeasuredWidth() >0
                && mEnabled){
            String newText = autoSplitText(this);
            if(!TextUtils.isEmpty(newText)){
                setText(newText);
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private String autoSplitText(TextView tv){
        String originText = tv.getText().toString();//原始文本
        Paint paint = tv.getPaint();//paint，方便获取文本长度
        float rowWidth = tv.getMeasuredWidth() - tv.getPaddingLeft() - tv.getPaddingRight();//一行文字最大的宽度

        String[] textLines = originText.replaceAll("\\r", "").split("\\n");//将文本按照段落分组
        StringBuilder retText = new StringBuilder();
        for(String line : textLines){
            if(paint.measureText(line) <= rowWidth){
                //当前段落长度小于行宽
                retText.append(line);
            } else {
                float curWidth = 0;
                for(int i = 0; i < line.length(); i++){
                    char ch = line.charAt(i);
                    curWidth += paint.measureText(String.valueOf(ch));
                    if(curWidth <= rowWidth){
                        retText.append(ch);
                    } else {
                        retText.append("\n");
                        curWidth = 0;
                        --i;
                    }
                }
            }
            retText.append("\n");
        }

        if(!originText.endsWith("\n")){
            retText.deleteCharAt(retText.length() -1);
        }
        return retText.toString();
    }
}
