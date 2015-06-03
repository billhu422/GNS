package edu.umass.cs.reconfiguration.json.reconfigurationpackets;

import org.json.JSONException;
import org.json.JSONObject;

import edu.umass.cs.nio.IntegerPacketType;
import edu.umass.cs.nio.Stringifiable;
import edu.umass.cs.reconfiguration.InterfaceReplicableRequest;
import edu.umass.cs.reconfiguration.reconfigurationutils.RequestParseException;

/**
@author V. Arun
 */
public class AckStartEpoch<NodeIDType> extends BasicReconfigurationPacket<NodeIDType> implements InterfaceReplicableRequest{

	private boolean coordType = false;
	public AckStartEpoch(NodeIDType initiator, String serviceName,
			int epochNumber, NodeIDType sender) {
		super(initiator, ReconfigurationPacket.PacketType.ACK_START_EPOCH, serviceName, epochNumber);
		this.setSender(sender);
	}
	public AckStartEpoch(JSONObject json, Stringifiable<NodeIDType> unstringer) throws JSONException {
		super(json, unstringer);
	}
	@Override
	public boolean needsCoordination() {
		return coordType;
	}
	@Override
	public void setNeedsCoordination(boolean b) {
		coordType = b;
	}
	@Override
	public IntegerPacketType getRequestType() throws RequestParseException {
		return this.getType();
	}
}
