package vsu.netcracker;


import java.util.ArrayList;
import java.util.List;

public class Bank {
    private final int initialCash = 100;
    private final int operatorsNumber = 10;

    private CashBox cashBox;
    private List<Operator> operatorPool;
    private VisitorSpawner spawner;

    public Bank() {
        cashBox = new CashBox(initialCash);
        Operator operator;
        operatorPool = new ArrayList<Operator>();
        for (int i = 0; i < operatorsNumber; i++) {
            operator = new Operator(cashBox);
            operatorPool.add(operator);
            operator.start();
        }

        spawner = new VisitorSpawner(operatorPool);
        spawner.start();

    }
}
