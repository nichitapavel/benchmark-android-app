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


public class NASLayout extends Fragment {
    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        final View view = inflater.inflate(R.layout.nas, container, false);
        final Button compute = view.findViewById(R.id.compute);
        compute.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        compute(view);
                    }
                }
        );

        // Populate the dropdown spinner with benchmark options
        Spinner spinnerClass = view.findViewById(R.id.nas_benchmark);
        ArrayAdapter<CharSequence> adapterClass = ArrayAdapter.createFromResource(
                getContext(),
                R.array.nas_benchmarks_options,
                android.R.layout.simple_spinner_item
        );
        adapterClass.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClass.setAdapter(adapterClass);

        // Populate the dropdown spinner with classes size
        // TODO not all algorithms support same classes, should be a bit dynamic
        spinnerClass = view.findViewById(R.id.nas_is_class);
        adapterClass = ArrayAdapter.createFromResource(
                getContext(),
                R.array.nas_class_options,
                android.R.layout.simple_spinner_item
        );
        adapterClass.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClass.setAdapter(adapterClass);

        // Populate the dropdown spinner with number of available threads
        // List is dynamically created, depending on number of available cores
        int cores = Runtime.getRuntime().availableProcessors();
        List<Integer> numberOfCores = new ArrayList<>();
        for (int i = 1; i <= cores; i++) {
            numberOfCores.add(i);
        }
        ArrayAdapter<Integer> adapterThreads = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_spinner_item,
                numberOfCores
        );
        Spinner spinnerThreads = view.findViewById(R.id.nas_is_threads);
        adapterThreads.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerThreads.setAdapter(adapterThreads);

        return view;
    }

    public void compute(View view) {
        Spinner spinner = view.findViewById(R.id.nas_is_class);
        String classText = spinner.getSelectedItem().toString();

        spinner  = view.findViewById(R.id.nas_is_threads);
        String threadText = spinner.getSelectedItem().toString();

        spinner  = view.findViewById(R.id.nas_benchmark);
        String benchmarkText = spinner.getSelectedItem().toString();

        EditText httpEndpointEditText = view.findViewById(R.id.http_endpoint);
        String httpEndpoint = httpEndpointEditText.getText().toString();

        switch (benchmarkText) {
            case "IS (Integer Sort)":
                NASISAsyncTask taskIS = new NASISAsyncTask(getActivity());
                taskIS.execute(classText, threadText, httpEndpoint);
                break;
            case "CG (Conjugate Gradient)":
                break;
            case "MG (Multi-Grid on a sequence of meshes)":
                NASMGAsyncTask taskMG = new NASMGAsyncTask(getActivity());
                taskMG.execute(classText, threadText, httpEndpoint);
                break;
            case "FT (discrete 3D fast Fourier Transform)":
                break;
            case "BT (Block Tri-diagonal solver)":
                break;
            case "SP (Scalar Penta-diagonal solver)":
                break;
            case "LU (Lower-Upper Gauss-Seidel solver)":
                break;
        }
    }
}
