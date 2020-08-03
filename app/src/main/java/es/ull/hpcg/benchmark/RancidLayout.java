package es.ull.hpcg.benchmark;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;


public class RancidLayout extends Fragment {
    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        final View view = inflater.inflate(R.layout.rancid, container, false);
        final Button compute = view.findViewById(R.id.compute);
        compute.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        compute(view);
                    }
                }
        );

        return view;
    }

    public void compute(View view) {
        RancidAsyncTask taskIS = new RancidAsyncTask(getActivity());
        taskIS.execute();
    }
}
