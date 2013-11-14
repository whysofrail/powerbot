package com.nkn.framework.nodes.banking;

import com.nkn.Globals;
import com.nkn.framework.Node;
import org.powerbot.script.methods.MethodContext;

/**
 * @Author : NKN
 */
public class Open extends Node {
    public Open(MethodContext ctx) {
        super(ctx);
    }

    @Override
    public boolean canExecute() {
        return !ctx.bank.isOpen() && !ctx.players.local().isInMotion();
    }

    @Override
    public void execute() {
        Globals.status = "Opening bank";
        ctx.bank.open();
    }
}
