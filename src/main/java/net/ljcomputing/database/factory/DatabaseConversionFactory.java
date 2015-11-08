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

package net.ljcomputing.database.factory;

import net.ljcomputing.database.servcie.DatabaseConversionService;
import net.ljcomputing.database.strategy.DatabaseConversionStrategyType;

/**
 * Database conversion service factory - creates a conversion service based upon
 * the strategy type requested.
 * 
 * @author James G. Willmore
 *
 */
public interface DatabaseConversionFactory {

  /**
   * Creates a new DatabaseConversion object.
   *
   * @param conversionStrategy the conversion strategy
   * @return the database conversion service
   * @throws Exception the exception
   */
  public DatabaseConversionService createConversionService(
      DatabaseConversionStrategyType.Type conversionStrategy) throws Exception;
}
