package com.example.notificationtypes;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RemoteViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "default_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create notification channel for devices running Android 8.0 (API level 26) and above
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CharSequence name = "Default Channel";
            String description = "This is the default notification channel.";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            // Register the channel with the system
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        // Button to trigger Basic Notification
        Button btnBasicNotification = findViewById(R.id.btn_basic_notification);
        btnBasicNotification.setOnClickListener(view -> sendBasicNotification());

        // Button to trigger Notification with Action
        Button btnActionNotification = findViewById(R.id.btn_action_notification);
        btnActionNotification.setOnClickListener(view -> sendNotificationWithAction());

        // Button to trigger BigText Notification
        Button btnBigTextNotification = findViewById(R.id.btn_bigtext_notification);
        btnBigTextNotification.setOnClickListener(view -> sendBigTextNotification());

        // Button to trigger Progress Notification
        Button btnProgressNotification = findViewById(R.id.btn_progress_notification);
        btnProgressNotification.setOnClickListener(view -> sendProgressNotification());

        // Button to trigger Custom Notification
        Button btnCustomNotification = findViewById(R.id.btn_custom_notification);
        btnCustomNotification.setOnClickListener(view -> sendCustomNotification());
    }

    // Basic Notification
//    private void sendBasicNotification() {
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
//                .setSmallIcon(R.drawable.notification_icon)
//                .setContentTitle("Basic Notification")
//                .setContentText("This is a simple notification.")
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                .setAutoCancel(true);
//
//        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        notificationManager.notify(1, builder.build());
//    }
    public void sendBasicNotification() {
        // Create NotificationManager
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // Create NotificationBuilder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default_channel")
                .setSmallIcon(R.drawable.notification_icon) // Set small icon
                .setContentTitle("Basic Notification") // Set the title
                .setContentText("This is a basic notification.") // Set the content text
                .setPriority(NotificationCompat.PRIORITY_DEFAULT) // Set priority
                .setAutoCancel(true); // Auto cancel when clicked

        // Issue the notification
        Button btnBasicNotification = findViewById(R.id.btn_basic_notification);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            btnBasicNotification.setText("not send");
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }


        notificationManager.notify(1, builder.build());
    }

    // Notification with Action
    private void sendNotificationWithAction() {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("Notification with Action")
                .setContentText("Tap to open MainActivity")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .addAction(R.drawable.ic_action, "Open", pendingIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(2, builder.build());
    }

    // BigTextStyle Notification
    private void sendBigTextNotification() {
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.bigText("Here is a longer message that will be displayed in a big text style. " +
                "This is useful when you have more content that you want to show in a scrollable format.");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("Big Text Notification")
                .setContentText("Tap to see more.")
                .setStyle(bigTextStyle)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(3, builder.build());
    }

    // Progress Notification
    private void sendProgressNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("Downloading")
                .setContentText("Download in progress...")
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setProgress(100, 0, false)
                .setAutoCancel(false);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(4, builder.build());

        new Thread(() -> {
            int progress = 0;
            while (progress <= 100) {
                builder.setProgress(100, progress, false);
                notificationManager.notify(4, builder.build());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progress += 10;
            }
            builder.setContentText("Download Complete")
                    .setProgress(0, 0, false)
                    .setAutoCancel(true);
            notificationManager.notify(4, builder.build());
        }).start();
    }

    // Custom Notification
    private void sendCustomNotification() {
        // Create NotificationManager
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // Create NotificationBuilder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default_channel")
                .setSmallIcon(R.drawable.notification_icon) // Set small icon
                .setContentTitle("Custom Notification") // Set the title
                .setContentText("This is a custom notification 😁😁") // Set the content text
                .setPriority(NotificationCompat.PRIORITY_DEFAULT) // Set priority
                .setAutoCancel(true); // Auto cancel when clicked

        // Issue the notification

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {

            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }


        notificationManager.notify(5, builder.build());
    }

}
