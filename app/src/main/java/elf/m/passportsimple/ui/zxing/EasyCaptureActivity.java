/*
 * Copyright (C) 2018 Jenly Yu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package elf.m.passportsimple.ui.zxing;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.king.zxing.CaptureActivity;
import com.king.zxing.DecodeFormatManager;
import elf.m.passportsimple.R;
import elf.m.passportsimple.ui.utils.StatusBarUtils;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class EasyCaptureActivity extends CaptureActivity {
    public static final String KEY_TITLE = "key_title";
    public static final String KEY_IS_QR_CODE = "key_code";
    public static final String KEY_IS_CONTINUOUS = "key_continuous_scan";

    @Override
    public int getLayoutId() {
        return R.layout.easy_capture_activity;
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Toolbar toolbar = findViewById(R.id.toolbar);
        StatusBarUtils.immersiveStatusBar(this,toolbar,0.2f);
     /*   TextView tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText(getIntent().getStringExtra(KEY_TITLE));*/
        getCaptureHelper()
                .decodeFormats(DecodeFormatManager.QR_CODE_FORMATS)//设置只识别二维码会提升速度
                .playBeep(true)
                .vibrate(true);
    }

    public void onClick(View v){
        switch (v.getId()){
           /* case R.id.ivLeft:
                onBackPressed();
                break;*/
        }
    }

    @Override
    public boolean onResultCallback(String result) {
        Log.d("liuzuo99","result="+result);
        return super.onResultCallback(result);
    }
}
