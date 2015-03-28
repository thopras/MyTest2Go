package itmelange.com.mytest2go;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import itmelange.com.mytest2go.model.MyAppSingleton;


public class MyConnectActivity extends ActionBarActivity {

    /**======================================================================
     * life cycle section
     * ======================================================================
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_connect);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_connect, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**======================================================================
     * user section
     * ======================================================================
     */

    public void sendMessage_json(View view) {

        EditText editPosition = (EditText) findViewById(R.id.editPosition);
        final TextView txtRespondMsg = (TextView) findViewById(R.id.txtRespondMsg);

        String posText = editPosition.getText().toString();

        // get request queue and send request
        String url ="http://192.168.43.100:8090/test/testservice.php";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        handleResponse(txtRespondMsg, response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        handleResponseError(txtRespondMsg, error);
                    }
                });

    }

    public void onSendClick(View view) {
        sendMessage_String(view);
    }

    /**
     * send message button clicked
     * @param view
     */
    public void sendMessage_String(View view) {

        //
        EditText editPosition = (EditText) findViewById(R.id.editPosition);
        final TextView txtRespondMsg = (TextView) findViewById(R.id.txtRespondMsg);

        txtRespondMsg.setText("sending request...");
        txtRespondMsg.setBackgroundResource(R.color.inProgressState);

        String posText = editPosition.getText().toString();

        // get request queue and send request
        String url ="http://192.168.43.100:8090/test/testservice.php";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        handleResponse(txtRespondMsg, response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        handleResponseError(txtRespondMsg, error);
                    }
                });

        RequestQueue requestQueue = MyAppSingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        MyAppSingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    /**
     * Handle response method
     * @param txtRespondMsg  the view
     * @param response the response string
     */
    private void handleResponse(TextView txtRespondMsg, String response) {
        try {
            if (txtRespondMsg == null) {
                return;
            }

            if (response != null) {
                // Display the response string.
                txtRespondMsg.setText("Response is: " + response);
            } else {
                txtRespondMsg.setText("response is null");
            }
            txtRespondMsg.setBackgroundResource(R.color.readyState);

        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                Log.e("responseZugriff", "Fehler beim Response-Zugriff");
                Log.e("responseZugriff", e.toString());
                Log.e("responseZugriff", e.getMessage());
                Log.e("responseZugriff", e.getStackTrace().toString());
                Log.e("responseZugriff", e.getCause().toString());
            }

        }
    }

    /**
     * The error handle method
     * @param txtRespondMsg  the view
     * @param error volley error
     */
    private void handleResponseError(TextView txtRespondMsg, VolleyError error) {

        String errormsg = "That didn't work!" + error.getMessage() + error.getStackTrace() + error.getCause();
        txtRespondMsg.setText(errormsg);
    }
}
