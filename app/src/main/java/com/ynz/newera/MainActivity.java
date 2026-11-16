package com.ynz.newera;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.content.Intent;
import android.net.Uri;
import android.text.InputType;
import android.view.Gravity;
import android.widget.*;

public class MainActivity extends Activity {

    private final String PASSWORD = "YNZ NEW ERA";
    private final String TELEGRAM = "https://t.me/ynz_newera";

    private LinearLayout root;
    private EditText targetInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLogin();
    }

    private GradientDrawable bg(int color) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(20);
        d.setStroke(2, Color.RED);
        return d;
    }

    private TextView makeText(String value, int size) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(Color.WHITE);
        t.setGravity(Gravity.CENTER);
        t.setTypeface(null, Typeface.BOLD);
        t.setPadding(12, 18, 12, 18);
        return t;
    }

    private Button makeButton(String value) {
        Button b = new Button(this);
        b.setText(value);
        b.setTextColor(Color.WHITE);
        b.setAllCaps(false);
        b.setBackground(bg(Color.rgb(45, 0, 0)));
        return b;
    }

    private void setup() {
        ScrollView scroll = new ScrollView(this);

        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(24, 30, 24, 30);
        root.setBackgroundColor(Color.rgb(12, 12, 12));

        scroll.addView(root);
        setContentView(scroll);
    }

    private void showLogin() {
        setup();

        root.addView(makeText("⚔ YNZ NEW ERA ⚔", 28));
        root.addView(makeText("WELCOME TO THE DARK SYSTEM", 13));

        EditText pass = new EditText(this);
        pass.setHint("ENTER PASSWORD");
        pass.setHintTextColor(Color.GRAY);
        pass.setTextColor(Color.WHITE);
        pass.setGravity(Gravity.CENTER);
        pass.setSingleLine(true);
        pass.setInputType(
            InputType.TYPE_CLASS_TEXT |
            InputType.TYPE_TEXT_VARIATION_PASSWORD
        );
        pass.setBackground(bg(Color.DKGRAY));

        root.addView(pass);

        Button enter = makeButton("ENTER");
        root.addView(enter);

        Button get = makeButton("GET PASSWORD");
        root.addView(get);

        enter.setOnClickListener(v -> {
            if (PASSWORD.equals(pass.getText().toString())) {
                showHome();
            } else {
                new AlertDialog.Builder(this)
                    .setTitle("ACCESS DENIED")
                    .setMessage("PASSWORD SALAH!")
                    .setPositiveButton("OK", null)
                    .show();
            }
        });

        get.setOnClickListener(v -> {
            try {
                startActivity(new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(TELEGRAM)
                ));
            } catch (Exception e) {
                Toast.makeText(this,
                    "Telegram tidak tersedia",
                    Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showHome() {
        setup();

        root.addView(makeText("⚔ YNZ NEW ERA ⚔", 29));
        root.addView(makeText("DEVELOPER : YNZ NEW ERA", 16));
        root.addView(makeText("TARGET SYSTEM", 20));

        targetInput = new EditText(this);
        targetInput.setHint("🎯 MASUKKAN TARGET");
        targetInput.setHintTextColor(Color.GRAY);
        targetInput.setTextColor(Color.WHITE);
        targetInput.setGravity(Gravity.CENTER);
        targetInput.setSingleLine(true);
        targetInput.setBackground(bg(Color.DKGRAY));

        root.addView(targetInput);

        addMenu("CRASH BIASA");
        addMenu("CRASH PARAH");
        addMenu("BANNED");

        root.addView(makeText(
            "DEVELOPER PROFILE\nYNZ NEW ERA\n© 2026",
            15
        ));
    }

    private void addMenu(String name) {
        Button b = makeButton(name);

        LinearLayout.LayoutParams params =
            new LinearLayout.LayoutParams(
                -1, -2
            );

        params.setMargins(0, 12, 0, 12);
        root.addView(b, params);

        b.setOnClickListener(v -> {
            String target =
                targetInput.getText().toString().trim();

            if (target.isEmpty()) {
                targetInput.setError("Isi TARGET dahulu");
                return;
            }

            new AlertDialog.Builder(this)
                .setTitle(name)
                .setMessage(
                    "TARGET: " + target +
                    "\n\nSTATUS: SIMULASI AKTIF" +
                    "\nTidak ada serangan sungguhan."
                )
                .setPositiveButton("OK", null)
                .show();
        });
    }
}
