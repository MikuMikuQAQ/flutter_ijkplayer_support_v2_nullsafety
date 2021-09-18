package example.android.app.src.main.java.com.example.ijkplayer_example;

import androidx.annotation.NonNull;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MainActivity extends FlutterActivity {

  @Override
  public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
    GeneratedPluginRegistrant.registerWith(flutterEngine);
//    new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL)
//            .setMethodCallHandler(
//                    (call, result) -> {
//                    }
//            );
  }
}
