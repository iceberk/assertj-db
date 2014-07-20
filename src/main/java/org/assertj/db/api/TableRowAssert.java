package org.assertj.db.api;

import org.assertj.db.error.AssertJDBException;
import org.assertj.db.type.Row;
import org.assertj.db.type.Table;

/**
 * Assertion methods about the data in a {@link Row} of a {@link Table}.
 * 
 * @author Régis Pouiller
 * 
 */
public class TableRowAssert extends AbstractRowAssert<TableAssert, Table, TableRowAssert, TableRowValueAssert> {

  /**
   * Constructor.
   * 
   * @param originalTableAssert The original assert ({@link TableAssert}).
   * @param row The row on which do assertion.
   */
  TableRowAssert(TableAssert originalTableAssert, Row row) {
    super(originalTableAssert, TableRowAssert.class, TableRowValueAssert.class, row);
  }

  /**
   * Returns to level of assertion methods on a {@link Table}.
   * 
   * @return a object of assertion methods on a {@link Table}.
   */
  public TableAssert returnToTable() {
    return returnToDbAssert();
  }

  /**
   * Returns assertion methods on the value corresponding to the column name in parameter.
   * 
   * @param columnName The column name.
   * @return An object to make assertions on the value.
   * @throws NullPointerException If the column name in parameter is null.
   * @throws AssertJDBException If there is no column with this name.
   */
  public TableRowValueAssert value(String columnName) {
    return new TableRowValueAssert(this, getValue(columnName));
  }

}
