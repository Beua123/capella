/**
 * 
 *   Copyright (c) 2006, 2019 THALES DMS FRANCE.
 *   
 *   This program and the accompanying materials are made available under the
 *   terms of the Eclipse Public License 2.0 which is available at
 *   http://www.eclipse.org/legal/epl-2.0
 *   
 *   SPDX-License-Identifier: EPL-2.0
 *  
 *   Contributors:
 *      Thales - initial API and implementation
 *  
 */
package org.polarsys.capella.viatra.core.data.cs.surrogate;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.IQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFPQuery;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFQuerySpecification;
import org.eclipse.viatra.query.runtime.api.impl.BaseMatcher;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.emf.types.EClassTransitiveInstancesKey;
import org.eclipse.viatra.query.runtime.emf.types.EStructuralFeatureInstancesKey;
import org.eclipse.viatra.query.runtime.matchers.backend.QueryEvaluationHint;
import org.eclipse.viatra.query.runtime.matchers.psystem.PBody;
import org.eclipse.viatra.query.runtime.matchers.psystem.PVariable;
import org.eclipse.viatra.query.runtime.matchers.psystem.annotations.PAnnotation;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.Equality;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameterDirection;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PVisibility;
import org.eclipse.viatra.query.runtime.matchers.tuple.Tuple;
import org.eclipse.viatra.query.runtime.matchers.tuple.Tuples;
import org.eclipse.viatra.query.runtime.util.ViatraQueryLoggingUtil;
import org.polarsys.capella.core.data.cs.AbstractPhysicalLinkEnd;
import org.polarsys.capella.core.data.cs.PhysicalLink;

/**
 * A pattern-specific query specification that can instantiate Matcher in a type-safe way.
 * 
 * <p>Original source:
 *         <code><pre>
 *         // Start of user code for imports 
 *         
 *         // End of user code
 *         
 *         {@literal @}Surrogate(feature="involvedLinks")
 *         pattern AbstractPhysicalLinkEnd__involvedLinks(self : AbstractPhysicalLinkEnd, target : PhysicalLink) {
 *         	PhysicalLink.linkEnds(target, self);
 *         } or {
 *         	PhysicalLinkEnd.port(ple, self);	
 *         	PhysicalLink.linkEnds(target, ple);
 *         }
 * </pre></code>
 * 
 * @see Matcher
 * @see Match
 * 
 */
@SuppressWarnings("all")
public final class AbstractPhysicalLinkEnd__involvedLinks extends BaseGeneratedEMFQuerySpecification<AbstractPhysicalLinkEnd__involvedLinks.Matcher> {
  /**
   * Pattern-specific match representation of the org.polarsys.capella.viatra.core.data.cs.surrogate.AbstractPhysicalLinkEnd__involvedLinks pattern,
   * to be used in conjunction with {@link Matcher}.
   * 
   * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
   * Each instance is a (possibly partial) substitution of pattern parameters,
   * usable to represent a match of the pattern in the result of a query,
   * or to specify the bound (fixed) input parameters when issuing a query.
   * 
   * @see Matcher
   * 
   */
  public static abstract class Match extends BasePatternMatch {
    private AbstractPhysicalLinkEnd fSelf;
    
    private PhysicalLink fTarget;
    
    private static List<String> parameterNames = makeImmutableList("self", "target");
    
    private Match(final AbstractPhysicalLinkEnd pSelf, final PhysicalLink pTarget) {
      this.fSelf = pSelf;
      this.fTarget = pTarget;
    }
    
    @Override
    public Object get(final String parameterName) {
      switch(parameterName) {
          case "self": return this.fSelf;
          case "target": return this.fTarget;
          default: return null;
      }
    }
    
    @Override
    public Object get(final int index) {
      switch(index) {
          case 0: return this.fSelf;
          case 1: return this.fTarget;
          default: return null;
      }
    }
    
    public AbstractPhysicalLinkEnd getSelf() {
      return this.fSelf;
    }
    
    public PhysicalLink getTarget() {
      return this.fTarget;
    }
    
