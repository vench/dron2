 

package com.dron;

import android.location.Location;

/**
 *
 * @author vench
 */
public interface IMyLocationListenerHandler {
    
    public void updateLocation(Location location);
    public void updateProvider(String provider, boolean status );
}
