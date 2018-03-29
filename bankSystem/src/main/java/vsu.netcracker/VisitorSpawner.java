package vsu.netcracker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Random;

public class VisitorSpawner extends Thread {
    private List<Operator> operatorPool;
    private final int minSpawnInterval = 300;
    private final int maxSpawnInterval = 500;
    private Visitor visitor;
    private Logger log = LogManager.getLogger(this.getClass());

    @Override
    public void run() {
        while (true)
            spawn();
    }

    private void spawn() {
        try {
            Random random = new Random();
            int operatorID = random.nextInt(operatorPool.size());
            visitor = new Visitor();
            operatorPool.get(operatorID).putVisitor(visitor);
            log.info("New visitor spawned: " + visitor.toString() + " to operator " + operatorID + ". Now queueing " + operatorPool.get(operatorID).getVisitorNumber());
            Thread.sleep(random.nextInt(maxSpawnInterval - minSpawnInterval) + minSpawnInterval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public VisitorSpawner(List<Operator> operatorPool) {
        this.operatorPool = operatorPool;
    }
}
