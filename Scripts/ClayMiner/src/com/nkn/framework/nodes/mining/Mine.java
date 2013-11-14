package com.nkn.framework.nodes.mining;

import com.nkn.framework.Node;
import org.powerbot.script.methods.MethodContext;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * @Author : NKN
 */
public class Mine extends Node {
    public Mine(MethodContext ctx) {
        super(ctx);
    }

    @Override
    public boolean canExecute() {
        return ctx.backpack.select().count() != 28;
    }

    @Override
    public void execute() {
        final int[] ROCK_IDS = {10578,10579,10577};
        List<Node> mineNodes = Arrays.asList(new MineOre(ctx,ROCK_IDS),new ToMine(ctx,ROCK_IDS));
        for(Node node : mineNodes )
            if(node.canExecute())
                node.execute();

    }
}
