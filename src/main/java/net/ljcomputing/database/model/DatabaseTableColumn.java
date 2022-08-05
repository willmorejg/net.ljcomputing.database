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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A database table column representation.
 * 
 * @author James G. Willmore
 *
 */
public class DatabaseTableColumn {
  private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseTableColumn.class);

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
  
  /** The data type. */
  private Integer dataType;
  
  /** The foreign key. */
  private boolean foreignKey = false;

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
    final Class<?> klass = SqlTypeMap.toClass(rsmd.getColumnType(columnNumber));
    className = klass.getSimpleName();
    LOGGER.debug("name: {}, className: {}, typeName: {}, typeNumber: {}", name, className, rsmd.getColumnTypeName(columnNumber), rsmd.getColumnType(columnNumber));
    dataType = rsmd.getColumnType(columnNumber);
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
    this.dataType = builder.dataType;
    this.foreignKey = builder.foreignKey;
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
   * Sets the class name.
   *
   * @param className the new class name
   */
  public void setClassName(String className) {
    this.className = className;
  }

  /**
   * Checks if the column is nullable.
   *
   * @return the boolean
   */
  public Boolean isNullable() {
    return nullable;
  }

  /**
   * Gets the data type.
   *
   * @return the integer
   */
  public Integer gatDataType() {
    return dataType;
  }
  
  /**
   * Checks if is foreign key.
   *
   * @return the boolean
   */
  public boolean foreignKey() {
    return foreignKey;
  }
  
  /**
   * Sets the foreign key.
   *
   * @param foreignKey the new foreign key
   */
  public void setForeignKey(Boolean foreignKey) {
    this.foreignKey = foreignKey;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Column [name=" + name + ", typeName=" + typeName + ", size=" + size
        + ", className=" + className + ", dataType=" + dataType + "]";
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
    
    /** The data type. */
    private Integer dataType;
    
    /** The foreign key. */
    private boolean foreignKey = false;

    /**
     * Instantiates a new builder.
     *
     * @param name the name
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
     * Data type.
     *
     * @param dataType the data type
     * @return the builder
     */
    public Builder dataType(Integer dataType) {
      this.dataType = dataType;
      return this;
    }
    
    /**
     * Foreign key.
     *
     * @param foreignKey the foreign key
     * @return the builder
     */
    public Builder foreignKey(boolean foreignKey) {
      this.foreignKey = foreignKey;
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
