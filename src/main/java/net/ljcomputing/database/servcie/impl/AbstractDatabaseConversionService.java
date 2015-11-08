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

import net.ljcomputing.database.context.ConversionServiceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
abstract class AbstractDatabaseConversionService {

  /** The SLF4j logger. */
  private static Logger logger = LoggerFactory
      .getLogger(AbstractDatabaseConversionService.class);

  /** The output directory path. */
  protected String outputDirectoryPath;

  /** The output directory. */
  protected File outputDirectory;

  /** The root. */
  protected Map<String, Object> root;

  /** The wrapper. */
  protected BeansWrapper wrapper;

  /** The static models. */
  protected TemplateHashModel staticModels;

  /** The string utils. */
  protected TemplateHashModel stringUtils;

  /** The file suffix. */
  protected String fileSuffix;

  /**
   * Instantiates a new abstract database conversion service.
   *
   * @param context the context
   * @throws Exception the exception
   */
  protected AbstractDatabaseConversionService(
      ConversionServiceContext context) throws Exception {
    logger.debug("context: {}", context);
    this.outputDirectoryPath = context.getOutputDirectoryPath();
    this.fileSuffix = context.getFileSuffix();

    this.outputDirectory = new File(this.outputDirectoryPath);

    root = new HashMap<String, Object>();
    wrapper = new BeansWrapperBuilder(Configuration.VERSION_2_3_21).build();

    staticModels = wrapper.getStaticModels();
    stringUtils = (TemplateHashModel) staticModels
        .get("net.ljcomputing.StringUtils");

    root.put("stringUtils", stringUtils);
  }

  /**
   * Create the output file for the processed template content.
   *
   * @param tableName the table name
   * @return the file
   */
  protected File outputFile(String tableName) {
    StringBuffer buf = new StringBuffer(outputDirectoryPath);

    buf.append(tableName);
    buf.append("." + fileSuffix);

    File file = new File(buf.toString());

    logger.info(" ... output file name set to {}", file.getAbsolutePath());

    return file;
  }

  /**
   * Write the processed template content to file.
   *
   * @param file the file 
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
