/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2014 the original author or authors.
 */
package org.assertj.db.api.assertions;

/**
 * Defines the assertion methods on modified columns.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 */
public interface AssertOnModifiedColumns<T extends AssertOnModifiedColumns<T>> {

  /**
   * Verifies that the number of columns with a modification in the values between the start point and the end point
   * is equals to the number in parameter.
   * <p>
   * Example where the assertion verifies that there are 3 modified columns :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).hasNumberOfModifiedColumns(3);
   * </code>
   * </pre>
   *
   * @param number The expected number of modified columns
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of modified columns is different to the number in parameter.
   * @see org.assertj.db.api.ChangeAssert#hasNumberOfModifiedColumns(int)
   */
  public T hasNumberOfModifiedColumns(int number);

  /**
   * Verifies that the indexes of columns with a modification in the values between the start point and the end point
   * is equals to the parameters.
   * <p>
   * Example where the assertion verifies that indexes of modified columns are 3 and 5 :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).hasModifiedColumns(3, 5);
   * </code>
   * </pre>
   *
   * @param indexes Indexes of the modified columns.
   * @return {@code this} assertion object.
   * @throws AssertionError If the indexes of the modified columns are different to the indexes in parameters.
   * @see org.assertj.db.api.ChangeAssert#hasModifiedColumns(Integer...)
   */
  public T hasModifiedColumns(Integer... indexes);

  /**
   * Verifies that the names of columns with a modification in the values between the start point and the end point
   * is equals to the parameters.
   * <p>
   * Example where the assertion verifies that names of modified columns are "name" and "birth" :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).hasModifiedColumns("name", "birth");
   * </code>
   * </pre>
   *
   * @param names Names of the modified columns.
   * @return {@code this} assertion object.
   * @throws AssertionError If the names of the modified columns are different to the names in parameters.
   * @see org.assertj.db.api.ChangeAssert#hasModifiedColumns(String...)
   */
  public T hasModifiedColumns(String... names);

}
