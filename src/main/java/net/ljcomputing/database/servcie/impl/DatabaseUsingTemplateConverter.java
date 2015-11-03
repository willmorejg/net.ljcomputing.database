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

import freemarker.template.Configuration;

import net.ljcomputing.StringUtils;
import net.ljcomputing.database.model.DatabaseTable;
import net.ljcomputing.database.model.DatabaseTables;
import net.ljcomputing.database.servcie.DatabaseConversionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Converts zero to many database tables using Freemarker template(s).
 * 
 * @author James G. Willmore
 *
 */
public class DatabaseUsingTemplateConverter extends AbstractDatabaseConversionService
    implements DatabaseConversionService {
  private static Logger logger = LoggerFactory.getLogger(DatabaseUsingTemplateConverter.class);

  /** The file suffix for the output file(s). */
  protected String fileSuffix;

  /** The output template. */
  protected String outputTemplate;

  /**
   * Instantiates a new database using template converter.
   *
   * @param outputTemplate the output template
   * @param fileSuffix the file suffix
   * @param outputDirectoryPath the output directory path
   * @param freemarkerConfiguration the freemarker configuration
   * @throws Exception the exception
   */
  DatabaseUsingTemplateConverter(String outputTemplate, String fileSuffix, 
      String outputDirectoryPath, Configuration freemarkerConfiguration) 
          throws Exception {
    super(outputDirectoryPath, freemarkerConfiguration);
    this.outputTemplate = outputTemplate;
    this.fileSuffix = fileSuffix;
  }

  /**
   * Process template.
   *
   * @param databaseTables the database tables
   * @param template the template
   * @return the map
   * @throws Exception the exception
   */
  private Map<String, String> processTemplate(DatabaseTables databaseTables, String template) 
      throws Exception {
    Map<String, String> results = new HashMap<String, String>();

    for (DatabaseTable table : databaseTables.getTables()) {
      results.put(StringUtils.camelCase(table.getTableName()), processTemplate(table, template));
    }

    return results;
  }

  /**
   * Process template.
   *
   * @param databaseTable the database table
   * @param template the template
   * @return the string
   * @throws Exception the exception
   */
  private String processTemplate(DatabaseTable databaseTable, String template) throws Exception {
    root.put("root", databaseTable);
    return processTemplate(template);
  }

  /**
   * Process template.
   *
   * @param template the template
   * @return the string
   * @throws Exception the exception
   */
  private String processTemplate(String template) throws Exception {
    StringWriter out = new StringWriter();

    freemarkerConfiguration.getTemplate(template).process(root, out);

    return out.getBuffer().toString();
  }

  /**
   * Output file.
   *
   * @param tableName the table name
   * @return the file
   */
  private File outputFile(String tableName) {
    StringBuffer buf = new StringBuffer(outputDirectoryPath);

    buf.append(tableName);
    buf.append("." + fileSuffix);

    File file = new File(buf.toString());

    logger.info(" ... output file name set to {}", file.getAbsolutePath());

    return file;
  }

  /**
   * @see net.ljcomputing.database.servcie.DatabaseConversionService#process(net.ljcomputing.database.model.DatabaseTables)
   */
  public void process(DatabaseTables databaseTables) throws Exception {
    Map<String, String> map = processTemplate(databaseTables, outputTemplate);

    Iterator<String> it = map.keySet().iterator();
    while (it.hasNext()) {
      String key = it.next();
      File file = outputFile(key);
      writeToFile(file, map.get(key));
    }
  }
}
