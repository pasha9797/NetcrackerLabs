package vsu.netcracker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ArrayBlockingQueue;

public class Operator extends Thread {
    private ArrayBlockingQueue<Visitor> queue;
    private Visitor current;
    private CashBox cashBox;
    private final int queueSize = 10;
    private Logger log = LogManager.getLogger(this.getClass());

    public void putVisitor(Visitor visitor) {
        try {
            queue.put(visitor);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getVisitorNumber() {
        return queue.size();
    }


    @Override
    public void run() {
        while (true) {
            serve();
        }
    }

    private void serve() {
        try {
            if (!queue.isEmpty()) {
                log.debug("Queue is not empty, begin to serve");
                current = queue.peek();
                log.debug("Going to sleep for visitor " + current.toString());
                Thread.sleep(current.getServeTime());
                synchronized (cashBox) {
                    log.debug("Going to serve visitor " + current.toString() + " cashbox is " + cashBox.getCash());
                    switch (current.getActionType()) {
                        case PUT_CASH:
                            cashBox.putCash(current.getCash());
                            cashBox.notifyAll();
                            break;
                        case TAKE_CASH:
                            while (cashBox.getCash() - current.getCash() < 0)
                                cashBox.wait();
                            cashBox.takeCash(current.getCash());
                            /*else {
                                log.info("Not enough cash for visitor " + current.toString());
                            }*/
                            break;
                    }
                    queue.take();
                    log.info("Finished serving visitor " + current.toString() + " cashbox is " + cashBox.getCash());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Operator(CashBox cashBox) {
        queue = new ArrayBlockingQueue<Visitor>(queueSize);
        this.cashBox = cashBox;
    }
}
