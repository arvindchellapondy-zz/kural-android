package zerobase.us.kural;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import zerobase.us.kural.model.Adhikaram;
import zerobase.us.kural.model.Adhikarams;
import zerobase.us.kural.model.Kural;
import zerobase.us.kural.model.Kurals;
import zerobase.us.kural.util.AdhikaramListViewAdapter;
import zerobase.us.kural.util.SwitchFragmentInterface;

public class ThirukuralActivity extends AppCompatActivity implements SwitchFragmentInterface {

    Adhikarams adhikarams;
    Kurals kurals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_kural);
        ButterKnife.inject(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        getData();

        Bundle bundle = new Bundle();
        bundle.putParcelable(AdhikaramFragment.ADHIKARAMS, adhikarams);
        bundle.putParcelable(AdhikaramFragment.KURALS, kurals);
        switchToFragment(new AdhikaramFragment(), false, bundle);

    }

    public void getData() {

        Gson gson = new Gson();
        String adhikaramJson = loadJSONFromAsset(this.getResources().openRawResource(R.raw.adhikaram));
        adhikarams = gson.fromJson(adhikaramJson, Adhikarams.class);

        String kuralJson = loadJSONFromAsset(this.getResources().openRawResource(R.raw.kural));
        kurals = gson.fromJson(kuralJson, Kurals.class);
    }


    public String loadJSONFromAsset(InputStream is) {
        String json = null;
        try {

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kural, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();
        if (count > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void switchToFragment(Fragment targetFragment) {
        switchToFragment(targetFragment, false);
    }

    @Override
    public void switchToFragment(Fragment targetFragment, boolean addToBackstack) {
        switchToFragment(targetFragment, addToBackstack, null);
    }

    @Override
    public void switchToFragment(Fragment targetFragment, boolean addToBackStack, Bundle bundle) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        if (bundle != null) {
            targetFragment.setArguments(bundle);
        } else {
            if (targetFragment.getArguments() != null) {
                targetFragment.getArguments().clear();
            }
            targetFragment.setArguments(null);
        }
        fragmentTransaction.replace(R.id.home_fragment_container, targetFragment);

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
    }
}
