package cwoj.tk.cwoj;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import util.OnItemClickListener;
import util.cardColorSelector;

public class SettingsActivity extends AppCompatActivity {

    private RecyclerView setting;
    private ArrayList<String> Data;
    private SettingsAdapter adapter;
    private Animation slideIn;
    private cardColorSelector colorSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        slideIn = AnimationUtils.loadAnimation(this, R.anim.slide_in);

        colorSelector = new cardColorSelector();

        Data = getData();

        adapter = new SettingsAdapter();
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position){
                    case 0:
                        Toast.makeText(SettingsActivity.this,
                                "这个就是开发者的github了",
                                Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SettingsActivity.this,
                                GithubActivity.class));
                        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                        break;
                    case 1:
                        startActivity(new Intent(SettingsActivity.this,
                                MainActivity.class));
                        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                        break;
                    case 2:
                        Toast.makeText(SettingsActivity.this,
                                "本功能可能要在很久以后才能开放，慢慢等吧",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        startActivity(new Intent(SettingsActivity.this,
                                AboutMeActivity.class));
                        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                        break;
                    case 4:
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://18312847646.github.io")));
                        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                        break;
                    case 5:
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://www.comet-studio.cn")));
                        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                        break;
                    case 6:
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://space.bilibili.com/8309713#!/index")));
                        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                        break;
                    case 7:
                        if(!joinQQGroup("1xAz-QGQL0FrWLWvBz_a5yE6aIv_64et")){
                            Toast.makeText(SettingsActivity.this,
                                    "抱歉，您未安装手机QQ，或安装的版本不支持，请升级。",
                                    Toast.LENGTH_SHORT).show();
                        }
                        break;
                    default:
                        break;
                }
            }

            // 还没想好
            @Override
            public void onItemLongClick(View view, int position) {
                Log.d(this.toString(), "onLongClick called.");
                switch (position){
                    default:
                        break;
                }
            }

            // 按钮反馈颜色变化，一开始我还以为是直接给ID。。
            // 后来又看到这个方法被标记过时、、内心是崩溃的
            @TargetApi(Build.VERSION_CODES.M)
            @SuppressWarnings("unchecked")
            @Override
            public void onItemTouch(View view, int position, MotionEvent event) {
//                switch (position){
//                    default:
//                        break;
//                }
                // 获取当前颜色，并匹配出按钮反馈色
                Log.d(this.toString(), "MotionEvent event = " + event.getAction());
                // 2 是按下去的状态
                switch (event.getAction()){
                    case 2:
                        ((CardView) view).setCardBackgroundColor(
                                getResources().getColor(colorSelector.getColorByIndex(position).getColorPressed()));
                        break;
                    default:
                        ((CardView) view).setCardBackgroundColor(
                                getResources().getColor(colorSelector.getColorByIndex(position).getColorNormal()));
                        break;
                }
            }
        });

        setting = (RecyclerView) findViewById(R.id.setting);
        setting.setLayoutManager(new LinearLayoutManager(this));
        setting.setItemAnimator(new DefaultItemAnimator());
        setting.setAdapter(adapter);
    }

    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        data.add("查看APP源代码");
        data.add("回到OJ界面");
        data.add("离线评测");
        data.add("关于开发者");
        data.add("推广：老大の博客");
        data.add("推广：工作室网站");
        data.add("推广：无脑科学");
        data.add("推广：编程社区");
        return data;
    }

    class SettingsAdapter extends Adapter{

        private OnItemClickListener onItemClickListener;

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new SettingsViewHolder(LayoutInflater.from(SettingsActivity.this)
                    .inflate(R.layout.setting_unit, parent, false));
        }
        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            ((SettingsViewHolder)holder).textView.setText(Data.get(position));
            ((SettingsViewHolder)holder).cardView.setCardBackgroundColor(
                    colorSelector.getColorByIndex(position).getColorNormal());

            // 如果设置了回调，则设置点击事件
            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getLayoutPosition();
                        onItemClickListener.onItemClick(holder.itemView, pos);
                    }
                });
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int pos = holder.getLayoutPosition();
                        onItemClickListener.onItemLongClick(holder.itemView, pos);
                        return false;
                    }
                });
                holder.itemView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        int pos = holder.getLayoutPosition();
                        onItemClickListener.onItemTouch(holder.itemView, pos, event);
                        return false;
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return Data.size();
        }

        class SettingsViewHolder extends RecyclerView.ViewHolder {
            CardView cardView;
            TextView textView;
            public SettingsViewHolder(View itemView) {
                super(itemView);
                cardView = (CardView) itemView.findViewById(R.id.cardUnit);
                textView = (TextView) itemView.findViewById(R.id.cardChild);
            }
        }

    }

    /**
     * 发起添加群流程。群号：ProgramLeague(319293196) 的 key 为：
     * 1xAz-QGQL0FrWLWvBz_a5yE6aIv_64et
     * 调用 joinQQGroup(1xAz-QGQL0FrWLWvBz_a5yE6aIv_64et)
     * 即可发起手Q客户端申请加群 ProgramLeague(319293196)
     * @param key 由官网生成的key
     * @return 返回true表示呼起手Q成功，返回false表示呼起失败
     */
    private boolean joinQQGroup(String key) {

        Intent intent = new Intent();
        intent.setData(Uri.parse(
                "mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com"+
                        "%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key));
        // 此Flag可根据具体产品需要自定义，如设置，则在加群界面按返回，
        // 返回手Q主界面，不设置，按返回会返回到呼起产品界面
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(intent);
            overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
            return true;
        } catch (Exception e) {
            // 未安装手Q或安装的版本不支持
            return false;
        }
    }
}
