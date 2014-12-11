package edu.umass.cs.gns.paxos;

import edu.umass.cs.gns.main.GNS;

import edu.umass.cs.gns.nio.InterfaceNodeConfig;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * User: abhigyan
 * Date: 6/29/13
 * Time: 7:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class PaxosNodeConfig implements InterfaceNodeConfig<Integer> {

  private  HashMap<Integer, NodeInfo> nodesInfo;

  /**
   * Stores list of nodes, their IP address, and port numbers
   * @param nodeConfigFile
   */
  public PaxosNodeConfig(String nodeConfigFile) {
    readConfigFile(nodeConfigFile);
  }

  public PaxosNodeConfig(int numNodes, int startingPort) {
    nodesInfo = new HashMap<Integer, NodeInfo>();
    for (int i = 0; i < numNodes; i++) {
      try {
        nodesInfo.put(i, new NodeInfo(i, InetAddress.getByName("localhost"), startingPort + i));
      } catch (UnknownHostException e) {
        e.printStackTrace();
      }
    }
  }



  private void readConfigFile(String configFile) {
    nodesInfo = new HashMap<Integer, NodeInfo>();
    try {
      BufferedReader br = new BufferedReader(new FileReader(configFile));

      while (true) {
        String line = br.readLine();
        if (line == null) break;
        String[] tokens = line.split("\\s+");
        nodesInfo.put(Integer.parseInt(tokens[0]),
                new NodeInfo(Integer.parseInt(tokens[0]),
                        InetAddress.getByName(tokens[1]),
                        Integer.parseInt(tokens[2])));
      }
    } catch (FileNotFoundException e) {
      GNS.getLogger().severe(" EXIT: Config file not found.");
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
      System.exit(2);
    } catch (IOException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }
  }

  @Override
  public boolean nodeExists(Integer ID) {
    return  nodesInfo.containsKey(ID);
  }

//  @Override
//  public int getNodeCount() {
//    return nodesInfo.size();
//  }

  @Override
  public Set<Integer> getNodeIDs() {
    return nodesInfo.keySet();
  }

  @Override
  public InetAddress getNodeAddress(Integer ID) {
    if (nodeExists(ID)) return  nodesInfo.get(ID).getAddress();
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public int getNodePort(Integer ID) {
    if (nodeExists(ID)) return  nodesInfo.get(ID).getPort();
    return -1;  //To change body of implemented methods use File | Settings | File Templates.
  }

@Override
public Integer valueOf(String nodeAsString) {
	return Integer.valueOf(nodeAsString);
}

  @Override
  public Set<Integer> getValuesFromStringSet(Set<String> strNodes) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Set<Integer> getValuesFromJSONArray(JSONArray array) throws JSONException {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}



class NodeInfo {
  private InetAddress address;
  private int port;
  private int ID;

  public NodeInfo(int ID, InetAddress address, int port) {
    this.ID = ID;
    this.port = port;
    this.address = address;
  }

  public int getPort() {
    return port;
  }

  public int getID() {
    return ID;
  }

  public InetAddress getAddress() {
    return address;
  }

}