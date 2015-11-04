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
    className = SqlTypeMap.toClass(rs.getInt(DatabaseTables.DATA_TYPE))
        .getSimpleName();
  }
  
  private DatabaseTableColumn(Builder builder) {
    this.name = builder.name;
    this.typeName = builder.typeName;
    this.size = builder.size;
    this.className = builder.className;
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

  /**
   * Database table column builder.
   */
  public static class Builder {

    /** The name. */
    private String name;

    /** The type name. */
    private String typeName;

    /** The size. */
    private Integer size;

    /** The class name. */
    private String className;

    /**
     * Instantiates a new builder.
     */
    public Builder(String name) {
      this.name = name;
    }

    /**
     * Type name.
     *
     * @param typeName the type name
     * @return the builder
     */
    public Builder typeName(String typeName) {
      this.typeName = typeName;
      return this;
    }

    /**
     * Size.
     *
     * @param size the size
     * @return the builder
     */
    public Builder size(Integer size) {
      this.size = size;
      return this;
    }

    /**
     * Class name.
     *
     * @param className the class name
     * @return the builder
     */
    public Builder className(String className) {
      this.className = className;
      return this;
    }
    
    /**
     * Builds a new database table column.
     *
     * @return the database table column
     */
    public DatabaseTableColumn build() {
      return new DatabaseTableColumn(this);
    }
  }
}
