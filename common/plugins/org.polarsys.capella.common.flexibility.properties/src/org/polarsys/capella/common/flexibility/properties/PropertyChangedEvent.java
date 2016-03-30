/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.flexibility.properties;

import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;

/**
 *
 */
public class PropertyChangedEvent {
  IProperty _property;
  IPropertyContext _context;

  public PropertyChangedEvent(IProperty property, IPropertyContext context) {
    this._property = property;
    this._context = context;
  }

  public IProperty getProperty() {
    return _property;
  }

  public IPropertyContext getPropertyContext() {
    return _context;
  }
}
