package com.nkn.framework;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;

/**
 * @Author : NKN
 */
public abstract class Node extends MethodProvider{
    public Node(MethodContext ctx) {
        super(ctx);
    }



    public abstract boolean canExecute();
    public abstract void execute();
}
