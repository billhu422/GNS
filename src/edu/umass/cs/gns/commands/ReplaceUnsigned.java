/*
 * Copyright (C) 2014
 * University of Massachusetts
 * All Rights Reserved 
 *
 * Initial developer(s): Westy.
 */
package edu.umass.cs.gns.commands;

import static edu.umass.cs.gns.clientsupport.Defs.*;
import edu.umass.cs.gns.clientsupport.UpdateOperation;

/**
 *
 * @author westy
 */
public class ReplaceUnsigned extends AbstractUpdate {

  public ReplaceUnsigned(CommandModule module) {
    super(module);
  }

  @Override
  public UpdateOperation getUpdateOperation() {
    return UpdateOperation.REPLACE_ALL;
  }

  @Override
  public String getCommandName() {
    return REPLACE;
  }

  @Override
  public String[] getCommandParameters() {
    return new String[]{GUID, FIELD, VALUE};
  }

  @Override
  public String getCommandDescription() {
    return "Replaces the current value key value pair from the GNS for the given guid.";
  }
}
