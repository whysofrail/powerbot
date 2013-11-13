package com.nkn;


import com.nkn.framework.Node;
import com.nkn.framework.nodes.banking.Bank;
import com.nkn.framework.nodes.mining.Mine;
import org.powerbot.script.Manifest;
import org.powerbot.script.PollingScript;
import org.powerbot.script.util.Random;
import org.powerbot.script.wrappers.Interactive;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @Author : NKN
 */

@Manifest(authors = {"NKN"}, name = "Clay Miner", description = "Mines clay. Perfectly.")
public class ClayMiner extends PollingScript{
    private final ArrayList<Node> NODES= new ArrayList<>();
    @Override
    public void start(){
        Collections.addAll(NODES, new Mine(ctx), new Bank(ctx));
        ctx.antipatterns.setEnabled(false);
    }
    @Override
    public int poll() {
        for (Node node : NODES){
            if(node.run())
                return Random.nextInt(250,300);
        }
        return 0;
    }

}
