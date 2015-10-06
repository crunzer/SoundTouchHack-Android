package com.bose.mdietger.soundtouchandroid.http;

import android.util.Log;

import com.android.volley.Response;
import com.bose.mdietger.soundtouchandroid.http.volume.Volume;
import com.bose.mdietger.soundtouchandroid.soundtouch.SoundTouch;
import com.bose.mdietger.soundtouchandroid.websockets.WebSocketConnector;

import org.java_websocket.drafts.Draft_17;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * SoundTouchDeviceManager class. This class is reponsible for interactions
 * with the SoundTouch devices.
 */
public class SoundTouchDeviceManager extends AbstractDeviceManager<SoundTouch> implements DeviceManager {

    private static final String TAG = "SoundTouchDeviceManager";

    private static final String HTTP_PROTOCOL = "http://";
    private static final String HTTP_PORT = "8090";

    private static final String WS_PROTOCOL = "ws://";
    private static final String WS_PORT = "8080";

    private static final String COLON = ":";


    /**
     * Instantiates a new SoundTouchDeviceManager.
     * @param device the device to manage
     */
    public SoundTouchDeviceManager(SoundTouch device) {
        super(device);
    }

    @Override
    protected String getAbsoluteUrl(String path) {
        return HTTP_PROTOCOL + device.getIp() + COLON + HTTP_PORT + path;
    }

    // ----------------------------------------------------------------------------------------------- MESSAGES (WEBSOCKETS)

    @Override
    public void listenForMessages() {
        try {
            URI uri = new URI(WS_PROTOCOL + device.getIp() + COLON + WS_PORT);

            Map<String,String> headers = new HashMap<String, String>();
            headers.put("Sec-WebSocket-Protocol","gabbo");

            WebSocketConnector connector = new WebSocketConnector(uri, new Draft_17(), headers, 5000);
            connector.connect();

            Log.d(TAG, "Listening for messages");
        } catch(Exception e) {
            Log.e(TAG, "Error listening for messages: " + e.getMessage());
        }
    }

    // ----------------------------------------------------------------------------------------------- VOLUME

    private static final String VOLUME = "/volume";

    @Override
    public void getVolume(Response.Listener responseListener, Response.ErrorListener errorListener) {
        Log.d(TAG, "getVolume");
        doGet(VOLUME, responseListener, errorListener);
    }

    @Override
    public void setVolume(Volume volumeLevel, Response.Listener responseListener, Response.ErrorListener errorListener) {
        Log.d(TAG, "setVolume");
        String dataXml = XmlMarshaller.getInstance().marshall(volumeLevel);
        doPost(VOLUME, dataXml, responseListener, errorListener);
    }

}
