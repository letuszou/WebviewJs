package top.letuszou.w;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView wv_test;
    private Button btn_test;
    private TextView logTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wv_test = (WebView) findViewById(R.id.wv_test);
        btn_test = (Button)findViewById(R.id.btn_test);
        logTextView = (TextView) findViewById(R.id.logTextView);
        getData();
    }


    private void getData(){
        // 设置支持javascript
        wv_test.getSettings().setJavaScriptEnabled(true);
        //启动缓存
        wv_test.getSettings().setAppCacheEnabled(true);
        //设置缓存模式
        wv_test.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        Log.e("error"," wv_test.loadUrl(file:///android_asset/index.html)");
        //加载网页
        wv_test.loadUrl("file:///android_asset/index.html");
        wv_test.addJavascriptInterface(this, "test");
        btn_test.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                wv_test.loadUrl("javascript:funFromjs()");
                Toast.makeText(MainActivity.this, "调用javascript:funFromjs()", Toast.LENGTH_LONG).show();
            }
        });

    }

    @android.webkit.JavascriptInterface
    public void actionFromJs() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "js调用了Native函数", Toast.LENGTH_SHORT).show();
                String text = logTextView.getText() + "\njs调用了Native函数";
                logTextView.setText(text);
            }
        });
    }

    @android.webkit.JavascriptInterface
    public void actionFromJsWithParam(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "js调用了Native函数传递参数：" + str, Toast.LENGTH_SHORT).show();
                String text = logTextView.getText() + "\njs调用了Native函数传递参数：" + str;
                logTextView.setText(text);
            }
        });

    }
}