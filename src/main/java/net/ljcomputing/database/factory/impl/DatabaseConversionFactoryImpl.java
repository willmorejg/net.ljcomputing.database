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

package net.ljcomputing.database.factory.impl;

import freemarker.template.Configuration;

import net.ljcomputing.database.context.ConversionServiceContext;
import net.ljcomputing.database.factory.DatabaseConversionFactory;
import net.ljcomputing.database.servcie.DatabaseConversionService;
import net.ljcomputing.database.servcie.impl.DatabaseUsingTemplateConverter;
import net.ljcomputing.database.strategy.DatabaseConversionStrategy;
import net.ljcomputing.database.strategy.DatabaseConversionStrategyType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Database conversion service factory - creates a conversion service based upon
 * the strategy type requested.
 *
 * @author James G. Willmore
 */
@Service
public class DatabaseConversionFactoryImpl
    implements DatabaseConversionFactory {

  /** SLF4J The logger. */
  private static Logger logger = LoggerFactory
      .getLogger(DatabaseConversionFactoryImpl.class);

  /** The instance. */
  private static DatabaseConversionFactoryImpl instance;

  /** The output path. */
  @Value("${database.output.path:out}")
  private String outputDirectoryPath;

  /** The Freemarker configuration. */
  @Autowired
  private Configuration freemarkerConfiguration;

  /**
   * Instantiates a new database conversion factory impl.
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
   * Creates the conversion service.
   *
   * @param conversionStrategy the conversion strategy
   * @return the database conversion service
   * @throws Exception the exception
   * @see net.ljcomputing.database.factory.DatabaseConversionFactory
   * #createConversionService(net.ljcomputing.database.strategy.DatabaseConversionStrategyType.Type)
   */
  public DatabaseConversionService createConversionService(
      DatabaseConversionStrategyType conversionStrategy) throws Exception {
    DatabaseConversionStrategy strategy = conversionStrategy.strategy();
    ConversionServiceContext context = strategy.getContext();
    context.createFreeMarkerContext(outputDirectoryPath, freemarkerConfiguration);

    logger.debug("context : {}", context);
    return new DatabaseUsingTemplateConverter(context);
  }
}
