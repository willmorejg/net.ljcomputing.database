/**
           Copyright 2015, James G. Willmore

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package net.ljcomputing.database.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A database table representation.
 * 
 * @author James G. Willmore
 *
 */
public class DatabaseTable {

  /** The table catalog. */
  private String tableCatalog;

  /** The table schema. */
  private String tableSchema;

  /** The table name. */
  private String tableName;

  /** The columns. */
  private List<DatabaseTableColumn> columns = new ArrayList<DatabaseTableColumn>();

  /**
   * Instantiates a new database table.
   *
   * @param tableCatalog the table catalog
   * @param tableSchema the table schema
   * @param tableName the table name
   */
  public DatabaseTable(String tableCatalog, String tableSchema, String tableName) {
    this.tableCatalog = tableCatalog;
    this.tableSchema = tableSchema;
    this.tableName = tableName;
  }

  /**
   * Gets the table catalog.
   *
   * @return the table catalog
   */
  public String getTableCatalog() {
    return tableCatalog;
  }

  /**
   * Gets the table schema.
   *
   * @return the table schema
   */
  public String getTableSchema() {
    return tableSchema;
  }

  /**
   * Gets the table name.
   *
   * @return the table name
   */
  public String getTableName() {
    return tableName;
  }

  /**
   * Gets the columns.
   *
   * @return the columns
   */
  public List<DatabaseTableColumn> getColumns() {
    return columns;
  }
  
  /**
   * Adds the column.
   *
   * @param column the column
   */
  public void addColumn(DatabaseTableColumn column) {
    columns.add(column);
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Table [tableCatalog=" + tableCatalog + ", tableSchema=" 
        + tableSchema + ", tableName=" + tableName
        + ", columns=" + columns + "]";
  }

}
