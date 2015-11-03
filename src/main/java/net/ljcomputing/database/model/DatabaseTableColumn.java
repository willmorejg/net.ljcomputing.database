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
   * Gets the name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name.
   *
   * @param name the new name
   */
  public void setName(String name) {
    this.name = name;
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
   * Sets the type name.
   *
   * @param typeName the new type name
   */
  public void setTypeName(String typeName) {
    this.typeName = typeName;
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
   * Sets the size.
   *
   * @param size the new size
   */
  public void setSize(Integer size) {
    this.size = size;
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
