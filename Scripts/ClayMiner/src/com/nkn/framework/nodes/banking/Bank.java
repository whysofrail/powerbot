package com.nkn.framework.nodes.banking;

import com.nkn.framework.Node;
import org.powerbot.script.methods.MethodContext;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @Author : NKN
 */
public class Bank extends Node {
    public Bank(MethodContext ctx) {
        super(ctx);
    }

    @Override
    public boolean canExecute() {

        return ctx.backpack.select().count() == 28;
    }

    @Override
    public void execute() {
       final ArrayList<Node> BANK_NODES = new ArrayList<>();
        Collections.addAll(BANK_NODES, new ToBank(ctx), new Open(ctx), new Deposit(ctx), new Close(ctx));

        for(Node node : BANK_NODES)
            node.run();


    }
}
