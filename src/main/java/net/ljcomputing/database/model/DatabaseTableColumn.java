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

import java.sql.ResultSet;

/**
 * A database table column representation.
 * 
 * @author James G. Willmore
 *
 */
public class DatabaseTableColumn {

  /** The name. */
  private String name;

  /** The type name. */
  private String typeName;

  /** The size. */
  private Integer size;

  /** The class name. */
  private String className;
  
  /**
   * Instantiates a new database table column from a ResultSet.
   *
   * @param rs the ResultSet
   * @throws Exception the Exception
   */
  public DatabaseTableColumn(ResultSet rs) throws Exception {
    name = rs.getString(DatabaseTables.COLUMN_NAME);
    typeName = rs.getString(DatabaseTables.TYPE_NAME);
    size = rs.getInt(DatabaseTables.COLUMN_SIZE);
    className = 
        SqlTypeMap.toClass(rs.getInt(DatabaseTables.DATA_TYPE)).getSimpleName();
  }

  /**
   * Gets the name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the type name.
   *
   * @return the type name
   */
  public String getTypeName() {
    return typeName;
  }

  /**
   * Gets the size.
   *
   * @return the size
   */
  public Integer getSize() {
    return size;
  }

  /**
   * Gets the class name.
   *
   * @return the class name
   */
  public String getClassName() {
    return className;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Column [name=" + name + ", typeName=" + typeName + ", size=" + size 
        + ", className=" + className + "]";
  }
}
