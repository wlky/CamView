package eu.livotov.labs.android.camview.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

import eu.livotov.labs.android.camview.CameraLiveView;
import eu.livotov.labs.android.camview.ScannerLiveView;
import eu.livotov.labs.android.camview.camera.CameraManager;
import eu.livotov.labs.android.camview.camera.CameraController;
import eu.livotov.labs.android.camview.camera.CameraDelayedOperationResult;
import eu.livotov.labs.android.camview.camera.CameraInfo;


public class MainActivity extends Activity
{

    private ScannerLiveView camera;
    private CameraController controller;
    private boolean flashStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        camera = (ScannerLiveView) findViewById(R.id.camview);
        camera.setScannerViewEventListener(new ScannerLiveView.ScannerViewEventListener()
        {
            @Override
            public void onCodeScanned(String data)
            {
                Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
            }
        });
        camera.startScanner();
    }


    @Override
    protected void onDestroy()
    {
        camera.stopScanner();
        super.onDestroy();
    }

    public void toggleFlash(View view)
    {
        flashStatus = !flashStatus;
        controller.switchFlashlight(flashStatus);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
