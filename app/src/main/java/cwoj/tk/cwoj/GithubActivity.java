package cwoj.tk.cwoj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GithubActivity extends AppCompatActivity {

    private WebView github;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github);

        github = (WebView) findViewById(R.id.github);

        github.loadUrl("https://github.com/ice1000/CWOJ");
        github.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        github.getSettings().setJavaScriptEnabled(true);
        github.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);return true;
            }});
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && github.canGoBack()) {
            github.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        }
        return false;
    }

    public void settings(View view){
        startActivity(new Intent(this, SettingsActivity.class));
        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
    }

}
