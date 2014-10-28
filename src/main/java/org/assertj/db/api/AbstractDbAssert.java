package org.assertj.db.api;

import static org.assertj.db.error.ShouldHaveColumnsSize.shouldHaveColumnsSize;
import static org.assertj.db.error.ShouldHaveRowsSize.shouldHaveRowsSize;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Descriptable;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.description.Description;
import org.assertj.core.internal.Failures;
import org.assertj.db.error.AssertJDBException;
import org.assertj.db.type.AbstractDbData;
import org.assertj.db.type.Column;
import org.assertj.db.type.Row;

/**
 * Assertion methods about the data in a <code>{@link Table}</code> or in a <code>{@link Request}</code>.
 * 
 * @author Régis Pouiller
 * 
 * @param <E> The class of the actual value (an sub-class of {@link AbstractDbData}).
 * @param <D> The class of the original assert (an sub-class of {@link AbstractDbAssert}).
 * @param <C> The class of this assert (an sub-class of {@link AbstractColumnAssert}).
 * @param <CV> The class of this assertion on the value (an sub-class of {@link AbstractColumnValueAssert}).
 * @param <R> The class of the equivalent row assert (an sub-class of {@link AbstractRowAssert}).
 * @param <RV> The class of the equivalent row assertion on the value (an sub-class of {@link AbstractRowValueAssert}).
 */
