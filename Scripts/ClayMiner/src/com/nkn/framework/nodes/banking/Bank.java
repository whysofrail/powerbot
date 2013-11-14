package com.nkn.framework.nodes.banking;

import com.nkn.Globals;
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
        ArrayList<Node> bankNodes = new ArrayList<>();
        Collections.addAll(bankNodes, new ToBank(ctx), new Open(ctx), new Deposit(ctx), new Close(ctx));

        for(Node node : bankNodes)
            node.run();


    }
}
