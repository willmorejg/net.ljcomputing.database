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
import net.ljcomputing.database.context.ConversionServiceContext;
import net.ljcomputing.database.model.DatabaseTable;
import net.ljcomputing.database.model.DatabaseTables;
import net.ljcomputing.database.servcie.DatabaseConversionService;

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
public class DatabaseUsingTemplateConverter extends
    AbstractDatabaseConversionService implements DatabaseConversionService {

  /** The output template. */
  protected String outputTemplate;

  /** The Freemarker configuration. */
  protected Configuration freemarkerConfiguration;

  /**
   * Instantiates a new database using template converter.
   *
   * @param context the context
   * @throws Exception the exception
   */
  public DatabaseUsingTemplateConverter(ConversionServiceContext context)
      throws Exception {
    super(context);
    
    this.outputTemplate = context.getOutputTemplate();
    this.freemarkerConfiguration = context.getFreemarkerConfiguration();
  }

  /**
   * Process template.
   *
   * @param databaseTables the database tables
   * @param template the template
   * @return the map
   * @throws Exception the exception
   */
  private Map<String, String> processTemplate(DatabaseTables databaseTables,
      String template) throws Exception {
    Map<String, String> results = new HashMap<>();

    for (DatabaseTable table : databaseTables.getTables()) {
      results.put(StringUtils.camelCase(table.getTableName()),
          processTemplate(table, template));
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
  private String processTemplate(DatabaseTable databaseTable, String template)
      throws Exception {
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
   * @see net.ljcomputing.database.servcie.DatabaseConversionService#process(
   * net.ljcomputing.database.model.DatabaseTables)
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
