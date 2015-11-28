package com.dron;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author vench
 */
public class MainActivity extends Activity implements IDronState ,
        IMyLocationListenerHandler
{
    
    
    private boolean allowGPS = false;
    
    public Location lastLocation;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        SenderState.init(this);
        
        
     
        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
 
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new MyLocationListener(this));
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new MyLocationListener(this));
 
    }
    
    
        /**
     * Обработчик включатлей моторов
     * @param view 
     */
    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
    }
    
    /**
     * Делаем фото
     * @param view 
     */
    public void onMakePhoto(View view) {
    
    }
    
    public void overScrollModeMover(View view) {
    }

    public String getIP() {
        final TextView ip = (TextView) findViewById(R.id.ip);
        return ip.getText().toString();
    }

    public Map<String, String> getParams() {
        Map<String, String> m = new HashMap();
        if(lastLocation != null) {
            m.put("provider", lastLocation.getProvider());
            m.put("latitude", lastLocation.getLatitude() + "");
            m.put("longitude", lastLocation.getLongitude() + "");
            
            
           final SeekBar m1 = (SeekBar) findViewById(R.id.mover1); 
           m.put("mover1", m1.getProgress() + "");
           final SeekBar m2 = (SeekBar) findViewById(R.id.mover2); 
           m.put("mover2", m2.getProgress() + "");
           final SeekBar m3 = (SeekBar) findViewById(R.id.mover3); 
           m.put("mover3", m3.getProgress() + "");
           final SeekBar m4 = (SeekBar) findViewById(R.id.mover4); 
           m.put("mover4", m4.getProgress() + "");
            
        }
        return m;
    }
    
    private void updateSeekBarProgress(SeekBar s, int value) {
        s.setProgress(0); 
        s.setMax(100);
        s.setProgress(  value ); 
    }
    
    private void updateSeekBarProgress(SeekBar s, String value) { 
        updateSeekBarProgress(s, Integer.parseInt(value));
    }

    public void setParams(Map<String, String> params) { 
        final TextView tv = (TextView) findViewById(R.id.log);
        try {  
            tv.setText(params.get("base") +  Integer.parseInt( params.get("mover1") ) );
            
            if(params.containsKey("mover1")) {
                final SeekBar m1 = (SeekBar) findViewById(R.id.mover1);
                updateSeekBarProgress(m1,  params.get("mover1"));  
            }
            if(params.containsKey("mover2")) {
                final SeekBar m2 = (SeekBar) findViewById(R.id.mover2);
                updateSeekBarProgress(m2,  params.get("mover2"));                
            }
            if(params.containsKey("mover3")) {
                final SeekBar m3 = (SeekBar) findViewById(R.id.mover3);
                updateSeekBarProgress(m3,  params.get("mover3"));               
            }
            if(params.containsKey("mover4")) {
                final SeekBar m4 = (SeekBar) findViewById(R.id.mover4);
                updateSeekBarProgress(m4,  params.get("mover4"));               
            }
            
        } catch(Exception e) {}
                
    }

    
    
    public void updateLocation(Location location) {
        
         if(allowGPS && (location.getProvider() == null ? LocationManager.NETWORK_PROVIDER == null : location.getProvider().equals(LocationManager.NETWORK_PROVIDER))) {
             return;
         }
         final TextView tv = (TextView) findViewById(R.id.dialog);
         tv.setText(location.getProvider() + ": " +
                   location.getLatitude() + " x " +
                   location.getLongitude() );
         lastLocation = location;
    }

    public void updateProvider(String provider, boolean status) {
        if(provider == null ? LocationManager.GPS_PROVIDER == null : provider.equals(LocationManager.GPS_PROVIDER)) {
            allowGPS = status;
        }
        
 
    }
}
