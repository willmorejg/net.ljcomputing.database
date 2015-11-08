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
import net.ljcomputing.database.strategy.impl.JsonConversionStrategy;
import net.ljcomputing.database.strategy.impl.XmlConversionStrategy;

import org.springframework.stereotype.Component;

/**
 * Enumeration of database conversion strategies.
 * 
 * @author James G. Willmore
 *
 */
@Component
public class DatabaseConversionStrategyType {
  public enum Type {
    CLASS(new ClassConversionStrategy()), XML(new XmlConversionStrategy()), JSON(
        new JsonConversionStrategy());
  
    /** The database conversion strategy. */
    private DatabaseConversionStrategy strategy;
  
    /**
     * Instantiates a new database conversion strategy type.
     *
     * @param strategy the strategy
     */
    private Type(DatabaseConversionStrategy strategy) {
      this.strategy = strategy;
    }
  
    /**
     * Get the the database conversion strategy.
     *
     * @return the database conversion strategy
     */
    public DatabaseConversionStrategy strategy() {
      return strategy;
    }
  }
}
