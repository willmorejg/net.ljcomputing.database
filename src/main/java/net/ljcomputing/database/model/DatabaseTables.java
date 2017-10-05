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

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import net.ljcomputing.StringUtils;

/**
 * A POJO to read a database schema and obtain a List of database table definitions.
 *
 * @author James G. Willmore
 */
@Component
public class DatabaseTables {

  /** The SFL4J logger. */
  private static Logger logger = LoggerFactory.getLogger(DatabaseTables.class);

  /** The Constant TABLE_CATALOG. */
  static final String TABLE_CATALOG = "TABLE_CAT";

  /** The Constant TABLE_SCHEMA. */
  static final String TABLE_SCHEMA = "TABLE_SCHEM";

  /** The Constant TABLE_NAME. */
  static final String TABLE_NAME = "TABLE_NAME";
  
  //*may* need in future - right now, not
//  static final String COLUMN_NAME = "COLUMN_NAME";
//  static final String TYPE_NAME = "TYPE_NAME";
//  static final String COLUMN_SIZE = "COLUMN_SIZE";
//  static final String DATA_TYPE = "DATA_TYPE";
//  static final String NULLABLE = "NULLABLE";
//  static final String ORDINAL_POSITION = "ORDINAL_POSITION";
//// --
//  static final String BUFFER_LENGTH = "BUFFER_LENGTH";
//  static final String DECIMAL_DIGITS = "DECIMAL_DIGITS";
//  static final String NUM_PREC_RADIX = "NUM_PREC_RADIX";
//  static final String REMARKS = "REMARKS";
//  static final String COLUMN_DEF = "COLUMN_DEF";
//  static final String SQL_DATA_TYPE = "SQL_DATA_TYPE";
//  static final String SQL_DATETIME_SUB = "SQL_DATETIME_SUB";
//  static final String CHAR_OCTET_LENGTH = "CHAR_OCTET_LENGTH";
//  static final String IS_AUTOINCREMENT = "IS_AUTOINCREMENT";
//  static final String IS_GENERATEDCOLUMN = "IS_GENERATEDCOLUMN";
////----
//  static final String SCOPE_CATALOG = "SCOPE_CATALOG";
//  static final String SCOPE_SCHEMA = "SCOPE_SCHEMA";
//  static final String SCOPE_TABLE = "SCOPE_TABLE";
//  static final String SOURCE_DATA_TYPE = "SOURCE_DATA_TYPE";

  /** The database tables. */
  private List<DatabaseTable> tables = new ArrayList<DatabaseTable>();

  /** The database tables map. */
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
   * Initializes the database table Object.
   *
   * @param conn the database connection used to read the database schema
   * @throws Exception an Exception encountered while instantiating the Object
   */
  private synchronized void init(Connection conn) throws Exception {
    DatabaseMetaData dbmd = conn.getMetaData();
    ResultSet rsdb = dbmd.getTables(null, null, "%", new String[] {"TABLE"});

    tablesMap.put("tables", tables);

    while (rsdb.next()) {
      DatabaseTable table = new DatabaseTable(rsdb);

      Map<String, Map<String, String>> keys = getForeignKeys(dbmd, table.getTableName());

      Statement stmt = conn.createStatement();

      // in order to get information about the table directly using the methods
      // in the ResultSetMetaData class, we have to execute a query -
      // the query returns no rows from the table
      ResultSet rs = stmt
          .executeQuery("select * from " + table.getTableName() + " where 1=0");

      ResultSetMetaData rsmdt = rs.getMetaData();
      int columnCount = rsmdt.getColumnCount();

      for (int i = 1; i <= columnCount; i++) {
        DatabaseTableColumn dbColumn = new DatabaseTableColumn(rsmdt, i);
        
        //TODO - refactor
        if (keys.containsKey(dbColumn.getName())) {
          String columnName = dbColumn.getName();
          Map<String, String> map = keys.get(columnName);
          Set<String> keySet = map.keySet();
          Object[] keySetArray = keySet.toArray();
          dbColumn.setClassName(StringUtils.camelCase(keySetArray[0].toString()));
          dbColumn.setForeignKey(true);
        }
        
        table.addColumn(dbColumn);
      }

      rs.close();
      stmt.close();
      
      tables.add(table);
    }
  }
  
  private Map<String, Map<String, String>> getForeignKeys(DatabaseMetaData dbmd, String tableName) 
      throws Exception {
    Map<String, Map<String, String>> keys = new HashMap<>();
    ResultSet rsdb = dbmd.getImportedKeys(null, null, tableName);

    while (rsdb.next()) {
      ResultSetMetaData rsmd = rsdb.getMetaData();
      int columnCount = rsmd.getColumnCount();

      for (int column = 1; column != columnCount; column++) {
        String columnName = rsmd.getColumnName(column);
        Object columnValue = rsdb.getObject(column);
      }

      String myColumn = rsdb.getObject("fkcolumn_name").toString();
      String otherTable = rsdb.getObject("pktable_name").toString();
      String otherColumn = rsdb.getObject("pkcolumn_name").toString();

      Map<String, String> map = new HashMap<String, String>();
      map.put(otherTable, otherColumn);
      keys.put(myColumn, map);
    }

    rsdb.close();

    return keys;
  }

  /**
   * Gets the database tables.
   *
   * @return the tables
   */
  public List<DatabaseTable> getTables() {
    return tables;
  }

  /**
   * Gets the database tables map.
   *
   * @return the tables map
   */
  public Map<String, Object> getTablesMap() {
    return tablesMap;
  }
}
