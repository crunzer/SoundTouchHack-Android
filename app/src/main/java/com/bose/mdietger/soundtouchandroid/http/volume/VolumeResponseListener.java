package com.bose.mdietger.soundtouchandroid.http.volume;

import com.bose.mdietger.soundtouchandroid.http.DefaultResponseListener;
import com.bose.mdietger.soundtouchandroid.http.XmlMarshaller;

/**
 * BassResponseListener class. This class is responsible of handling
 * GET volume responses.
 */
public class VolumeResponseListener extends DefaultResponseListener {

    private static final String TAG = "BassResponseListener";

    private VolumeCallback callback;

    /**
     * Instantiates a new BassResponseListener.
     * @param callback the callback
     */
    public VolumeResponseListener(VolumeCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onResponse(String response) {
        VolumeResponse volume = XmlMarshaller.getInstance().unmarshall(VolumeResponse.class, response);
        callback.setVolume(volume.getActualVolume());
    }

}
