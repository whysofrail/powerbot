package com.nkn.framework.nodes.banking;

import com.nkn.framework.Node;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Condition;
import org.powerbot.script.util.Random;
import org.powerbot.script.wrappers.*;

import java.util.concurrent.Callable;

/**
 * @Author : NKN
 */
public class ToBank extends Node {
    private final Tile BANK_TILE = new Tile(Random.nextInt(3093,3094),Random.nextInt(3495,3489));
    public ToBank(MethodContext ctx) {
        super(ctx);
    }

    @Override
    public boolean canExecute() {
        return ctx.backpack.select().count() == 28 && !ctx.bank.isOnScreen();
    }

    @Override
    public void execute() {
        if(ctx.movement.stepTowards(BANK_TILE))
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return ctx.movement.getDistance(ctx.players.local(),ctx.movement.getDestination()) < 7;
                }
            },550,20);




    }
}
