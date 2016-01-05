package cwoj.tk.cwoj;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toFloat();

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);

                finish();
            }
        }.start();
    }

    private void toFloat(){
        ImageView NC = (ImageView) findViewById(R.id.ncFloat);
        NC.setBackgroundResource(R.drawable.nc_floating);

        AnimationDrawable NCFloat = (AnimationDrawable) NC.getBackground();
        NCFloat.start();
    }
}
