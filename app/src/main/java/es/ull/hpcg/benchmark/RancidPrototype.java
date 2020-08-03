package es.ull.hpcg.benchmark;

import java.util.ArrayList;
import java.util.List;

import es.ull.pcg.hpc.benchmark.Parameters;
import es.ull.pcg.hpc.benchmark.benchmark.BenchmarkImplementation;
import npb3.BT;

public class RancidPrototype extends BenchmarkImplementation {
    protected BT bt = new BT('W', 1, true);

    public RancidPrototype (String title) {
        super(title);
    }

    @Override
    public boolean handleException (RuntimeException e) {
        return true;
    }

    @Override
    public void instrumentedRun() {
        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add(bt.runULL(
                "http://localhost",
                new HostInfo().getName()
        ));

    }
}
