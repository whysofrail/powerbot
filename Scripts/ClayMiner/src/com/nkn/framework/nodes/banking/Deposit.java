package com.nkn.framework.nodes.banking;

import com.nkn.framework.Node;
import org.powerbot.script.methods.*;
import org.powerbot.script.methods.Bank;

/**
 * @Author : NKN
 */
public class Deposit extends Node {
    private final int[] BANK_ITEM_IDS = {434,435,1617,1618,1619,1620,1621,1622,1623,1624,1625,1626,1627,1628,1629,1630,1631,1632};
    public Deposit(MethodContext ctx) {
        super(ctx);
    }

    @Override
    public boolean canExecute() {
        return ctx.bank.isOpen() && ctx.backpack.select().count() == 28 && !ctx.players.local().isInMotion();
    }

    @Override
    public void execute() {
        for (int BANK_ITEM_ID : BANK_ITEM_IDS) {
            ctx.bank.deposit(BANK_ITEM_ID, Bank.Amount.ALL);
        }

    }
}
