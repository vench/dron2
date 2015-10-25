 

package com.dron;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 *
 * @author vench
 */
public class MyLocationListener implements LocationListener {
    
    private final IMyLocationListenerHandler handler;
    
    public MyLocationListener(IMyLocationListenerHandler handler) {
        this.handler = handler;
    }
    
    public void onLocationChanged(Location location) { 
        handler.updateLocation(location); 
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        
    }

    public void onProviderEnabled(String provider) {
         handler.updateProvider(provider, true);
    }
    

    
    public void onProviderDisabled(String provider) {
         handler.updateProvider(provider, false);
    }
}
