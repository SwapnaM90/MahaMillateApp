/*
 * Copyright (c) - 2020 & Created By AbhishekR - EncureIT :)
 */

package com.mninetytechnology.mahamillateapp.Helpers;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GlobalHelper {
    public Context context;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private UIHelper uiHelper;
    private UserHelper userHelper;

    public GlobalHelper(Context context) {
        this.context = context;
    }

    public SharedPreferencesHelper getSharedPreferencesHelper() {
        if (this.sharedPreferencesHelper == null) {
            this.sharedPreferencesHelper = new SharedPreferencesHelper(this.context);
        }
        return this.sharedPreferencesHelper;
    }

    public String getDevicesID() {
        return Settings.Secure.getString(this.context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public String getCurrentDateTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(c.getTime());
    }

    public UIHelper getUIHelper() {
        if (this.uiHelper == null) {
            this.uiHelper = new UIHelper(this.context);
        }
        return uiHelper;
    }

    public UserHelper getUserHelper() {
        if (this.userHelper == null) {
            this.userHelper = new UserHelper(this.context);
        }
        return this.userHelper;
    }

    public static String md5(final String s) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte[] messageDigest = digest.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getVersionNumber() {
        String version = "0.0";
        PackageInfo pInfo = null;
        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    public String getRandomNumber() {
        return String.valueOf(System.currentTimeMillis());
    }

//    public void writeTextFile(String text) {
//        try {
//            SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
//            SimpleDateFormat timeformat = new SimpleDateFormat("hh:mm:ss");
//            Date date = new Date();
//            File root = new File(Environment.getExternalStorageDirectory(), Contants.FOLDER_SAVE);
//            if (!root.exists()) {
//                android.util.Log.e("WriteText", "Root Not Exists");
//                if (!root.mkdir()) {
//                    android.util.Log.e("WriteText", "Failed to create direcktory");
//                    return;
//                }
//            }
//
//            File fileLog = new File(root, "log-" + format.format(date) + ".txt");
//            if (!fileLog.exists()) {
//                android.util.Log.e("WriteText", "Can't found file Log");
//                if (!fileLog.createNewFile()) {
//                    android.util.Log.e("WriteText", "Can't create file Log");
//                    return;
//                }
//            }
//
//            BufferedWriter buf = new BufferedWriter(new FileWriter(fileLog, true));
//            final String currentDateTimeString = timeformat.format(new Date());
//            buf.append(currentDateTimeString + " : " + text);
//            buf.newLine();
//            buf.close();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//        android.util.Log.i("WriteText", "write : " + text);
//    }

//    public boolean checkFileDirExternal() {
//        File folder = new File(Environment.getExternalStorageDirectory(), Contants.FOLDER_SAVE);
//        boolean success = true;
//        if (!folder.exists()) {
//            success = folder.mkdir();
//            return success;
//        } else {
//            return true;
//        }
//    }
//
//    public File createFileFromBitmap(Bitmap bitmap, String file_name) throws IOException {
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bytes);
//        File destination = null;
//        if (checkFileDirExternal()) {
//            destination = new File(Environment.getExternalStorageDirectory(), Contants.FOLDER_SAVE +
//                    Contants.DEFAULT_FILENAME + file_name + ".png");
//            FileOutputStream fo;
//            destination.createNewFile();
//            fo = new FileOutputStream(destination);
//            fo.write(bytes.toByteArray());
//            fo.close();
//        }
//        return destination;
//    }
}
