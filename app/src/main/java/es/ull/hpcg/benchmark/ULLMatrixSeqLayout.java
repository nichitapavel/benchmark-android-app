package es.ull.hpcg.benchmark;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class ULLMatrixSeqLayout extends Fragment {
    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        final View view = inflater.inflate(R.layout.ull_matrix_seq, container, false);
        EditText integerText = view.findViewById(R.id.matrix_size);
        integerText.setTransformationMethod(null);
        integerText = view.findViewById(R.id.matrix_module);
        integerText.setTransformationMethod(null);

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
        EditText matrixSizeEditText = view.findViewById(R.id.matrix_size);
        String matrixSize = matrixSizeEditText.getText().toString();

        EditText matrixModuleEditText = view.findViewById(R.id.matrix_module);
        String matrixModule = matrixModuleEditText.getText().toString();

        EditText httpEndpointEditText = view.findViewById(R.id.http_endpoint);
        String httpEndpoint = httpEndpointEditText.getText().toString();

        Switch printSwitch = view.findViewById(R.id.matrix_print);

        boolean matrixPrint = printSwitch.isChecked();
        String asyncPrint = matrixPrint ? "true" : "false";

        // InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        // imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        ULLMatrixSeqAsyncTask task = new ULLMatrixSeqAsyncTask(getActivity());
        task.execute(matrixSize, matrixModule, asyncPrint, httpEndpoint);
    }

}
