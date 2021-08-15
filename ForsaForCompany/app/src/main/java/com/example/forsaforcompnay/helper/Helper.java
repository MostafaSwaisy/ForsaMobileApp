package com.example.forsaforcompnay.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class Helper {
    private final SharedPreferences sp;
    private SharedPreferences.Editor spEditor;
    private static Helper helper;

    public Helper(Context context) {
        this.sp = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static Helper getInstance(Context context) {
        if (helper == null) {
            helper = new Helper(context);
            return helper;
        } else {
            return helper;
        }
    }

    public boolean setBoolean(String key, boolean value) {
        spEditor = sp.edit();
        spEditor.putBoolean(key, value);
        spEditor.commit();
        return true;
    }

    public boolean getBoolean(String key) {
        return sp.getBoolean(key, false);
    }

    public boolean setFloat(String key, float value) {
        spEditor = sp.edit();
        spEditor.putFloat(key, value);
        spEditor.commit();
        return true;
    }

    public float getFloat(String key) {
        return sp.getFloat(key, -1);
    }

    public boolean setLong(String key, long value) {
        spEditor = sp.edit();
        spEditor.putLong(key, value);
        spEditor.commit();
        return true;
    }

    public long getLong(String key) {
        return sp.getLong(key, -1);
    }

    public boolean setInt(String key, int value) {
        spEditor = sp.edit();
        spEditor.putInt(key, value);
        spEditor.commit();
        return true;
    }

    public int getInt(String key) {
        return sp.getInt(key, -1);
    }

    public boolean setString(String key, String value) {
        spEditor = sp.edit();
        spEditor.putString(key, value);
        spEditor.commit();
        return true;
    }

    public String getString(String key) {
        try {
            return sp.getString(key, null);
        } catch (Exception e) {
            return null;
        }

    }

    public static boolean checkData(String text, TextView textView) {
        if (text != null) {
            if (text.equals("") || text.isEmpty()) {
                textView.setText("Empty");
                return false;
            } else {
                textView.setText(text);
            }
        }
        return true;
    }
    public void clearall() {
        sp.edit().clear().commit();
    }
}
