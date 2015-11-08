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

package net.ljcomputing.database.context.impl;

import freemarker.template.Configuration;

/**
 * A generic database conversion service context definition.
 *
 * @author James G. Willmore
 */
public class DatabaseConversionServiceContext
    extends AbstractDatabaseConversionServiceContext {

  /**
   * Instantiates a new generic database conversion service context.
   *
   * @param outputDirectoryPath the output directory path
   * @param freemarkerConfiguration the freemarker configuration
   * @param template the template
   * @param suffix the suffix
   */
  public DatabaseConversionServiceContext(String outputDirectoryPath,
      Configuration freemarkerConfiguration, String template, String suffix) {
    super(outputDirectoryPath, freemarkerConfiguration);
    super.outputTemplate = template;
    super.fileSuffix = suffix;
  }

}
