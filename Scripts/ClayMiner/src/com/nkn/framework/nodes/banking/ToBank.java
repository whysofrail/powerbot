package com.nkn.framework.nodes.banking;

import com.nkn.ClayMiner;
import com.nkn.Globals;
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
        Globals.status = "Walking to bank";
        if(!ctx.movement.isRunning() && ctx.movement.getEnergyLevel() > 30)
            ctx.movement.setRunning(true);
        LocalPath path = ctx.movement.findPath(ctx.movement.getClosestOnMap(BANK_TILE).randomize(2,2));
        path.traverse();
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.movement.getDistance(ctx.players.local(), ctx.movement.getDestination()) < 14;
            }
        }, 250, 20);




    }
}