    @Override
    public boolean set(final String parameterName, final Object newValue) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      if ("self".equals(parameterName) ) {
          this.fSelf = (AbstractPhysicalLinkEnd) newValue;
          return true;
      }
      if ("target".equals(parameterName) ) {
          this.fTarget = (PhysicalLink) newValue;
          return true;
      }
      return false;
    }
    
    public void setSelf(final AbstractPhysicalLinkEnd pSelf) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fSelf = pSelf;
    }
    
    public void setTarget(final PhysicalLink pTarget) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fTarget = pTarget;
    }
    
    @Override
    public String patternName() {
      return "org.polarsys.capella.viatra.core.data.cs.surrogate.AbstractPhysicalLinkEnd__involvedLinks";
    }
    
    @Override
    public List<String> parameterNames() {
      return AbstractPhysicalLinkEnd__involvedLinks.Match.parameterNames;
    }
    
    @Override
    public Object[] toArray() {
      return new Object[]{fSelf, fTarget};
    }
    
    @Override
    public AbstractPhysicalLinkEnd__involvedLinks.Match toImmutable() {
      return isMutable() ? newMatch(fSelf, fTarget) : this;
    }
    
    @Override
    public String prettyPrint() {
      StringBuilder result = new StringBuilder();
      result.append("\"self\"=" + prettyPrintValue(fSelf) + ", ");
      result.append("\"target\"=" + prettyPrintValue(fTarget));
      return result.toString();
    }
    
    @Override
    public int hashCode() {
      return Objects.hash(fSelf, fTarget);
    }
    
    @Override
    public boolean equals(final Object obj) {
      if (this == obj)
          return true;
      if (obj == null) {
          return false;
      }
      if ((obj instanceof AbstractPhysicalLinkEnd__involvedLinks.Match)) {
          AbstractPhysicalLinkEnd__involvedLinks.Match other = (AbstractPhysicalLinkEnd__involvedLinks.Match) obj;
          return Objects.equals(fSelf, other.fSelf) && Objects.equals(fTarget, other.fTarget);
      } else {
          // this should be infrequent
          if (!(obj instanceof IPatternMatch)) {
              return false;
          }
          IPatternMatch otherSig  = (IPatternMatch) obj;
          return Objects.equals(specification(), otherSig.specification()) && Arrays.deepEquals(toArray(), otherSig.toArray());
      }
    }
    
    @Override
    public AbstractPhysicalLinkEnd__involvedLinks specification() {
      return AbstractPhysicalLinkEnd__involvedLinks.instance();
    }
    
    /**
     * Returns an empty, mutable match.
     * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
     * 
     * @return the empty match.
     * 
     */
    public static AbstractPhysicalLinkEnd__involvedLinks.Match newEmptyMatch() {
      return new Mutable(null, null);
    }
    
    /**
     * Returns a mutable (partial) match.
     * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
     * 
     * @param pSelf the fixed value of pattern parameter self, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return the new, mutable (partial) match object.
     * 
     */
    public static AbstractPhysicalLinkEnd__involvedLinks.Match newMutableMatch(final AbstractPhysicalLinkEnd pSelf, final PhysicalLink pTarget) {
      return new Mutable(pSelf, pTarget);
    }
    
    /**
     * Returns a new (partial) match.
     * This can be used e.g. to call the matcher with a partial match.
     * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
     * @param pSelf the fixed value of pattern parameter self, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return the (partial) match object.
     * 
     */
    public static AbstractPhysicalLinkEnd__involvedLinks.Match newMatch(final AbstractPhysicalLinkEnd pSelf, final PhysicalLink pTarget) {
      return new Immutable(pSelf, pTarget);
    }
    
    private static final class Mutable extends AbstractPhysicalLinkEnd__involvedLinks.Match {
      Mutable(final AbstractPhysicalLinkEnd pSelf, final PhysicalLink pTarget) {
        super(pSelf, pTarget);
      }
      
      @Override
      public boolean isMutable() {
        return true;
      }
    }
    
    private static final class Immutable extends AbstractPhysicalLinkEnd__involvedLinks.Match {
      Immutable(final AbstractPhysicalLinkEnd pSelf, final PhysicalLink pTarget) {
        super(pSelf, pTarget);
      }
      
      @Override
      public boolean isMutable() {
        return false;
      }
    }
  }
  
  /**
   * Generated pattern matcher API of the org.polarsys.capella.viatra.core.data.cs.surrogate.AbstractPhysicalLinkEnd__involvedLinks pattern,
   * providing pattern-specific query methods.
   * 
   * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
   * e.g. in conjunction with {@link ViatraQueryEngine#on(QueryScope)}.
   * 
   * <p>Matches of the pattern will be represented as {@link Match}.
   * 
   * <p>Original source:
   * <code><pre>
   * // Start of user code for imports 
   * 
   * // End of user code
   * 
   * {@literal @}Surrogate(feature="involvedLinks")
   * pattern AbstractPhysicalLinkEnd__involvedLinks(self : AbstractPhysicalLinkEnd, target : PhysicalLink) {
   * 	PhysicalLink.linkEnds(target, self);
   * } or {
   * 	PhysicalLinkEnd.port(ple, self);	
   * 	PhysicalLink.linkEnds(target, ple);
   * }
   * </pre></code>
   * 
   * @see Match
   * @see AbstractPhysicalLinkEnd__involvedLinks
   * 
   */
  public static class Matcher extends BaseMatcher<AbstractPhysicalLinkEnd__involvedLinks.Match> {
    /**
     * Initializes the pattern matcher within an existing VIATRA Query engine.
     * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
     * 
     * @param engine the existing VIATRA Query engine in which this matcher will be created.
     * @throws ViatraQueryRuntimeException if an error occurs during pattern matcher creation
     * 
     */
    public static AbstractPhysicalLinkEnd__involvedLinks.Matcher on(final ViatraQueryEngine engine) {
      // check if matcher already exists
      Matcher matcher = engine.getExistingMatcher(querySpecification());
      if (matcher == null) {
          matcher = (Matcher)engine.getMatcher(querySpecification());
      }
      return matcher;
    }
    
    /**
     * @throws ViatraQueryRuntimeException if an error occurs during pattern matcher creation
     * @return an initialized matcher
     * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
     * 
     */
    public static AbstractPhysicalLinkEnd__involvedLinks.Matcher create() {
      return new Matcher();
    }
    
    private static final int POSITION_SELF = 0;
    
    private static final int POSITION_TARGET = 1;
    
    private static final Logger LOGGER = ViatraQueryLoggingUtil.getLogger(AbstractPhysicalLinkEnd__involvedLinks.Matcher.class);
    
    /**
     * Initializes the pattern matcher within an existing VIATRA Query engine.
     * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
     * 
     * @param engine the existing VIATRA Query engine in which this matcher will be created.
     * @throws ViatraQueryRuntimeException if an error occurs during pattern matcher creation
     * 
     */
    private Matcher() {
      super(querySpecification());
    }
    
    /**
     * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
     * @param pSelf the fixed value of pattern parameter self, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return matches represented as a Match object.
     * 
     */
    public Collection<AbstractPhysicalLinkEnd__involvedLinks.Match> getAllMatches(final AbstractPhysicalLinkEnd pSelf, final PhysicalLink pTarget) {
      return rawStreamAllMatches(new Object[]{pSelf, pTarget}).collect(Collectors.toSet());
    }
    
    /**
     * Returns a stream of all matches of the pattern that conform to the given fixed values of some parameters.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     * @param pSelf the fixed value of pattern parameter self, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return a stream of matches represented as a Match object.
     * 
     */
    public Stream<AbstractPhysicalLinkEnd__involvedLinks.Match> streamAllMatches(final AbstractPhysicalLinkEnd pSelf, final PhysicalLink pTarget) {
      return rawStreamAllMatches(new Object[]{pSelf, pTarget});
    }
    
    /**
     * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
     * Neither determinism nor randomness of selection is guaranteed.
     * @param pSelf the fixed value of pattern parameter self, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return a match represented as a Match object, or null if no match is found.
     * 
     */
    public Optional<AbstractPhysicalLinkEnd__involvedLinks.Match> getOneArbitraryMatch(final AbstractPhysicalLinkEnd pSelf, final PhysicalLink pTarget) {
      return rawGetOneArbitraryMatch(new Object[]{pSelf, pTarget});
    }
    
    /**
     * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
     * under any possible substitution of the unspecified parameters (if any).
     * @param pSelf the fixed value of pattern parameter self, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return true if the input is a valid (partial) match of the pattern.
     * 
     */
    public boolean hasMatch(final AbstractPhysicalLinkEnd pSelf, final PhysicalLink pTarget) {
      return rawHasMatch(new Object[]{pSelf, pTarget});
    }
    
    /**
     * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
     * @param pSelf the fixed value of pattern parameter self, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return the number of pattern matches found.
     * 
     */
    public int countMatches(final AbstractPhysicalLinkEnd pSelf, final PhysicalLink pTarget) {
      return rawCountMatches(new Object[]{pSelf, pTarget});
    }
    
    /**
     * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
     * Neither determinism nor randomness of selection is guaranteed.
     * @param pSelf the fixed value of pattern parameter self, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @param processor the action that will process the selected match.
     * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
     * 
     */
    public boolean forOneArbitraryMatch(final AbstractPhysicalLinkEnd pSelf, final PhysicalLink pTarget, final Consumer<? super AbstractPhysicalLinkEnd__involvedLinks.Match> processor) {
      return rawForOneArbitraryMatch(new Object[]{pSelf, pTarget}, processor);
    }
    
    /**
     * Returns a new (partial) match.
     * This can be used e.g. to call the matcher with a partial match.
     * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
     * @param pSelf the fixed value of pattern parameter self, or null if not bound.
     * @param pTarget the fixed value of pattern parameter target, or null if not bound.
     * @return the (partial) match object.
     * 
     */
    public AbstractPhysicalLinkEnd__involvedLinks.Match newMatch(final AbstractPhysicalLinkEnd pSelf, final PhysicalLink pTarget) {
      return AbstractPhysicalLinkEnd__involvedLinks.Match.newMatch(pSelf, pTarget);
    }
    
    /**
     * Retrieve the set of values that occur in matches for self.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<AbstractPhysicalLinkEnd> rawStreamAllValuesOfself(final Object[] parameters) {
      return rawStreamAllValues(POSITION_SELF, parameters).map(AbstractPhysicalLinkEnd.class::cast);
    }
    
    /**
     * Retrieve the set of values that occur in matches for self.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<AbstractPhysicalLinkEnd> getAllValuesOfself() {
      return rawStreamAllValuesOfself(emptyArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for self.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<AbstractPhysicalLinkEnd> streamAllValuesOfself() {
      return rawStreamAllValuesOfself(emptyArray());
    }
    
    /**
     * Retrieve the set of values that occur in matches for self.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<AbstractPhysicalLinkEnd> streamAllValuesOfself(final AbstractPhysicalLinkEnd__involvedLinks.Match partialMatch) {
      return rawStreamAllValuesOfself(partialMatch.toArray());
    }
    
    /**
     * Retrieve the set of values that occur in matches for self.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<AbstractPhysicalLinkEnd> streamAllValuesOfself(final PhysicalLink pTarget) {
      return rawStreamAllValuesOfself(new Object[]{null, pTarget});
    }
    
    /**
     * Retrieve the set of values that occur in matches for self.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<AbstractPhysicalLinkEnd> getAllValuesOfself(final AbstractPhysicalLinkEnd__involvedLinks.Match partialMatch) {
      return rawStreamAllValuesOfself(partialMatch.toArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for self.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<AbstractPhysicalLinkEnd> getAllValuesOfself(final PhysicalLink pTarget) {
      return rawStreamAllValuesOfself(new Object[]{null, pTarget}).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<PhysicalLink> rawStreamAllValuesOftarget(final Object[] parameters) {
      return rawStreamAllValues(POSITION_TARGET, parameters).map(PhysicalLink.class::cast);
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<PhysicalLink> getAllValuesOftarget() {
      return rawStreamAllValuesOftarget(emptyArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<PhysicalLink> streamAllValuesOftarget() {
      return rawStreamAllValuesOftarget(emptyArray());
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<PhysicalLink> streamAllValuesOftarget(final AbstractPhysicalLinkEnd__involvedLinks.Match partialMatch) {
      return rawStreamAllValuesOftarget(partialMatch.toArray());
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     *      
     * @return the Stream of all values or empty set if there are no matches
     * 
     */
    public Stream<PhysicalLink> streamAllValuesOftarget(final AbstractPhysicalLinkEnd pSelf) {
      return rawStreamAllValuesOftarget(new Object[]{pSelf, null});
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<PhysicalLink> getAllValuesOftarget(final AbstractPhysicalLinkEnd__involvedLinks.Match partialMatch) {
      return rawStreamAllValuesOftarget(partialMatch.toArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for target.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<PhysicalLink> getAllValuesOftarget(final AbstractPhysicalLinkEnd pSelf) {
      return rawStreamAllValuesOftarget(new Object[]{pSelf, null}).collect(Collectors.toSet());
    }
    
    @Override
    protected AbstractPhysicalLinkEnd__involvedLinks.Match tupleToMatch(final Tuple t) {
      try {
          return AbstractPhysicalLinkEnd__involvedLinks.Match.newMatch((AbstractPhysicalLinkEnd) t.get(POSITION_SELF), (PhysicalLink) t.get(POSITION_TARGET));
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in tuple not properly typed!",e);
          return null;
      }
    }
    
    @Override
    protected AbstractPhysicalLinkEnd__involvedLinks.Match arrayToMatch(final Object[] match) {
      try {
          return AbstractPhysicalLinkEnd__involvedLinks.Match.newMatch((AbstractPhysicalLinkEnd) match[POSITION_SELF], (PhysicalLink) match[POSITION_TARGET]);
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in array not properly typed!",e);
          return null;
      }
    }
    
    @Override
    protected AbstractPhysicalLinkEnd__involvedLinks.Match arrayToMatchMutable(final Object[] match) {
      try {
          return AbstractPhysicalLinkEnd__involvedLinks.Match.newMutableMatch((AbstractPhysicalLinkEnd) match[POSITION_SELF], (PhysicalLink) match[POSITION_TARGET]);
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in array not properly typed!",e);
          return null;
      }
    }
    
    /**
     * @return the singleton instance of the query specification of this pattern
     * @throws ViatraQueryRuntimeException if the pattern definition could not be loaded
     * 
     */
    public static IQuerySpecification<AbstractPhysicalLinkEnd__involvedLinks.Matcher> querySpecification() {
      return AbstractPhysicalLinkEnd__involvedLinks.instance();
    }
  }
  
  private AbstractPhysicalLinkEnd__involvedLinks() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryRuntimeException if the pattern definition could not be loaded
   * 
   */
  public static AbstractPhysicalLinkEnd__involvedLinks instance() {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }
  
  @Override
  protected AbstractPhysicalLinkEnd__involvedLinks.Matcher instantiate(final ViatraQueryEngine engine) {
    return AbstractPhysicalLinkEnd__involvedLinks.Matcher.on(engine);
  }
  
  @Override
  public AbstractPhysicalLinkEnd__involvedLinks.Matcher instantiate() {
    return AbstractPhysicalLinkEnd__involvedLinks.Matcher.create();
  }
  
  @Override
  public AbstractPhysicalLinkEnd__involvedLinks.Match newEmptyMatch() {
    return AbstractPhysicalLinkEnd__involvedLinks.Match.newEmptyMatch();
  }
  
  @Override
  public AbstractPhysicalLinkEnd__involvedLinks.Match newMatch(final Object... parameters) {
    return AbstractPhysicalLinkEnd__involvedLinks.Match.newMatch((org.polarsys.capella.core.data.cs.AbstractPhysicalLinkEnd) parameters[0], (org.polarsys.capella.core.data.cs.PhysicalLink) parameters[1]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link AbstractPhysicalLinkEnd__involvedLinks} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link AbstractPhysicalLinkEnd__involvedLinks#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private static final AbstractPhysicalLinkEnd__involvedLinks INSTANCE = new AbstractPhysicalLinkEnd__involvedLinks();
    
    /**
     * Statically initializes the query specification <b>after</b> the field {@link #INSTANCE} is assigned.
     * This initialization order is required to support indirect recursion.
     * 
     * <p> The static initializer is defined using a helper field to work around limitations of the code generator.
     * 
     */
    private static final Object STATIC_INITIALIZER = ensureInitialized();
    
    public static Object ensureInitialized() {
      INSTANCE.ensureInitializedInternal();
      return null;
    }
  }
  
  private static class GeneratedPQuery extends BaseGeneratedEMFPQuery {
    private static final AbstractPhysicalLinkEnd__involvedLinks.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_self = new PParameter("self", "org.polarsys.capella.core.data.cs.AbstractPhysicalLinkEnd", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.polarsys.org/capella/core/cs/5.0.0", "AbstractPhysicalLinkEnd")), PParameterDirection.INOUT);
    
    private final PParameter parameter_target = new PParameter("target", "org.polarsys.capella.core.data.cs.PhysicalLink", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://www.polarsys.org/capella/core/cs/5.0.0", "PhysicalLink")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_self, parameter_target);
    
    private GeneratedPQuery() {
      super(PVisibility.PUBLIC);
    }
    
    @Override
    public String getFullyQualifiedName() {
      return "org.polarsys.capella.viatra.core.data.cs.surrogate.AbstractPhysicalLinkEnd__involvedLinks";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("self","target");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return parameters;
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() {
      setEvaluationHints(new QueryEvaluationHint(null, QueryEvaluationHint.BackendRequirement.UNSPECIFIED));
      Set<PBody> bodies = new LinkedHashSet<>();
      {
          PBody body = new PBody(this);
          PVariable var_self = body.getOrCreateVariableByName("self");
          PVariable var_target = body.getOrCreateVariableByName("target");
          new TypeConstraint(body, Tuples.flatTupleOf(var_self), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/cs/5.0.0", "AbstractPhysicalLinkEnd")));
          new TypeConstraint(body, Tuples.flatTupleOf(var_target), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/cs/5.0.0", "PhysicalLink")));
          body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
             new ExportedParameter(body, var_self, parameter_self),
             new ExportedParameter(body, var_target, parameter_target)
          ));
          // 	PhysicalLink.linkEnds(target, self)
          new TypeConstraint(body, Tuples.flatTupleOf(var_target), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/cs/5.0.0", "PhysicalLink")));
          PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
          new TypeConstraint(body, Tuples.flatTupleOf(var_target, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/core/cs/5.0.0", "PhysicalLink", "linkEnds")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/cs/5.0.0", "AbstractPhysicalLinkEnd")));
          new Equality(body, var__virtual_0_, var_self);
          bodies.add(body);
      }
      {
          PBody body = new PBody(this);
          PVariable var_self = body.getOrCreateVariableByName("self");
          PVariable var_target = body.getOrCreateVariableByName("target");
          PVariable var_ple = body.getOrCreateVariableByName("ple");
          new TypeConstraint(body, Tuples.flatTupleOf(var_self), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/cs/5.0.0", "AbstractPhysicalLinkEnd")));
          new TypeConstraint(body, Tuples.flatTupleOf(var_target), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/cs/5.0.0", "PhysicalLink")));
          body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
             new ExportedParameter(body, var_self, parameter_self),
             new ExportedParameter(body, var_target, parameter_target)
          ));
          // 	PhysicalLinkEnd.port(ple, self)
          new TypeConstraint(body, Tuples.flatTupleOf(var_ple), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/cs/5.0.0", "PhysicalLinkEnd")));
          PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
          new TypeConstraint(body, Tuples.flatTupleOf(var_ple, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/core/cs/5.0.0", "PhysicalLinkEnd", "port")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/cs/5.0.0", "PhysicalPort")));
          new Equality(body, var__virtual_0_, var_self);
          // 		PhysicalLink.linkEnds(target, ple)
          new TypeConstraint(body, Tuples.flatTupleOf(var_target), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/cs/5.0.0", "PhysicalLink")));
          PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
          new TypeConstraint(body, Tuples.flatTupleOf(var_target, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.polarsys.org/capella/core/cs/5.0.0", "PhysicalLink", "linkEnds")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_1_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.polarsys.org/capella/core/cs/5.0.0", "AbstractPhysicalLinkEnd")));
          new Equality(body, var__virtual_1_, var_ple);
          bodies.add(body);
      }
      {
          PAnnotation annotation = new PAnnotation("Surrogate");
          annotation.addAttribute("feature", "involvedLinks");
          addAnnotation(annotation);
      }
      return bodies;
    }
  }
}
