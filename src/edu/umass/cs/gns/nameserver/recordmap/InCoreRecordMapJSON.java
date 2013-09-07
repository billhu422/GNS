package edu.umass.cs.gns.nameserver.recordmap;

import edu.umass.cs.gns.main.GNS;
import edu.umass.cs.gns.nameserver.NameRecord;
import edu.umass.cs.gns.nameserver.ValuesMap;
import edu.umass.cs.gns.nameserver.fields.Field;
import edu.umass.cs.gns.nameserver.recordExceptions.FieldNotFoundException;
import edu.umass.cs.gns.nameserver.recordExceptions.RecordNotFoundException;
import edu.umass.cs.gns.nameserver.replicacontroller.ReplicaControllerRecord;
import edu.umass.cs.gns.util.ConfigFileInfo;
import edu.umass.cs.gns.util.HashFunction;
import edu.umass.cs.gns.util.JSONUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

/**
 * Stores GUID, KEY, VALUE triples
 *
 * @author westy
 */
public class InCoreRecordMapJSON extends BasicRecordMap {
  private static final String NAME = NameRecord.NAME.getFieldName();

  private Map<String, JSONObject> recordMap;

  public InCoreRecordMapJSON() {
    recordMap = new HashMap<String, JSONObject>();
  }

  @Override
  public void addNameRecord(JSONObject json) {
    try {
      recordMap.put(json.getString(NAME), json);
    } catch (JSONException e) {
      GNS.getLogger().severe("Error getting json record: " + e);
    }
  }

  @Override
  public void removeNameRecord(String name) {
    recordMap.remove(name);
  }

  @Override
  public boolean containsName(String name) {
    return recordMap.containsKey(name);
  }

  @Override
  public void reset() {
    recordMap.clear();
  }

  @Override
  public void updateNameRecordListValue(String name, String key, ArrayList<String> value) {
    if (containsName(name)) {
      try {
        recordMap.get(name).put(key, value);
        //System.out.println("&&&&"+recordMap.get(name).toString());
      } catch (JSONException e) {
        GNS.getLogger().severe("Error updating json record: " + e);
      }
    }
  }

  @Override
  public void updateNameRecordListValueInt(String name, String key, Set<Integer> value) {
    if (containsName(name)) {
      try {
        recordMap.get(name).put(key, value);
        //System.out.println("&&&&"+recordMap.get(name).toString());
      } catch (JSONException e) {
        GNS.getLogger().severe("Error updating json record: " + e);
      }
    }
  }

  @Override
  public void updateNameRecordFieldAsString(String name, String key, String string) {
    if (containsName(name)) {
      try {
        recordMap.get(name).put(key, string);
        //System.out.println("&&&&"+recordMap.get(name).toString());
      } catch (JSONException e) {
        GNS.getLogger().severe("Error updating json record: " + e);
      }
    }
  }
  
  @Override
  public void updateNameRecordFieldAsCollection(String name, String key, Collection list) {
    if (containsName(name)) {
      try {
        recordMap.get(name).put(key, list);
        //System.out.println("&&&&"+recordMap.get(name).toString());
      } catch (JSONException e) {
        GNS.getLogger().severe("Error updating json record: " + e);
      }
    }
  }
  
  @Override
  public void updateNameRecordFieldAsMap(String name, String key, Map map) {
    if (containsName(name)) {
      try {
        recordMap.get(name).put(key, map);
        //System.out.println("&&&&"+recordMap.get(name).toString());
      } catch (JSONException e) {
        GNS.getLogger().severe("Error updating json record: " + e);
      }
    }
  }

  @Override
  public String getNameRecordField(String name, String key) {
    if (containsName(name)) {
      try {
        return recordMap.get(name).getString(key);
      } catch (JSONException e) {
        GNS.getLogger().severe("Error updating json record: " + e);
        return null;
      }
    } else {
      return null;
    }
  }

  @Override
  public ArrayList<String> getNameRecordFields(String name, ArrayList<String> key) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Set<String> getAllRowKeys() {
    return recordMap.keySet();
  }

  @Override
  public Set<String> getAllColumnKeys(String name) {
    if (!containsName(name)) {
      try {
        return JSONUtils.JSONArrayToSetString(recordMap.get(name).names());
      } catch (JSONException e) {
        GNS.getLogger().severe("Error updating json record: " + e);
        return null;
      }
    } else {
      return null;
    }
  }
  