public abstract class AbstractDbAssert<E extends AbstractDbData<E>, D extends AbstractDbAssert<E, D, C, CV, R, RV>, C extends AbstractColumnAssert<E, D, C, CV, R, RV>, CV extends AbstractColumnValueAssert<E, D, C, CV, R, RV>, R extends AbstractRowAssert<E, D, C, CV, R, RV>, RV extends AbstractRowValueAssert<E, D, C, CV, R, RV>>
    implements Descriptable<D> {

  /**
   * Info on the object to assert.
   */
  private final WritableAssertionInfo info;
  /**
   * The actual value on which the assertion is.
   */
  private final E actual;
  /**
   * Class of the assertion.
   */
  private final D myself;

  /**
   * Index of the next row to get.
   */
  private int indexNextRow;
  /**
   * Class of the assert on the row (used to make instance).
   */
  private final Class<R> rowAssertClass;
  /**
   * Class of the assert on the column (used to make instance).
   */
  private final Class<C> columnAssertClass;
  /**
   * Index of the next column to get.
   */
  private int indexNextColumn;
  /**
   * Map the rows assert with their index in key (contains the rows assert already generated).
   */
  private Map<Integer, R> rowsAssertMap = new HashMap<Integer, R>();
  /**
   * Map the columns assert with their index in key (contains the columns assert already generated).
   */
  private Map<Integer, C> columnsAssertMap = new HashMap<Integer, C>();

  /**
   * To notice failures in the assertion.
   */
  private static Failures failures = Failures.instance();

  /**
   * Constructor of the database assertions.
   * 
   * @param actualValue The actual value on which the assertion is.
   * @param selfType Class of the assertion
   */
  protected AbstractDbAssert(E actualValue, Class<D> selfType, Class<C> columnAssertType, Class<R> rowAssertType) {
    myself = selfType.cast(this);
    actual = actualValue;
    rowAssertClass = rowAssertType;
    columnAssertClass = columnAssertType;
    info = new WritableAssertionInfo();
  }

  /** {@inheritDoc} */
  public D as(String description, Object... args) {
    return describedAs(description, args);
  }

  /** {@inheritDoc} */
  @Override
  public D as(Description description) {
    return describedAs(description);
  }

  /** {@inheritDoc} */
  @Override
  public D describedAs(String description, Object... args) {
    info.description(description, args);
    return myself;
  }

  /** {@inheritDoc} */
  @Override
  public D describedAs(Description description) {
    info.description(description);
    return myself;
  }

  /**
   * Verifies that the number of rows is equal to the number in parameter.
   * <p>
   * Example where the assertion verifies that the table have 2 rows :
   * </p>
   * 
   * <pre>
   * assertThat(table).hasRowsSize(2);
   * </pre>
   * 
   * @param expected The number to compare to the number of rows.
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of rows is different to the number in parameter.
   */
  public D hasRowsSize(int expected) {
    List<Row> rowsList = actual.getRowsList();
    int size = rowsList.size();
    if (size != expected) {
      throw failures.failure(info, shouldHaveRowsSize(size, expected));
    }
    return myself;
  }

  /**
   * Verifies that the number of columns is equal to the number in parameter.
   * <p>
   * Example where the assertion verifies that the table have 8 columns :
   * </p>
   * 
   * <pre>
   * assertThat(table).hasColumnsSize(8);
   * </pre>
   * 
   * @param expected The number to compare to the number of columns.
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of columns is different to the number in parameter.
   */
  public D hasColumnsSize(int expected) {
    List<String> columnsNameList = actual.getColumnsNameList();
    int size = columnsNameList.size();
    if (size != expected) {
      throw failures.failure(info, shouldHaveColumnsSize(size, expected));
    }
    return myself;
  }

  /**
   * Returns the {@link Row} at the {@code index} in parameter.
   * 
   * @param index The index corresponding to the {@link Row}.
   * @return The {@link Row}.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  protected Row getRow(int index) {
    int size = actual.getRowsList().size();
    if (index < 0 || index >= size) {
      throw new AssertJDBException("Index %s out of the limits [0, %s[", index, size);
    }
    Row row = actual.getRow(index);
    indexNextRow = index + 1;
    return row;
  }

  /**
   * Gets an instance of row assert corresponding to the index. If this instance is already instanced, the method
   * returns it from the cache.
   * 
   * @param index Index of the row on which is the instance of row assert.
   * @return The row assert implementation.
   */
  private R getRowAssertInstance(int index) {
    if (rowsAssertMap.containsKey(index)) {
      R rowAssert = rowsAssertMap.get(index).initialize();
      indexNextRow = index + 1;
      return rowAssert;
    }

    try {
      Constructor<R> constructor = rowAssertClass.getDeclaredConstructor(myself.getClass(), Row.class);
      R instance = constructor.newInstance(this, getRow(index));
      rowsAssertMap.put(index, instance);
      return instance.as("Row at index " + index + " of " + info.descriptionText());
    } catch (Exception e) {
      throw new AssertJDBException("There is an exception '" + e.getMessage()
          + "'\n\t in the instanciation of the assertion " + rowAssertClass.getName() + "\n\t on the row with "
          + myself.getClass() + ".\n "
          + "It is normally impossible.\n That means there is a big mistake in the development of AssertJDB.\n "
          + "Please write an issue for that if you meet this problem.");
    }
  }

  /**
   * Returns assertion methods on the next {@link Row} in the list of {@link Row}.
   * 
   * @return An object to make assertions on the next {@link Row}.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  public R row() {
    return getRowAssertInstance(indexNextRow);
  }

  /**
   * Returns assertion methods on the {@link Row} at the {@code index} in parameter.
   * 
   * @param index The index corresponding to the {@link Row}.
   * @return An object to make assertions on the {@link Row}.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  public R row(int index) {
    return getRowAssertInstance(index);
  }

  /**
   * Returns the {@link Column} at the {@code index} in parameter.
   * 
   * @param index The index corresponding to the {@link Column}.
   * @return The {@link Column}.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  protected Column getColumn(int index) {
    List<String> columnsNameList = actual.getColumnsNameList();
    int size = columnsNameList.size();
    if (index < 0 || index >= size) {
      throw new AssertJDBException("Index %s out of the limits [0, %s[", index, size);
    }
    Column column = actual.getColumn(index);
    indexNextColumn = index + 1;
    return column;
  }

  /**
   * Gets an instance of column assert corresponding to the index. If this instance is already instanced, the method
   * returns it from the cache.
   * 
   * @param index Index of the column on which is the instance of column assert.
   * @return The column assert implementation.
   */
  private C getColumnAssertInstance(int index) {
    if (columnsAssertMap.containsKey(index)) {
      C columnAssert = columnsAssertMap.get(index).initialize();
      indexNextColumn = index + 1;
      return columnAssert;
    }

    try {
      Constructor<C> constructor = columnAssertClass.getDeclaredConstructor(myself.getClass(), Column.class);
      C instance = constructor.newInstance(this, getColumn(index));
      columnsAssertMap.put(index, instance);
      return instance.as("Column at index " + index + " of " + info.descriptionText());
    } catch (Exception e) {
      throw new AssertJDBException("There is an exception '" + e.getMessage()
          + "'\n\t in the instanciation of the assertion " + columnAssertClass.getName() + "\n\t on the column with "
          + myself.getClass() + ".\n "
          + "It is normally impossible.\n That means there is a big mistake in the development of AssertJDB.\n "
          + "Please write an issue for that if you meet this problem.");
    }
  }

  /**
   * Returns assertion methods on the next {@link Column} in the list of {@link Column}.
   * 
   * @return An object to make assertions on the next {@link Column}.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  public C column() {
    return getColumnAssertInstance(indexNextColumn);
  }

  /**
   * Returns assertion methods on the {@link Column} at the {@code index} in parameter.
   * 
   * @param index The index corresponding to the {@link Column}.
   * @return An object to make assertions on the {@link Column}.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  public C column(int index) {
    return getColumnAssertInstance(index);
  }

  /**
   * Returns assertion methods on the {@link Column} corresponding to the column name in parameter.
   * 
   * @param columnName The column name.
   * @return An object to make assertions on the {@link Column}.
   * @throws NullPointerException If the column name in parameter is null.
   * @throws AssertJDBException If there is no column with this name.
   */
  public C column(String columnName) {
    if (columnName == null) {
      throw new NullPointerException("Column name must be not null");
    }
    List<String> columnsNameList = actual.getColumnsNameList();
    int index = columnsNameList.indexOf(columnName.toUpperCase());
    if (index == -1) {
      throw new AssertJDBException("Column <%s> does not exist", columnName);
    }
    return getColumnAssertInstance(index);
  }
}
