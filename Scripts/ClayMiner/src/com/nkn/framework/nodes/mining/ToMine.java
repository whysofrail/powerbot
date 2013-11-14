package com.nkn.framework.nodes.mining;

import com.nkn.Globals;
import com.nkn.framework.Node;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.Movement;
import org.powerbot.script.util.Condition;
import org.powerbot.script.util.Random;
import org.powerbot.script.wrappers.LocalPath;
import org.powerbot.script.wrappers.Path;
import org.powerbot.script.wrappers.Tile;
import org.powerbot.script.wrappers.TileMatrix;

import java.util.EnumSet;
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
        Globals.status = "Walking to mine";
        if(!ctx.movement.isRunning() && ctx.movement.getEnergyLevel() > 30)
            ctx.movement.setRunning(true);
        Tile[] mineLocations = {new Tile(3080,3400),new Tile(3081,3399),new Tile(3082,3400), new Tile(3083,3399)};
        Tile mineTile = mineLocations[Random.nextInt(0,mineLocations.length )];
        LocalPath path = ctx.movement.findPath(ctx.movement.getClosestOnMap(mineTile).randomize(2,2));      //Located on two different map chunks.
        path.traverse();
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.movement.getDistance(ctx.players.local(),ctx.movement.getDestination()) < 14;
            }
        },250,20);
    }
}
