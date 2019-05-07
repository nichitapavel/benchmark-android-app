package es.ull.hpcg.benchmark;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import npb3.BMInOut.BMArgs;
import npb3.BT;


public class NASBTAsyncTask extends AsyncTask<String, Void, List<String>> {
    WeakReference<Activity> mWeakActivity;

    public NASBTAsyncTask(Activity activity) {
        mWeakActivity = new WeakReference<>(activity);
    }

    @Override
    protected List<String> doInBackground(String... values) {
        // TODO Change all this code with NAS IS code
        boolean serial = false;
        int threads = Integer.parseInt(values[1]);
        if (threads == 1) {
            serial = true;
        }

        BT bt = null;

        try{
            bt = new BT(values[0].charAt(0), threads, serial);
        }catch(OutOfMemoryError e){
            BMArgs.outOfMemoryMessage();
        }


        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add(bt.runULL(
                values[2],
                new HostInfo().getName()
        ));

        return listOfStrings;
    }


    @Override
    protected void onPreExecute() {
        Activity activity = mWeakActivity.get();
        if (activity != null){
            TextView text = activity.findViewById(R.id.status);
            text.setText(activity.getString(R.string.working));
            Spinner spinner = activity.findViewById(R.id.nas_is_threads);
            spinner.setEnabled(false);
            spinner = activity.findViewById(R.id.nas_is_class);
            spinner.setEnabled(false);
            spinner = activity.findViewById(R.id.nas_benchmark);
            spinner.setEnabled(false);
            Button compute = activity.findViewById(R.id.compute);
            compute.setEnabled(false);
            EditText input = activity.findViewById(R.id.http_endpoint);
            input.setEnabled(false);
            TextView matrixTiming = activity.findViewById(R.id.matrix_timing);
            matrixTiming.setText("");
        }
    }

    @Override
    protected void onPostExecute(List<String> s) {
        super.onPostExecute(s);
        Activity activity = mWeakActivity.get();
        if (activity != null) {
            TextView status = activity.findViewById(R.id.status);
            status.setText(activity.getString(R.string.done));
            Spinner spinner = activity.findViewById(R.id.nas_is_threads);
            spinner.setEnabled(true);
            spinner = activity.findViewById(R.id.nas_is_class);
            spinner.setEnabled(true);
            spinner = activity.findViewById(R.id.nas_benchmark);
            spinner.setEnabled(true);
            Button compute = activity.findViewById(R.id.compute);
            compute.setEnabled(true);
            EditText input = activity.findViewById(R.id.http_endpoint);
            input.setEnabled(true);
            TextView matrixTiming = activity.findViewById(R.id.matrix_timing);
            matrixTiming.setText(s.get(0));
            // matrixTiming.setEnabled(false);
            // matrixTiming.setTextColor(Color.rgb(0,0,0));
        }
    }
}
