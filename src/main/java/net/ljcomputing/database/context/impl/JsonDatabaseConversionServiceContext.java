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
 * The JSON database conversion service context definition.
 *
 * @author James G. Willmore
 */
public class JsonDatabaseConversionServiceContext
    extends AbstractDatabaseConversionServiceContext {

  /**
   * Instantiates a new JSON database conversion service context.
   *
   * @param outputDirectoryPath the output directory path
   * @param freemarkerConfiguration the freemarker configuration
   */
  public JsonDatabaseConversionServiceContext(String outputDirectoryPath,
      Configuration freemarkerConfiguration) {
    super(outputDirectoryPath, freemarkerConfiguration);
    super.outputTemplate = "jsonTemplate.ftl";
    super.fileSuffix = "json";
  }

}
