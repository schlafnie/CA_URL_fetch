package com.maria.ca_url_fetch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    public void onCreate(Bundle s)
    {
        super.onCreate(s);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.theCode);
        Ion.with(getApplicationContext()).load("https://stocksnap.io").asString().setCallback(new FutureCallback<String>() {
            @Override
            public void onCompleted(Exception e, String result) {
                String imgRegex = "(?i)<img[^>]+?src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";

                Pattern p = Pattern.compile(imgRegex);
                Matcher m = p.matcher(result);
                ArrayList<String> imagePaths = new ArrayList<String>();

                while (m.find()) {
                    String imgSrc = m.group(1);

                    if (imgSrc.toLowerCase().contains(".jpg")){
                        imagePaths.add(imgSrc);
                    }

                }

                String urls_test = "";

                for (String img : imagePaths ) {

                    urls_test = urls_test + img + "/n/r";
                }

                tv.setText(urls_test);
            }



        });

    }
}
