package itmelange.com.mytest2go.model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by thopras on 25.03.2015.
 *
 * This singleton holds the request queue
 * which is used application wide.
 */
public class MyAppSingleton {

    private static MyAppSingleton mInstance;
    private static Context mContext;

    private RequestQueue mRequestQueue;

    private MyAppSingleton(Context context) {
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    /**
     * get instance for this class once
     * @param context the app context
     * @return instance of this class
     */
    public static synchronized MyAppSingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MyAppSingleton(context);
        }
        return mInstance;
    }

    /**
     * get only this request queue
     * @return the one and only request queue
     */
    public RequestQueue getRequestQueue() {
        if (this.mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            this.mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return this.mRequestQueue;
    }

    /**
     * Add a request of certain type to the queue
     * @param request the request
     * @param <T> volley request type, for example StringRequest
     */
    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }

}
