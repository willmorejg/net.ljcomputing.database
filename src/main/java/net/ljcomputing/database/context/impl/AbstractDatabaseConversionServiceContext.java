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

import net.ljcomputing.database.context.ConversionServiceContext;

/**
 * The database conversion service context definition.
 * 
 * @author James G. Willmore
 *
 */
public abstract class AbstractDatabaseConversionServiceContext
    implements ConversionServiceContext {

  /** The output path. */
  private String outputDirectoryPath;

  /** The Freemarker configuration. */
  private Configuration freemarkerConfiguration;

  /** The output template. */
  protected String outputTemplate;

  /** The file suffix for the output file(s). */
  protected String fileSuffix;

  /**
   * Instantiates a new database conversion service context from builder.
   */
  public AbstractDatabaseConversionServiceContext() {
  }

  /*
   * (non-Javadoc)
   * 
   * @see net.ljcomputing.database.context.ConversionServiceContext#
   * createFreeMarkerContext(java.lang.String, freemarker.template.Configuration)
   */
  public void createFreeMarkerContext(String outputDirectoryPath,
      Configuration freemarkerConfiguration) {
    this.outputDirectoryPath = outputDirectoryPath;
    this.freemarkerConfiguration = freemarkerConfiguration;

  }

  /*
   * (non-Javadoc)
   * 
   * @see net.ljcomputing.database.context.ConversionServiceContext#
   * getOutputDirectoryPath()
   */
  public String getOutputDirectoryPath() {
    return outputDirectoryPath;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.ljcomputing.database.context.ConversionServiceContext#getOutputTemplate
   * ()
   */
  public String getOutputTemplate() {
    return outputTemplate;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.ljcomputing.database.context.ConversionServiceContext#getFileSuffix()
   */
  public String getFileSuffix() {
    return fileSuffix;
  }

  /*
   * (non-Javadoc)
   * 
   * @see net.ljcomputing.database.context.ConversionServiceContext#
   * getFreemarkerConfiguration()
   */
  public Configuration getFreemarkerConfiguration() {
    return freemarkerConfiguration;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "DatabaseConversionServiceContext [outputDirectoryPath="
        + outputDirectoryPath + ", outputTemplate=" + outputTemplate
        + ", fileSuffix=" + fileSuffix + ", freemarkerConfiguration="
        + freemarkerConfiguration + "]";
  }
}
