package com.ynz.newera;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

    private final String KEY = "YNZ NEW ERA";
    private final int RED = Color.rgb(210, 0, 25);
    private final int WHITE = Color.WHITE;
    private final int DARK = Color.rgb(12, 12, 15);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(Color.rgb(8, 8, 10));
        getWindow().setNavigationBarColor(Color.BLACK);

        showLogin();
    }

    int dp(int value) {
        return (int) (value * getResources().getDisplayMetrics().density);
    }

    GradientDrawable shape(int color, int radius, int stroke) {
        GradientDrawable bg = new GradientDrawable();
        bg.setColor(color);
        bg.setCornerRadius(dp(radius));

        if (stroke > 0) {
            bg.setStroke(dp(1), stroke);
        }

        return bg;
    }

    TextView text(String value, int size, int color) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setGravity(Gravity.CENTER);
        t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        return t;
    }

    void showLogin() {
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setPadding(dp(22), dp(25), dp(22), dp(20));
        box.setBackground(shape(Color.rgb(25, 20, 23), 18, RED));

        TextView logo = text("YNZ", 42, RED);
        logo.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        box.addView(logo);

        TextView subtitle = text("N E W   E R A", 15, WHITE);
        box.addView(subtitle);

        TextView info = text("LOGIN ACCESS", 13, Color.LTGRAY);
        info.setPadding(0, dp(20), 0, dp(10));
        box.addView(info);

        EditText input = new EditText(this);
        input.setSingleLine(true);
        input.setHint("Masukkan password / key");
        input.setTextColor(WHITE);
        input.setHintTextColor(Color.GRAY);
        input.setPadding(dp(12), dp(10), dp(12), dp(10));
        input.setBackground(shape(Color.rgb(40, 35, 38), 10, RED));
        box.addView(input);

        AlertDialog dialog = new AlertDialog.Builder(this)
            .setView(box)
            .setPositiveButton("LOGIN", null)
            .setNegativeButton("GET PASSWORD", null)
            .setCancelable(false)
            .create();

        dialog.setOnShowListener(v -> {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                .setTextColor(RED);

            dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                .setTextColor(RED);

            dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                .setOnClickListener(view -> {
                    if (input.getText().toString().equals(KEY)) {
                        dialog.dismiss();
                        showHome();
                    } else {
                        input.setError("Password salah!");
                    }
                });

            dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                .setOnClickListener(view -> openTelegram());
        });

        dialog.show();
    }

    void showHome() {
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(18), dp(20), dp(18), dp(20));

        GradientDrawable background = new GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            new int[] {
                Color.rgb(35, 5, 12),
                Color.rgb(12, 12, 15),
                Color.BLACK
            }
        );
        root.setBackground(background);

        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);

        LinearLayout content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);

        TextView logo = text("YNZ", 48, RED);
        logo.setPadding(0, dp(12), 0, 0);
        content.addView(logo);

        TextView title = text("N E W   E R A", 17, WHITE);
        title.setPadding(0, 0, 0, dp(5));
        content.addView(title);

        TextView welcome = text(
            "WELCOME TO THE NEW GENERATION",
            11,
            Color.LTGRAY
        );
        welcome.setPadding(0, dp(5), 0, dp(25));
        content.addView(welcome);

        addSection(content, "MAIN MENU");

        addMenu(content, "BUG COLLECTION",
            "Kumpulan informasi bug (simulasi)");

        addMenu(content, "CRASH BIASA",
            "Menu simulasi crash biasa");

        addMenu(content, "CRASH PARAH",
            "Menu simulasi crash tingkat lanjut");

        addMenu(content, "BANNED",
            "Informasi dan simulasi status banned");

        addMenu(content, "TARGET",
            "Menu target latihan (simulasi)");

        addSection(content, "OTHER MENU");

        addMenu(content, "PROFIL DEVELOPER",
            "Developer: YNZ NEW ERA\nTelegram: @ynz_newera");

        addMenu(content, "TELEGRAM",
            "Hubungi Telegram resmi");

        TextView footer = text(
            "YNZ NEW ERA  •  VERSION 1.0",
            10,
            Color.GRAY
        );
        footer.setPadding(0, dp(25), 0, dp(10));
        content.addView(footer);

        scroll.addView(content);
        root.addView(scroll);

        setContentView(root);
    }

    void addSection(LinearLayout parent, String title) {
        TextView section = text(title, 12, RED);
        section.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
        section.setPadding(dp(5), dp(12), 0, dp(8));
        parent.addView(section);
    }

    void addMenu(LinearLayout parent, String name, String description) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(16), dp(14), dp(16), dp(14));
        card.setBackground(shape(Color.rgb(28, 25, 28), 14, RED));

        LinearLayout.LayoutParams params =
            new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            );

        params.setMargins(0, dp(5), 0, dp(8));
        parent.addView(card, params);

        TextView heading = text(name, 15, WHITE);
        heading.setGravity(Gravity.LEFT);
        card.addView(heading);

        TextView detail = text(description, 11, Color.LTGRAY);
        detail.setGravity(Gravity.LEFT);
        detail.setPadding(0, dp(5), 0, 0);
        card.addView(detail);

        card.setOnClickListener(v -> {
            if (name.equals("TELEGRAM")) {
                openTelegram();
            } else {
                new AlertDialog.Builder(this)
                    .setTitle(name)
                    .setMessage(description)
                    .setPositiveButton("OK", null)
                    .show();
            }
        });
    }

    void openTelegram() {
        try {
            Intent intent = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://t.me/ynz_newera")
            );
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(
                this,
                "Tidak bisa membuka Telegram",
                Toast.LENGTH_SHORT
            ).show();
        }
    }
}
