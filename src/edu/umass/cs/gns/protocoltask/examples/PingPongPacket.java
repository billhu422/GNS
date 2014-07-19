package edu.umass.cs.gns.protocoltask.examples;

import org.json.JSONException;
import org.json.JSONObject;

import edu.umass.cs.gns.nsdesign.packet.Packet;
import edu.umass.cs.gns.protocoltask.json.ProtocolPacket;

/**
 * @author V. Arun
 */
public class PingPongPacket extends ProtocolPacket { // BasicPacket implements ProtocolEvent<Packet.PacketType,Long> {
	public static final String FIELD1 = "FIELD1";
	public static final String COUNTER = "COUNTER";
	public static final String SENDER = "SENDER";
	public static final String INITIATOR = "INITIATOR";
	public static final String KEY = "KEY";

	public final String field1 = "PingPong"; // unnecessary field
	private int counter = 0;

	public PingPongPacket(int sid, int iid, Packet.PacketType t) {
		super(sid, iid);
		this.setType(t);
	}

	public PingPongPacket(JSONObject json) throws JSONException {
		super(json);
		this.setType(Packet.getPacketType(json));
		this.counter = (json.has(COUNTER) ? json.getInt(COUNTER) : 0);
	}

	public void incrCounter() {
		this.counter++;
	}

	public int getCounter() {
		return this.counter;
	}

	public int flip(int rcvr) { // flip sender/reciever and ping/pong type
		if (this.getType() == Packet.PacketType.TEST_PING)
			this.setType(Packet.PacketType.TEST_PONG);
		else if (this.getType() == Packet.PacketType.TEST_PONG)
			this.setType(Packet.PacketType.TEST_PING);
		return super.flip(rcvr);
	}

	@Override
	public JSONObject toJSONObjectImpl() throws JSONException {
		JSONObject json = new JSONObject();
		json.put(FIELD1, this.field1);
		json.put(COUNTER, this.counter);
		return json;
	}

	@Override
	public Object getMessage() {
		return this;
	}
}