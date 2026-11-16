
package com.ynz.newera;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.widget.*;

public class MainActivity extends Activity {

    private final String PASSWORD = "YNZ NEW ERA";
    private final String TELEGRAM = "https://t.me/ynz_newera";

    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginPage();
    }

    private GradientDrawable bg(int color) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(20);
        d.setStroke(2, Color.RED);
        return d;
    }

    private void loginPage() {
        layout = new LinearLayout(this);
        layout.setOrientation(1);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(25, 25, 25, 25);
        layout.setBackgroundColor(Color.rgb(10,10,10));

        setContentView(layout);

        TextView title = new TextView(this);
        title.setText("⚔ YNZ NEW ERA ⚔");
        title.setTextSize(28);
        title.setTextColor(Color.RED);
        title.setTypeface(null, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        layout.addView(title);

        EditText pass = new EditText(this);
        pass.setHint("ENTER PASSWORD");
        pass.setHintTextColor(Color.GRAY);
        pass.setTextColor(Color.WHITE);
        pass.setGravity(Gravity.CENTER);
        pass.setBackground(bg(Color.DKGRAY));
        layout.addView(pass);

        Button enter = new Button(this);
        enter.setText("ENTER");
        layout.addView(enter);

        Button get = new Button(this);
        get.setText("GET PASSWORD");
        layout.addView(get);

        enter.setOnClickListener(v -> {
            if (PASSWORD.equals(pass.getText().toString())) {
                Toast.makeText(this,
                    "PASSWORD BENAR!",
                    Toast.LENGTH_SHORT).show();
                homePage();
            } else {
                Toast.makeText(this,
                    "PASSWORD SALAH!",
                    Toast.LENGTH_SHORT).show();
            }
        });

        get.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_VIEW,
                Uri.parse(TELEGRAM));
            startActivity(i);
        });
    }

    private void homePage() {
        // Halaman utama dibuat pada STEP 2.
    }
}