  @Override
  public NameRecord getNameRecordLazy(String name) {
   throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public NameRecord getNameRecordLazy(String name, ArrayList<String> keys) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public HashMap<Field, Object> lookup(String name, Field nameField, ArrayList<Field> fields1) throws RecordNotFoundException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public HashMap<Field, Object> lookup(String name, Field nameField, ArrayList<Field> fields1, Field valuesMapField, ArrayList<Field> valuesMapKeys) throws RecordNotFoundException {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void update(String name, Field nameField, ArrayList<Field> fields1, ArrayList<Object> values1) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void update(String name, Field nameField, ArrayList<Field> fields1, ArrayList<Object> values1, Field valuesMapField, ArrayList<Field> valuesMapKeys, ArrayList<Object> valuesMapValues) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void increment(String name, ArrayList<Field> fields1, ArrayList<Object> values1) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void increment(String name, ArrayList<Field> fields1, ArrayList<Object> values1, Field votesMapField, ArrayList<Field> votesMapKeys, ArrayList<Object> votesMapValues) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Object getIterator(Field nameField, ArrayList<Field> fields) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public HashMap<Field, Object> next(Object iterator, Field nameField, ArrayList<Field> fields) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void returnIterator() {
    return;  //To change body of implemented methods use File | Settings | File Templates.
  }

  //
  // THESE WILL BE DEPRECATED
  //
  @Override
  public NameRecord getNameRecord(String name) {
    if (containsName(name)) {
      try {
        return new NameRecord(recordMap.get(name));
      } catch (JSONException e) {
        GNS.getLogger().severe("Error getting json record: " + e);
        return null;
      }
    } else {
      //System.out.println("&&&& NOT FOUND: " + name);
      return null;
    }
  }

  @Override
  public void addNameRecord(NameRecord recordEntry) {
    try {
      recordMap.put(recordEntry.getName(), recordEntry.toJSONObject());
    } catch (JSONException e) {
      GNS.getLogger().severe("Error getting json record: " + e);
    } catch (FieldNotFoundException e) {
      GNS.getLogger().severe("Field not found Exception: " + e.getMessage());
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }
  }

  @Override
  public Set<NameRecord> getAllNameRecords() {
    Set<NameRecord> result = new HashSet();
    for (Map.Entry<String, JSONObject> entry : recordMap.entrySet()) {
      try {
        result.add(new NameRecord(entry.getValue()));
      } catch (JSONException e) {
        GNS.getLogger().severe("Error getting json record: " + e);
      }
    }
    return result;
  }

  @Override
  public void updateNameRecord(NameRecord recordEntry) {
    addNameRecord(recordEntry);
  }
  
  @Override
  public ReplicaControllerRecord getNameRecordPrimary(String name) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  @Override
  public ReplicaControllerRecord getNameRecordPrimaryLazy(String name) {
     throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void addNameRecordPrimary(ReplicaControllerRecord recordEntry) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void updateNameRecordPrimary(ReplicaControllerRecord recordEntry) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Set<ReplicaControllerRecord> getAllPrimaryNameRecords() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  //
  // TEST CODE
  //
  public static void main(String[] args) throws Exception, FieldNotFoundException {
    test();
  }

  private static NameRecord createNameRecord(String name, String key, String value) throws Exception {
    ValuesMap valuesMap = new ValuesMap();
    valuesMap.put(key,new ArrayList(Arrays.asList(value)));
    HashSet<Integer> x = new HashSet<Integer>();
    x.add(0);
    x.add(1);
    x.add(2);
    return new NameRecord(name, x, name+"-2",valuesMap);
  }

  private static void test() throws Exception, FieldNotFoundException {
    ConfigFileInfo.readHostInfo("ns1", 4);
    HashFunction.initializeHashFunction();
    InCoreRecordMapJSON recordMap = new InCoreRecordMapJSON();
    NameRecord nameRecord = createNameRecord("1A434C0DAA0B17E48ABD4B59C632CF13501C7D24", "FRANK", "XYZ");
    recordMap.addNameRecord(nameRecord);
    nameRecord = recordMap.getNameRecord("1A434C0DAA0B17E48ABD4B59C632CF13501C7D24");
    System.out.println(nameRecord);
    if (nameRecord != null) {
      System.out.println(nameRecord.getKey("_GNS_account_info"));
      System.out.println(nameRecord.getKey("_GNS_guid_info"));
    }
    System.out.println(recordMap.getNameRecordField("1A434C0DAA0B17E48ABD4B59C632CF13501C7D24", "FRANK"));
    recordMap.updateNameRecordSingleValue("1A434C0DAA0B17E48ABD4B59C632CF13501C7D24", "FRANK", "SLACKER");
    System.out.println(recordMap.getNameRecordField("1A434C0DAA0B17E48ABD4B59C632CF13501C7D24", "FRANK"));
    System.out.println(recordMap.getAllRowKeys());
    nameRecord = recordMap.getNameRecord("1A434C0DAA0B17E48ABD4B59C632CF13501C7D24");
    System.out.println(nameRecord);
    if (nameRecord != null) {
      System.out.println(nameRecord.getKey("FRANK"));
    }
  }
}
