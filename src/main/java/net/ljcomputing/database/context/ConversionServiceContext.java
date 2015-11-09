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

package net.ljcomputing.database.context;

import freemarker.template.Configuration;

/**
 *  Interface for database conversion service context definition.
 *  
 * @author James G. Willmore
 *
 */
public interface ConversionServiceContext {

  /**
   * Creates the FreeMarker context.
   *
   * @param outputDirectoryPath the output directory path
   * @param freemarkerConfiguration the FreeMarker configuration
   */
  public void createFreeMarkerContext(String outputDirectoryPath,
      Configuration freemarkerConfiguration);

  /**
   * Gets the output directory path.
   *
   * @return the output directory path
   */
  public String getOutputDirectoryPath();

  /**
   * Gets the output template.
   *
   * @return the output template
   */
  public String getOutputTemplate();

  /**
   * Gets the file suffix.
   *
   * @return the file suffix
   */
  public String getFileSuffix();

  /**
   * Gets the Freemarker configuration.
   *
   * @return the Freemarker configuration
   */
  public Configuration getFreemarkerConfiguration();
}
