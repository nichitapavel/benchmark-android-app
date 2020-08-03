package es.ull.hpcg.benchmark;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import es.ull.pcg.hpc.benchmark.Parameters;
import matrix.lib.AMatrix;
import matrix.lib.HTTPData;
import matrix.lib.MatrixFloat;
import matrix.lib.Operation;
import matrix.lib.TimeController;


public class RancidAsyncTask extends AsyncTask<String, Void, List<String>> {
    WeakReference<Activity> mWeakActivity;

    public RancidAsyncTask(Activity activity) {
        mWeakActivity = new WeakReference<>(activity);
    }

    @Override
    protected List<String> doInBackground(String... values) {
        StringBuilder message = new StringBuilder();

        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add(message.toString());
        RancidPrototype rancidPrototype = new RancidPrototype("Rancid BT");
        rancidPrototype.instrumentedRun();

        return listOfStrings;
    }

    @Override
    protected void onPreExecute() {
        Activity activity = mWeakActivity.get();
        Parameters param = new Parameters("Rancid");
        param.addParameter("Height", 1000000);
        param.addParameter("Width", 1000000);

        if (activity != null){
            TextView text = activity.findViewById(R.id.status);
            text.setText(activity.getString(R.string.working));
        }
    }

    @Override
    protected void onPostExecute(List<String> s) {
        super.onPostExecute(s);
        Activity activity = mWeakActivity.get();
        if (activity != null){
            TextView status = activity.findViewById(R.id.status);
            status.setText(activity.getString(R.string.done));
        }
    }
}
