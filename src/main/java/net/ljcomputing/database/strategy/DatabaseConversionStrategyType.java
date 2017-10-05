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

package net.ljcomputing.database.strategy;

import net.ljcomputing.database.strategy.impl.ClassConversionStrategy;
import net.ljcomputing.database.strategy.impl.JsConversionStrategy;
import net.ljcomputing.database.strategy.impl.JsonConversionStrategy;
import net.ljcomputing.database.strategy.impl.SqlConversionStrategy;
import net.ljcomputing.database.strategy.impl.XmlConversionStrategy;

/**
 * Enumeration of database conversion strategies.
 * 
 * @author James G. Willmore
 *
 */
public enum DatabaseConversionStrategyType {
  CLASS(new ClassConversionStrategy(), "class"), XML(new XmlConversionStrategy(), "xml"), JSON(
      new JsonConversionStrategy(), "json"), SQL(new SqlConversionStrategy(), "sql"),
  JS(new JsConversionStrategy(), "js");

  /** The database conversion strategy. */
  private DatabaseConversionStrategy strategy;
  
  /** The properties prefix. */
  private String propertiesPrefix;

  /**
   * Instantiates a new database conversion strategy type.
   *
   * @param strategy the strategy
   * @param propertiesPrefix the properties prefix
   */
  private DatabaseConversionStrategyType(DatabaseConversionStrategy strategy,
      String propertiesPrefix) {
    this.strategy = strategy;
    this.propertiesPrefix = propertiesPrefix;
  }

  /**
   * Get the the database conversion strategy.
   *
   * @return the database conversion strategy
   */
  public DatabaseConversionStrategy strategy() {
    return strategy;
  }
  
  /**
   * Get the properties prefix for the database conversion strategy.
   *
   * @return the string
   */
  public String propertiesPrefix() {
    return propertiesPrefix;
  }
}
