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

import java.sql.ResultSetMetaData;

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

  /** indicates if the column is nullable. */
  private Boolean nullable;

  /**
   * Instantiates a new database table column.
   *
   * @param rsmd the rsmd
   * @param columnNumber the column number
   * @throws Exception the exception
   */
  public DatabaseTableColumn(ResultSetMetaData rsmd, int columnNumber)
      throws Exception {
    name = rsmd.getColumnName(columnNumber);
    typeName = rsmd.getColumnTypeName(columnNumber);
    size = rsmd.getColumnDisplaySize(columnNumber);
    className = SqlTypeMap.toClass(rsmd.getColumnType(columnNumber))
        .getSimpleName();
    nullable = (rsmd.isNullable(columnNumber) == 0);
  }

  /**
   * Instantiates a new database table column from builder.
   *
   * @param builder the builder
   */
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

  /**
   * Checks if the column is nullable.
   *
   * @return the boolean
   */
  public Boolean isNullable() {
    return nullable;
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
