package com.nkn.framework.nodes.mining;

import com.nkn.framework.Node;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Condition;
import org.powerbot.script.wrappers.GameObject;

import java.util.concurrent.Callable;

/**
 * @Author : NKN
 */
public class MineOre extends Node {
    private final int[] ROCK_IDS;
    public MineOre(MethodContext ctx, int[] rockIDs) {
        super(ctx);
        ROCK_IDS = rockIDs;
    }

    @Override
    public boolean canExecute() {
        return ctx.backpack.select().count() != 28 && !ctx.objects.select().id(ROCK_IDS).nearest().isEmpty() && !ctx.players.local().isInMotion();
    }

    @Override
    public void execute() {
        for(final GameObject rock: ctx.objects){
            if(rock.isValid() && rock.interact("Mine")){
                if(Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() {
                        return !rock.isValid();
                    }
                }, 350, 10))
                    break;

            }
        }
    }
}
