package com.bose.mdietger.soundtouchandroid.http;

import com.android.volley.Response;
import com.bose.mdietger.soundtouchandroid.http.volume.Volume;

/**
 * DeviceManager interface. The DeviceManager defines all possible interactions
 * with the device.
 */
public interface DeviceManager {

    /**
     * Start listening for messages from device.
     */
    void listenForMessages();

    /**
     * Get the current volume level.
     * @param responseListener the responseListener
     * @param errorListener the errorListener
     */
    void getVolume(Response.Listener responseListener, Response.ErrorListener errorListener);

    /**
     * Sets volume to volume level.
     * @param volumeLevel the volume level to set
     * @param responseListener the responseListener
     * @param errorListener the errorListener
     */
    void setVolume(Volume volumeLevel, Response.Listener responseListener, Response.ErrorListener errorListener);

}
