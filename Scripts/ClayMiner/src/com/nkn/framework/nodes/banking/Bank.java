package com.nkn.framework.nodes.banking;


import com.nkn.framework.Node;
import org.powerbot.script.methods.MethodContext;


import java.util.Arrays;
import java.util.List;

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
        List<Node> bankNodes = Arrays.asList(new ToBank(ctx), new Open(ctx), new Deposit(ctx), new Close(ctx));
        for(Node node : bankNodes)
            if(node.canExecute())
                node.execute();


    }
}
