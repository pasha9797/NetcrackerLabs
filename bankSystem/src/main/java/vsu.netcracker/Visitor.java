package vsu.netcracker;

import java.util.Random;

public class Visitor {
    private final int maxCash = 100;
    private int minServeTime = 5000;
    private int maxServeTime = 6000;

    private ActionType actionType;
    private int cash;
    private int serveTime;

    public Visitor() {
        Random random = new Random();
        if (random.nextBoolean())
            actionType = ActionType.PUT_CASH;
        else
            actionType = ActionType.TAKE_CASH;

        cash = random.nextInt(maxCash) + 1;
        serveTime = random.nextInt(maxServeTime - minServeTime) + minServeTime;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public int getCash() {
        return cash;
    }

    public int getServeTime() {
        return serveTime;
    }

    @Override
    public String toString() {
        return "{" +
                actionType +
                ", " + cash +
                '}';
    }
}
