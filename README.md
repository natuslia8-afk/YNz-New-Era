# YNZ NEW ERA — Android

Contoh proyek Android native (Java) dengan tema hitam-merah.

## Login
Password demo: `YNZ NEW ERA`

## Konfigurasi Telegram
Ubah `https://t.me/ynz_newera` di `MainActivity.java` menjadi username Telegram developer yang benar.

## Build lewat GitHub
1. Buat repository GitHub baru.
2. Upload seluruh isi folder proyek ini ke repository (pastikan folder `.github` ikut terunggah).
3. Buka tab **Actions**.
4. Jalankan workflow **Build YNZ NEW ERA APK**, atau push commit ke branch `main`.
5. Setelah selesai, buka hasil workflow dan unduh artifact `YNZ-NEW-ERA-debug-apk`.
6. Ekstrak artifact untuk mendapatkan `app-debug.apk`.

## Catatan
Menu Crash/Banned adalah demo UI saja. Proyek ini tidak melakukan crash, pemblokiran, atau tindakan terhadap perangkat/akun.
Password ditanam di aplikasi hanya untuk demo. Untuk aplikasi sungguhan, gunakan autentikasi sisi server dan jangan menyimpan password rahasia di kode APK.
