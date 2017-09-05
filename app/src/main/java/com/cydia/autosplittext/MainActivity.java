package com.cydia.autosplittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cydia.autosplittext.view.AutoSplitTextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mAdjustTextBtn;

    private AutoSplitTextView mDisplayTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        mAdjustTextBtn = (Button) findViewById(R.id.btn_adjust_text);
        mAdjustTextBtn.setOnClickListener(this);
        mDisplayTv = (AutoSplitTextView) findViewById(R.id.tv_display);
        mDisplayTv.setEnabled(true);
        mDisplayTv.setText(R.string.text);
        mAdjustTextBtn.setText("Set Enabled false");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_adjust_text:
                mDisplayTv.setText("");
                boolean enabled = mDisplayTv.isEnabled();
                mDisplayTv.setEnabled(!enabled);
                mDisplayTv.setText(R.string.text);
                mAdjustTextBtn.setText("Set Enabled " + (enabled ? "true" : "false"));
                break;
            default:
        }
    }
}
