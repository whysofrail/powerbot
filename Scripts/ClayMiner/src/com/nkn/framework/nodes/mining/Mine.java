package com.nkn.framework.nodes.mining;

import com.nkn.framework.Node;
import org.powerbot.script.methods.MethodContext;


import java.util.ArrayList;
import java.util.Collections;


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
        final ArrayList<Node> NODES = new ArrayList<>();
        Collections.addAll(NODES,new MineOre(ctx,ROCK_IDS),new ToMine(ctx,ROCK_IDS));
        for(Node node : NODES )
            node.run();

    }
}
