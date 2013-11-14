package com.nkn.framework.nodes.banking;

import com.nkn.Globals;
import com.nkn.framework.Node;
import org.powerbot.script.methods.MethodContext;

/**
 * @Author : NKN
 */
public class Close extends Node {
    public Close(MethodContext ctx) {
        super(ctx);
    }

    @Override
    public boolean canExecute() {
        return ctx.bank.isOpen() && ctx.backpack.select().count() != 28 && !ctx.players.local().isInMotion();
    }

    @Override
    public void execute() {
        Globals.status = "Closing bank";
        ctx.bank.close();
    }
}
