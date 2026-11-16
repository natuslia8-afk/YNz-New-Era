
package com.ynz.newera;

import android.app.*;
import android.os.*;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.*;
import android.widget.*;
import android.content.*;

public class MainActivity extends Activity {

    String KEY = "YNZ NEW ERA";
    LinearLayout layout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        login();
    }

    void login() {
        EditText input = new EditText(this);
        input.setHint("Masukkan password / key");
        input.setTextColor(Color.BLACK);

        new AlertDialog.Builder(this)
            .setTitle("YNZ NEW ERA LOGIN")
            .setMessage("Masukkan key untuk melanjutkan")
            .setView(input)
            .setPositiveButton("LOGIN", (d, w) -> {
                if (input.getText().toString().equals(KEY)) {
                    home();
                } else {
                    Toast.makeText(this, "Key salah!", Toast.LENGTH_SHORT).show();
                    login();
                }
            })
            .setNegativeButton("GET PASSWORD", (d, w) -> {
                telegram();
                login();
            })
            .setCancelable(false)
            .show();
    }

    void home() {
        layout = new LinearLayout(this);
        layout.setOrientation(1);
        layout.setPadding(25, 35, 25, 25);
        layout.setBackgroundColor(Color.rgb(12,12,12));

        TextView title = new TextView(this);
        title.setText("YNZ NEW ERA");
        title.setTextSize(28);
        title.setTypeface(null, Typeface.BOLD);
        title.setTextColor(Color.RED);
        title.setGravity(Gravity.CENTER);
        layout.addView(title);

        addMenu("BUG COLLECTION");
        addMenu("CRASH BIASA");
        addMenu("CRASH PARAH");
        addMenu("BANNED");
        addMenu("TARGET");

        addMenu("PROFIL DEVELOPER");
        addMenu("TELEGRAM");

        ScrollView scroll = new ScrollView(this);
        scroll.addView(layout);
        setContentView(scroll);
    }

    void addMenu(String name) {
        Button btn = new Button(this);
        btn.setText(name);
        btn.setTextColor(Color.WHITE);
        btn.setBackgroundTintList(
            android.content.res.ColorStateList.valueOf(Color.rgb(160,0,0))
        );

        LinearLayout.LayoutParams params =
            new LinearLayout.LayoutParams(-1, -2);
        params.setMargins(0, 10, 0, 10);
        layout.addView(btn, params);

        btn.setOnClickListener(v -> {
            if (name.equals("TELEGRAM")) {
                telegram();
            } else if (name.equals("PROFIL DEVELOPER")) {
                new AlertDialog.Builder(this)
                    .setTitle("DEVELOPER")
                    .setMessage("YNZ NEW ERA\nTelegram: @ynz_newera")
                    .setPositiveButton("OK", null)
                    .show();
            } else {
                new AlertDialog.Builder(this)
                    .setTitle(name)
                    .setMessage("Menu " + name + "\nMode simulasi aktif.")
                    .setPositiveButton("OK", null)
                    .show();
            }
        });
    }

    void telegram() {
        try {
            Intent i = new Intent(Intent.ACTION_VIEW,
                android.net.Uri.parse("https://t.me/ynz_newera"));
            startActivity(i);
        } catch (Exception e) {
            Toast.makeText(this, "Telegram tidak ditemukan",
                Toast.LENGTH_SHORT).show();
        }
    }
}
