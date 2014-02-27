/*
 * Copyright (C) 2014
 * University of Massachusetts
 * All Rights Reserved 
 *
 * Initial developer(s): Westy.
 */
package edu.umass.cs.gns.commands.account;

import edu.umass.cs.gns.clientsupport.AccountAccess;
import edu.umass.cs.gns.clientsupport.ClientUtils;
import static edu.umass.cs.gns.clientsupport.Defs.*;
import edu.umass.cs.gns.clientsupport.FieldMetaData;
import edu.umass.cs.gns.clientsupport.MetaDataTypeName;
import edu.umass.cs.gns.commands.CommandModule;
import edu.umass.cs.gns.commands.GnsCommand;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author westy
 */
public class RegisterAccount extends GnsCommand {

  public RegisterAccount(CommandModule module) {
    super(module);
  }

  @Override
  public String[] getCommandParameters() {
    return new String[]{NAME, GUID, PUBLICKEY, PASSWORD};
  }

  @Override
  public String getCommandName() {
    return REGISTERACCOUNT;
  }

  @Override
  public String execute(JSONObject json) throws InvalidKeyException, InvalidKeySpecException,
          JSONException, NoSuchAlgorithmException, SignatureException {
    String name = json.getString(NAME);
    String guid = json.optString(GUID, null);
    String publicKey = json.getString(PUBLICKEY);
    String password = json.optString(PASSWORD, null);
    if (guid == null) {
      guid = ClientUtils.createGuidFromPublicKey(publicKey);
    }
    String result = AccountAccess.addAccountWithVerification(module.getHost(), name, guid, publicKey, password);
    if (OKRESPONSE.equals(result)) {
      // set up the default read access
      FieldMetaData.add(MetaDataTypeName.READ_WHITELIST, guid, ALLFIELDS, EVERYONE);
      return guid;
    } else {
      return result;
    }
  }

  @Override
  public String getCommandDescription() {
    return "Creates a GUID associated with the the human readable name "
            + "(a human readable name) and the supplied publickey. Returns a guid.";

  }
}
