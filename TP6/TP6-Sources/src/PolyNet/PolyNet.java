package PolyNet;

import java.util.HashSet;
import java.util.PriorityQueue;

public class PolyNet {
    private PolyNetNode[] nodes;

    public PolyNet(PolyNetNode[] nodes)
    {
        this.nodes = nodes;
    }

    // MST (using Prim's algorithm).
    public int computeTotalCableLength()
    {
        int totalCableLength = 0;
        PriorityQueue<PolyNetConnection> knownConnections = new PriorityQueue<>();
        HashSet<PolyNetNode> visitedNodes = new HashSet<>();
        
        PolyNetConnection currConn = new PolyNetConnection(nodes[0],0);
        
        do {
            for(PolyNetConnection c : currConn.getConnectedNode().getConnections()){
                if(!visitedNodes.contains(c.getConnectedNode()))
                    knownConnections.offer(c);
            }           
           
            totalCableLength += currConn.getDistance();
            visitedNodes.add(currConn.getConnectedNode());
            
            do{
                currConn = knownConnections.poll();
            } while(knownConnections.size() > 0 && visitedNodes.contains(currConn.getConnectedNode()));
     
        } while(visitedNodes.size()!=nodes.length || knownConnections.size() != 0);
       
        
        
        
        
        

        return totalCableLength;
    }
}
