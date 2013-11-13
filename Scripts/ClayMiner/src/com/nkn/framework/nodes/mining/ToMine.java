package com.nkn.framework.nodes.mining;

import com.nkn.framework.Node;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Condition;
import org.powerbot.script.util.Random;
import org.powerbot.script.wrappers.Tile;

import java.util.concurrent.Callable;

/**
 * @Author : NKN
 */
public class ToMine extends Node {
    private final int[] ROCK_IDS;
    public ToMine(MethodContext ctx, int[] rockIDs) {
        super(ctx);
        ROCK_IDS = rockIDs;
    }

    @Override
    public boolean canExecute() {
        return ctx.backpack.select().count() != 28 && ctx.objects.select().id(ROCK_IDS).within(6).isEmpty() ;
    }

    @Override
    public void execute() {
        Tile[] mineLocations = {new Tile(3080,3400),new Tile(3081,3399),new Tile(3082,3400), new Tile(3083,3399)};
        Tile mineTile = mineLocations[Random.nextInt(0,mineLocations.length )];
        if(ctx.movement.stepTowards(mineTile))
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return ctx.movement.getDistance(ctx.players.local(), ctx.movement.getDestination()) < 7;
                }
            }, 550, 20);
    }
}
