package itmelange.com.mytest2go;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

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

    /**
     * send message button clicked
     * @param view
     */
    public void sendMessage(View view) {
        //
        EditText editPosition = (EditText) findViewById(R.id.editPosition);
        final TextView txtRespondMsg = (TextView) findViewById(R.id.txtRespondMsg);

        String posText = editPosition.getText().toString();

        // get request queue and send request
        String url ="http://localhost:8090/test/testservice.php";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        txtRespondMsg.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                txtRespondMsg.setText("That didn't work!");
            }
        });


        RequestQueue requestQueue = MyAppSingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        MyAppSingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}
