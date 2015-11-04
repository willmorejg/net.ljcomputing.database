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

package net.ljcomputing.database.servcie.impl;

import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;

import freemarker.template.Configuration;
import freemarker.template.TemplateHashModel;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstract implementation of a database conversion service.
 * 
 * @author James G. Willmore
 *
 */
public abstract class AbstractDatabaseConversionService {

  /** The output directory path. */
  protected String outputDirectoryPath;

  /** The output directory. */
  protected File outputDirectory;

  /** The Freemarker configuration. */
  protected Configuration freemarkerConfiguration;

  /** The root. */
  protected Map<String, Object> root;

  /** The wrapper. */
  protected BeansWrapper wrapper;

  /** The static models. */
  protected TemplateHashModel staticModels;

  /** The string utils. */
  protected TemplateHashModel stringUtils;

  /**
   * Instantiates a new abstract database conversion service.
   *
   * @param outputDirectoryPath the output directory path
   * @param freemarkerConfiguration the freemarker configuration
   * @throws Exception the exception
   */
  AbstractDatabaseConversionService(String outputDirectoryPath, 
      Configuration freemarkerConfiguration)
      throws Exception {
    this.outputDirectoryPath = outputDirectoryPath + "/";
    this.freemarkerConfiguration = freemarkerConfiguration;
    this.outputDirectory = new File(this.outputDirectoryPath);

    root = new HashMap<String, Object>();
    wrapper = new BeansWrapperBuilder(Configuration.VERSION_2_3_21).build();

    staticModels = wrapper.getStaticModels();
    stringUtils = (TemplateHashModel) staticModels.get("net.ljcomputing.StringUtils");

    root.put("stringUtils", stringUtils);
  }

  /**
   * Write to file.
   *
   * @param file the file @param content the content
   * @param content the content
   * @throws Exception the exception
   */
  protected void writeToFile(File file, String content) throws Exception {
    OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
    os.write(content.getBytes());
    os.flush();
    os.close();
  }
}
