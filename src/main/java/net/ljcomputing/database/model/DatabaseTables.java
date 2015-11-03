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

import net.ljcomputing.database.servcie.impl.SqlTypeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

/**
 * The Class DatabaseTables.
 *
 * @author James G. Willmore
 */
@Component
public class DatabaseTables {

  /** The Constant TABLE_CATALOG. */
  private static final String TABLE_CATALOG = "TABLE_CAT";

  /** The Constant TABLE_SCHEMA. */
  private static final String TABLE_SCHEMA = "TABLE_SCHEM";

  /** The Constant TABLE_NAME. */
  private static final String TABLE_NAME = "TABLE_NAME";

  /** The Constant COLUMN_NAME. */
  private static final String COLUMN_NAME = "COLUMN_NAME";

  /** The Constant TYPE_NAME. */
  private static final String TYPE_NAME = "TYPE_NAME";

  /** The Constant COLUMN_SIZE. */
  private static final String COLUMN_SIZE = "COLUMN_SIZE";

  private static final String DATA_TYPE = "DATA_TYPE";

  /** The tables. */
  private List<DatabaseTable> tables = new ArrayList<DatabaseTable>();

  /** The tables map. */
  private Map<String, Object> tablesMap = new HashMap<String, Object>();

  /**
   * Instantiates a new database tables.
   *
   * @param jdbcTemplate the jdbc template
   * @throws Exception the exception
   */
  @Autowired(required = false)
  public DatabaseTables(JdbcTemplate jdbcTemplate) throws Exception {
    this(jdbcTemplate.getDataSource());
  }

  /**
   * Instantiates a new database tables.
   *
   * @param dataSource the data source
   * @throws Exception the exception
   */
  @Autowired(required = false)
  public DatabaseTables(DataSource dataSource) throws Exception {
    this(dataSource.getConnection());
  }

  /**
   * Instantiates a new database tables.
   *
   * @param conn the conn
   * @throws Exception the exception
   */
  @Autowired(required = false)
  public DatabaseTables(Connection conn) throws Exception {
    init(conn);
  }

  /**
   * Inits the.
   *
   * @param conn the conn
   * @throws Exception the exception
   */
  private synchronized void init(Connection conn) throws Exception {
    DatabaseMetaData dbmd = conn.getMetaData();
    ResultSet rsdb = dbmd.getTables(null, null, "%", null);

    tablesMap.put("tables", tables);

    while (rsdb.next()) {

      DatabaseTable table = new DatabaseTable(rsdb.getString(TABLE_CATALOG),
          rsdb.getString(TABLE_SCHEMA), rsdb.getString(TABLE_NAME));

      ResultSet rstmdt = dbmd.getColumns(table.getTableCatalog(),
          table.getTableSchema(), table.getTableName(), null);

      while (rstmdt.next()) {
        DatabaseTableColumn column = new DatabaseTableColumn();
        column.setName(rstmdt.getString(COLUMN_NAME));
        column.setTypeName(rstmdt.getString(TYPE_NAME));
        column.setSize(rstmdt.getInt(COLUMN_SIZE));
        column.setClassName(
            SqlTypeMap.toClass(rstmdt.getInt(DATA_TYPE)).getSimpleName());

        table.addColumn(column);
      }

      tables.add(table);
    }
  }

  /**
   * Gets the tables.
   *
   * @return the tables
   */
  public List<DatabaseTable> getTables() {
    return tables;
  }

  /**
   * Gets the tables map.
   *
   * @return the tables map
   */
  public Map<String, Object> getTablesMap() {
    return tablesMap;
  }
}
