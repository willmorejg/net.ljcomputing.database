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

package net.ljcomputing.database.servcie;

/**
 * Database conversion service factory - creates a conversion service based upon
 * the service type requested.
 * 
 * @author James G. Willmore
 *
 */
public interface DatabaseConversionFactory {

  /**
   * Conversion service type.
   */
  public enum ServiceType {
    CLASS, XML, JSON
  }

  /**
   * Creates a new DatabaseConversion object.
   *
   * @param serviceType the service type
   * @return the database conversion service
   * @throws Exception the exception
   */
  public DatabaseConversionService createConversionService(ServiceType serviceType) 
      throws Exception;
}
