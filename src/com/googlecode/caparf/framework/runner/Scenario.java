/*
 * Copyright (C) 2010 Denis Nazarov <denis.nsc@gmail.com>.
 *
 * This file is part of caparf (http://code.google.com/p/caparf/).
 *
 * caparf is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * caparf is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with caparf. If not, see <http://www.gnu.org/licenses/>.
 */

package com.googlecode.caparf.framework.runner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.googlecode.caparf.framework.base.Algorithm;
import com.googlecode.caparf.framework.base.BaseInput;
import com.googlecode.caparf.framework.base.BaseItem;
import com.googlecode.caparf.framework.base.BaseItemPlacement;
import com.googlecode.caparf.framework.base.InputSuite;
import com.googlecode.caparf.framework.base.BaseOutput;
import com.googlecode.caparf.framework.base.BaseOutputVerifier;

/**
 * Defines scenario to be executed by caparf.
 *
 * @author denis.nsc@gmail.com (Denis Nazarov)
 */
public class Scenario<I extends BaseInput<? extends BaseItem>,
    O extends BaseOutput<? extends BaseItemPlacement>> {

  private List<Algorithm<I, O>> algorithms;
  private InputSuite<I> inputs;
  private BaseOutputVerifier<I, O> verifier;
  private long timeLimit;

  /**
   * Creates empty scenario.
   */
  public Scenario() {
    algorithms = new ArrayList<Algorithm<I, O>>();
    inputs = new InputSuite<I>();
    verifier = null;
    timeLimit = 0;
  }

  /**
   * @return all algorithms in the scenario
   */
  public List<Algorithm<I, O>> getAlgorithms() {
    return algorithms;
  }

  /**
   * @return suite of all inputs in the scenario
   */
  public InputSuite<I> getInputs() {
    return inputs;
  }

  /**
   * @return verifier to be used in the scenario
   */
  public BaseOutputVerifier<I, O> getVerifier() {
    return verifier;
  }

  /**
   * Adds single algorithm to the scenario.
   *
   * @param algorithm algorithm to add
   */
  public void addAlgorithm(Algorithm<I, O> algorithm) {
    this.algorithms.add(algorithm);
  }

  /**
   * Adds list of algorithms to the scenario.
   *
   * @param algorithms list of algorithms to add
   */
  public void addAlgorithms(List<? extends Algorithm<I, O>> algorithms) {
    this.algorithms.addAll(algorithms);
  }

  /**
   * Adds all given algorithms to the scenario.
   *
   * @param algorithms algorithms to add
   */
  public <T extends Algorithm<I, O>> void addAlgorithms(T... algorithms) {
    Collections.addAll(this.algorithms, algorithms);
  }

  /**
   * Adds single input to the scenario.
   *
   * @param input input to add
   */
  public void addInput(I input) {
    this.inputs.add(input);
  }

  /**
   * Adds suite of inputs to the scenario.
   *
   * @param suite suite of inputs to add
   */
  public void addInputSuite(InputSuite<? extends I> suite) {
    this.inputs.addAll(suite.getAll());
  }

  /**
   * Adds list of inputs to the scenario.
   *
   * @param inputs list of inputs to add
   */
  public void addInputs(List<? extends I> inputs) {
    this.inputs.addAll(inputs);
  }

  /**
   * Adds all given inputs to the scenario.
   *
   * @param inputs inputs to add
   */
  public <T extends I> void addInputs(T... inputs) {
    this.inputs.addAll(inputs);
  }

  /**
   * Sets verifier to be used in the scenario.
   *
   * @param verifier verifier to be used in the scenario
   */
  public void setVerifier(BaseOutputVerifier<I, O> verifier) {
    this.verifier = verifier;
  }

  /**
   * @return time limit per algorithm run in milliseconds
   */
  public long getTimeLimit() {
    return timeLimit;
  }

  /**
   * Sets time limit per algorithm run. {@code 0} means that algorithms will be
   * run without limits.
   *
   * @param millis time limit per algorithm run in milliseconds
   */
  public void setTimeLimit(long millis) {
    if (millis < 0) {
      throw new IllegalArgumentException("time limit is negative");
    }
    this.timeLimit = millis;
  }
}
