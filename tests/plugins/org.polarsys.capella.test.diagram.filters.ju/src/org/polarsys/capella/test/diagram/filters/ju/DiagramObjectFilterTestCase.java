/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.filters.ju;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.junit.Assert;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.framework.helpers.HelperMessages;

/**
 * This class is a generic test case for diagram filter test. To use it, create a test case that inherits from this
 * class and implement abstract methods, that are (see method documentation for more details) :
 * 
 * - getTestProjectName() - getDiagramName() - getFilterName() - getFilteredObjetIds()
 * 
 * Notice that the tested diagram in the model of test must not have the tested filter activated by default. Indeed, the
 * first check of this generic test case is to verify that elements that must be filtered by the tested filter are not
 * filtered firstly.
 */
public abstract class DiagramObjectFilterTestCase extends BasicTestCase {

  // these values are obtained by using methods defined in concrete test cases
  protected String diagramName = getDiagramName();
  protected String projectTestName = getTestProjectName();
  protected String filterName = getFilterName();
  protected List<String> filteredObjetIDs = getFilteredObjetIDs();

  // internal variables
  protected Session session;
  protected DDiagram diagram;

  protected Hashtable<String, DDiagramElement> diagramElement2ObjectID = new Hashtable<String, DDiagramElement>();
  private List<DDiagramElement> notFiltered = new ArrayList<DDiagramElement>();

  // these methods must be overridden by concrete test cases
  /** returns the name of the test project folder (by default in the folder "model") */
  protected abstract String getTestProjectName();

  /** returns the name of the tested diagram in the test project */
  protected abstract String getDiagramName();

  /** returns the name of the tested filter in the tested diagram */
  protected abstract String getFilterName();

  /** returns the ID list of all objects in the tested diagram that should be filtered by the tested filter */
  protected abstract List<String> getFilteredObjetIDs();

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(projectTestName);
  }

  protected void getCurrentDiagram() {

    session = getSessionForTestModel(projectTestName);
    IFile airdFile = getAirdFileForLoadedModel(projectTestName);
    GuiActions.openSession(airdFile, true);
    diagram = (DDiagram) DiagramHelper.getDRepresentation(session, diagramName);
    Assert.assertNotNull(MessageFormat.format(HelperMessages.diagramNotContainedInSession, diagramName, airdFile),
        diagram);// test case check
  }

  protected void checkAndInsertFilter() {

    // activate the filter
    FilterDescription filter = DiagramHelper.getFilterForDiagram(diagram, filterName);
    Assert.assertNotNull(MessageFormat.format(HelperMessages.filterNotFound, filterName, diagramName), filter);

    // Check that the filter is not already in the diagram
    EList<FilterDescription> activeFilters = diagram.getActivatedFilters();
    boolean filterIsActive = activeFilters.contains(filter);
    Assert.assertFalse("Filter " + filterName + " is already active. Please remove this filter before running the test",
        filterIsActive);

    DiagramHelper.addFilterInDiagram(diagram, filter);

    // Refresh
    DiagramHelper.refreshDiagram(diagram);

    // check that filter is active
    assertFilterActive(diagram, filterName);
  }

  protected void preRunTest() {

    getCurrentDiagram();

    // initialize a matching table to get semantic object IDs from diagram elements
    for (DDiagramElement elt : diagram.getDiagramElements()) {
      EObject target = elt.getTarget();
      if (target != null && target instanceof CapellaElement) {
        diagramElement2ObjectID.put(((CapellaElement) target).getId(), elt);
      }
    }

    // Check that given IDs are in the model
    for (String id : filteredObjetIDs) {
      boolean contains = diagramElement2ObjectID.containsKey(id);
      assertTrue("Object " + id + " is not valid for " + diagramName + " of project " + projectTestName, contains); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }
  }

  @Override
  public void test() {

    preRunTest();

    // check that objects that will be filtered are not filtered yet
    for (DDiagramElement elt : diagram.getDiagramElements()) {

      DDiagramElement objectToBeFiltered = diagramElement2ObjectID.get(((CapellaElement) elt.getTarget()).getId());
      if (objectToBeFiltered != null) {

        String objectID = ((CapellaElement) objectToBeFiltered.getTarget()).getId();
        if (!DiagramHelper.isDiagramElementFiltered(elt)) {
          notFiltered.add(elt);
        } else if (isFiltered(objectToBeFiltered)) {
          assertTrue("Object " + objectID + " should not be filtered at the begininning of the test in diagram " //$NON-NLS-1$ //$NON-NLS-2$
              + diagramName + " of project " + projectTestName, false); //$NON-NLS-1$
        }
      }
    }

    checkAndInsertFilter();
    postRunTest();
  }

  protected void postRunTest() {

    // check that expected filtered objects are actually filtered
    for (DDiagramElement elt : diagram.getDiagramElements()) {

      DDiagramElement objectToBeFiltered = diagramElement2ObjectID.get(((CapellaElement) elt.getTarget()).getId());

      if (objectToBeFiltered != null) {

        String objectID = ((CapellaElement) objectToBeFiltered.getTarget()).getId();
        boolean isFiltered = isFiltered(elt);
        if (!isFiltered && filteredObjetIDs.contains(objectID)) {
          assertTrue("Object " + objectID + " should be filtered by filter " + filterName + " in diagram " + diagramName
              + " of project " + projectTestName, false);
        } else if (isFiltered && !filteredObjetIDs.contains(objectID) && notFiltered.contains(elt)) {
          assertTrue("Object " + objectID + " should not be filtered by filter " + filterName + " in diagram "
              + diagramName + " of project " + projectTestName, false);
        }
      }
    }
  }

  protected boolean isFiltered(DDiagramElement elt) {
    return DiagramHelper.isDiagramElementFiltered(elt);
  }

  /**
   * Checks that the given filter is activated on the given diagram.
   * 
   * @param diagram
   * @param filterName
   */
  protected void assertFilterActive(DDiagram diagram, String filterName) {

    FilterDescription filter = DiagramHelper.getFilterForDiagram(diagram, filterName);
    Assert.assertNotNull(MessageFormat.format(HelperMessages.filterNotFound, filterName, diagram.getName()), filter);

    EList<FilterDescription> activatedFilters = diagram.getActivatedFilters();
    assertTrue(activatedFilters.contains(filter));
  }
}
