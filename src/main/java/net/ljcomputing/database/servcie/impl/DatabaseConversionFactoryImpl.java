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

import net.ljcomputing.database.servcie.DatabaseConversionFactory;
import net.ljcomputing.database.servcie.DatabaseConversionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Implementation of a database conversion factory.
 *
 * @author James G. Willmore
 */
@Service
public class DatabaseConversionFactoryImpl implements DatabaseConversionFactory {

  /** The static instance of the database conversion service factory. */
  private static DatabaseConversionFactoryImpl instance = null;

  /** The Freemarker configuration. */
  @Autowired
  protected Configuration freemarkerConfiguration;

  /** The output path. */
  @Value("${database.output.path:out}")
  private String outputPath;

  /** The class template. */
  @Value("${database.template.class:classTemplate.ftl}")
  private String classTemplate;

  /** The class suffix. */
  @Value("${database.suffix.class:java}")
  private String classSuffix;

  /** The xml template. */
  @Value("${database.template.xml:xmlTemplate.ftl}")
  private String xmlTemplate;

  @Value("${database.suffix.xml:xml}")
  private String xmlSuffix;

  /** The json template. */
  @Value("${database.template.json:jsonTemplate.ftl}")
  private String jsonTemplate;

  @Value("${database.suffix.json:json}")
  private String jsonSuffix;

  /**
   * Instantiates a new database conversion factory.
   */
  private DatabaseConversionFactoryImpl() {
    super();
  }

  /**
   * Gets the single instance of the database conversion service factory.
   *
   * @return single instance of the database conversion service factory.
   */
  public static synchronized DatabaseConversionFactoryImpl getInstance() {
    if (null == instance) {
      instance = new DatabaseConversionFactoryImpl();
    }

    return instance;
  }

  /**
   * @see net.ljcomputing.database.servcie.DatabaseConversionFactory#createConversionService(net.ljcomputing.database.servcie.DatabaseConversionFactory.ServiceType)
   */
  public DatabaseConversionService createConversionService(ServiceType serviceType) 
      throws Exception {
    switch (serviceType) {
      case CLASS:
        return new DatabaseUsingTemplateConverter(
            classTemplate, classSuffix, outputPath, freemarkerConfiguration);
      case XML:
        return new DatabaseUsingTemplateConverter(
            xmlTemplate, xmlSuffix, outputPath, freemarkerConfiguration);
      case JSON:
        return new DatabaseUsingTemplateConverter(
            jsonTemplate, jsonSuffix, outputPath, freemarkerConfiguration);
      default:
        return null;
    }
  }
}
