package com.example.android.sunshine.utilities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

import com.example.android.sunshine.R;

/**
 * Created by hassan on 10/01/2018.
 */

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference preference = findPreference(key);
        if(preference != null){
            if(!(preference instanceof CheckBoxPreference)){
                setPreferenceSummary(preference,sharedPreferences.getString(key,""));
            }
        }
    }


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preference_settings_screen);

        PreferenceScreen preferenceScreen = getPreferenceScreen();
        SharedPreferences sharedPreferences = preferenceScreen.getSharedPreferences();

        int count = preferenceScreen.getPreferenceCount();
        for(int i = 0; i < count; i++){
            Preference preference = preferenceScreen.getPreference(i);
            if(!(preference instanceof CheckBoxPreference)){
                String value = sharedPreferences.getString(preference.getKey(),"");
                setPreferenceSummary(preference,value);
            }
        }
    }

    void setPreferenceSummary(Preference p, Object o){
        String value = o.toString();
        String key = p.getKey();

        if(p instanceof ListPreference){
            ListPreference listPreference = (ListPreference) p;
            int index = listPreference.findIndexOfValue(value);
            if(index >= 0){
                p.setSummary(listPreference.getEntries()[index]);
            }else{
                p.setSummary(value);
            }
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onStop(){
        super.onStop();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
}
