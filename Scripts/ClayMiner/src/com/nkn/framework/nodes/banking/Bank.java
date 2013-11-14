package com.nkn.framework.nodes.banking;


import com.nkn.framework.Node;
import org.powerbot.script.methods.MethodContext;



/**
 * @Author : NKN
 */
public class Bank extends Node {
    private Node[] bankNodes;
    public Bank(MethodContext ctx) {
        super(ctx);
        bankNodes = new Node[] {new ToBank(ctx), new Open(ctx), new Deposit(ctx), new Close(ctx)};
    }

    @Override
    public boolean canExecute() {

        return ctx.backpack.select().count() == 28;
    }

    @Override
    public void execute() {

        for(Node node : bankNodes)
            if(node.canExecute())
                node.execute();


    }
}
