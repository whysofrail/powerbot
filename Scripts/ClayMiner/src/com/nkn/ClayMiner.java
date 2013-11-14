package com.nkn;


import com.nkn.framework.Node;
import com.nkn.framework.nodes.banking.Bank;
import com.nkn.framework.nodes.mining.Mine;
import org.powerbot.event.MessageEvent;
import org.powerbot.event.MessageListener;
import org.powerbot.event.PaintListener;
import org.powerbot.script.Manifest;
import org.powerbot.script.PollingScript;
import org.powerbot.script.util.Random;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author : NKN
 */

@Manifest(authors = {"NKN"}, name = "Clay Miner", description = "Mines clay. Perfectly.")
public class ClayMiner extends PollingScript implements PaintListener, MessageListener{
    private Node[] nodes;
    private AtomicInteger clayMined = new AtomicInteger(0);
    @Override
    public void start() {
        nodes = new Node[] {new Mine(ctx), new Bank(ctx)};
        Globals.status = "Starting up";
        ctx.antipatterns.setEnabled(false);
        ctx.camera.setPitch(true);
    }
    @Override
    public int poll() {
        for (Node node : nodes){
           if(node.canExecute())
               node.execute();

        }
        return Random.nextInt(250,350);
    }

    @Override
    public void repaint(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawString("Status: " + Globals.status, 100, 100);
        g.drawString("Clay Mined: " + clayMined,100,130);

    }

    @Override
    public void messaged(MessageEvent messageEvent) {
        if(messageEvent.getMessage().contains("manage to mine") && messageEvent.getSender().equals("")){
           clayMined.incrementAndGet();
        }

    }
}
