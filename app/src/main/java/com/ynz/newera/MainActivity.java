package com.ynz.newera;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

    private static final String KEY = "YNZ NEW ERA";
    private LinearLayout root, content;
    private int red = Color.rgb(210, 15, 35);
    private int white = Color.WHITE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLogin();
    }

    private GradientDrawable shape(int color, int stroke) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(18);
        d.setStroke(2, stroke);
        return d;
    }

    private TextView text(String value, int size, int color) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setGravity(Gravity.CENTER);
        t.setTypeface(Typeface.DEFAULT_BOLD);
        t.setPadding(12, 12, 12, 12);
        return t;
    }

    private void screen(String title) {
        ScrollView scroll = new ScrollView(this);
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(24, 35, 24, 30);
        root.setBackgroundColor(Color.rgb(12, 8, 12));

        TextView heading = text(title, 27, red);
        root.addView(heading);

        scroll.addView(root);
        setContentView(scroll);
    }

    private void addButton(String label, Runnable action) {
        TextView b = text(label, 16, white);
        b.setBackground(shape(Color.rgb(25, 12, 18), red));
        LinearLayout.LayoutParams p =
            new LinearLayout.LayoutParams(-1, 58);
        p.setMargins(0, 9, 0, 9);
        root.addView(b, p);
        b.setOnClickListener(v -> action.run());
    }

    private void showLogin() {
        screen("YNZ NEW ERA");
        root.addView(text("WELCOME BACK", 17, white));
        root.addView(text("ENTER YOUR ACCESS KEY", 13, red));

        EditText input = new EditText(this);
        input.setSingleLine(true);
        input.setHint("Enter password");
        input.setHintTextColor(Color.GRAY);
        input.setTextColor(white);
        input.setBackground(shape(Color.rgb(25, 20, 25), red));
        root.addView(input, new LinearLayout.LayoutParams(-1, 55));

        addButton("LOGIN", () -> {
            if (KEY.equals(input.getText().toString())) {
                showHome();
            } else {
                new AlertDialog.Builder(this)
                    .setTitle("ACCESS DENIED")
                    .setMessage("Password salah!")
                    .setPositiveButton("OK", null)
                    .show();
            }
        });

        addButton("GET PASSWORD", this::showGetPassword);
    }

    private void showGetPassword() {
        screen("GET PASSWORD");
        root.addView(text("Contact developer to get your key", 15, white));
        addButton("TELEGRAM", () ->
            openUrl("https://t.me/ynz_newera"));
        addButton("BACK", this::showLogin);
    }

    private void showHome() {
        screen("YNZ NEW ERA");
        root.addView(text("WELCOME TO THE PANEL", 14, white));

        addButton("BUG COLLECTION", () -> showCollection());
        addButton("PROFILE DEVELOPER", () -> showProfile());
        addButton("TELEGRAM", () ->
            openUrl("https://t.me/ynz_newera"));
        addButton("LOGOUT", this::showLogin);
    }

    private void showCollection() {
        screen("BUG COLLECTION");
        addButton("CRASH BIASA", () ->
            showInfo("CRASH BIASA"));
        addButton("CRASH PARAH", () ->
            showInfo("CRASH PARAH"));
        addButton("BANNED", () ->
            showInfo("BANNED"));
        addButton("TARGET", () ->
            showInfo("TARGET"));
        addButton("BACK", this::showHome);
    }

    private void showInfo(String name) {
        screen(name);
        root.addView(text(
            "Mode simulasi / informasi saja. Tidak melakukan serangan atau merusak perangkat.",
            15, white));
        addButton("BACK", this::showCollection);
    }

    private void showProfile() {
        screen("PROFILE DEVELOPER");
        root.addView(text("YNZ NEW ERA", 23, red));
        root.addView(text("Developer: YNZ", 16, white));
        root.addView(text("Telegram: @ynz_newera", 14, white));
        addButton("CONTACT DEVELOPER", () ->
            openUrl("https://t.me/ynz_newera"));
        addButton("BACK", this::showHome);
    }

    private void openUrl(String url) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } catch (Exception e) {
            Toast.makeText(this, "Link tidak bisa dibuka",
                Toast.LENGTH_SHORT).show();
        }
    }
}
