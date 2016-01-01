package cwoj.tk.cwoj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

//    private Toolbar toolbar;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
        webView = (WebView) findViewById(R.id.webView);
//        setSupportActionBar(toolbar);

        webView.loadUrl("http://www.cwoj.tk");
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
//        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab1);
//        fab1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        }
        return false;
    }

    public void settings(View view){
        startActivity(new Intent(this, SettingsActivity.class));
        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
    }

}
