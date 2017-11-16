package tk.arnoldwho.arecorder;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.support.v4.app.NotificationCompat;
import java.io.File;
import java.lang.reflect.Method;

/**
 * Created by arnold on 2017/11/10.
 */

public class MainActivity extends Activity{

        private int id = 1;
        public static final int REQUEST_CODE = 1;
        public MediaProjectionManager mMediaProjectionManager;
        public ScreenRecorder Recorder;
        public Button startButton;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            startButton = (Button) findViewById(R.id.button);
            mMediaProjectionManager = (MediaProjectionManager) getSystemService(MEDIA_PROJECTION_SERVICE);
        }

      public void startRecorder(View view){
        if (Recorder != null) {
            Recorder.quit();
            Recorder = null;
        }
        else {
            moveTaskToBack(true);
            startActivity(new Intent("StartRecorder"));
        }
    }

        NotificationManager mNotificationManager;


    public void notification(View view) {
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.tile_enabled);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("这是标题");
        builder.setContentText("这是正文");
        builder.setSmallIcon(R.drawable.tile_enabled);
        builder.setLargeIcon(bitmap);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setAutoCancel(false);

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        String[] events = {"第一", "第二", "第三", "第四", "第五", "第六", "第七"};
        inboxStyle.setBigContentTitle("展开后的标题");
        inboxStyle.setSummaryText("这是摘要");
        for (String event : events) {
            inboxStyle.addLine(event);
        }
        builder.setStyle(inboxStyle);

        Intent intent = new Intent(this, ScreenRecorder.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pIntent);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(id, builder.build());
    }


}
