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

/**
 * @Author : NKN
 */

@Manifest(authors = {"NKN"}, name = "Clay Miner", description = "Mines clay. Perfectly.")
public class ClayMiner extends PollingScript implements PaintListener, MessageListener{
    private final ArrayList<Node> NODES = new ArrayList<>();
    @Override
    public void start() {
        Collections.addAll(NODES, new Mine(ctx), new Bank(ctx));
        Globals.status = "Starting up";
        ctx.antipatterns.setEnabled(false);
        ctx.camera.setPitch(true);
    }
    @Override
    public int poll() {
        for (Node node : NODES){
            if(node.run())
                return Random.nextInt(250,300);
        }
        return 0;
    }

    @Override
    public void repaint(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawString("Status: " + Globals.status, 100, 100);
        g.drawString("Clay Mined: " + Integer.toString(Globals.clayMined),100,130);

    }

    @Override
    public void messaged(MessageEvent messageEvent) {
        if(messageEvent.getMessage().contains("manage to mine") && messageEvent.getSender().equals("")){
            Globals.clayMined++;
        }

    }
}
