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

package net.ljcomputing.database;

import net.ljcomputing.database.factory.DatabaseConversionFactory;
import net.ljcomputing.database.model.DatabaseTables;
import net.ljcomputing.database.servcie.DatabaseConversionService;
import net.ljcomputing.database.strategy.DatabaseConversionStrategyType;
import net.ljcomputing.database.strategy.DatabaseConversionStrategyTypes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import java.io.File;

/**
 * Configuration and command line runner for the net.ljcomputing.database application.
 * 
 * @author James G. Willmore
 *
 */
@Configuration
@EnableAutoConfiguration
@Import(DatabaseConversionStrategyTypes.class)
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "net.ljcomputing.database.*" })
public class DatabaseApp implements CommandLineRunner {

  /** The SFL4J logger. */
  private static Logger logger = LoggerFactory.getLogger(DatabaseApp.class);

  /** The database tables. */
  @Autowired
  private DatabaseTables databaseTables;

  /** The output path. */
  @Value("${database.output.path:out/}")
  private String outputPath;

  /** The database conversion factory. */
  @Autowired
  private DatabaseConversionFactory databaseConversionFactory;
  
  /** The database conversion strategy types. */
  @Autowired
  private DatabaseConversionStrategyTypes types;

  /**
   * The main method.
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(DatabaseApp.class, args);
  }

  /**
   * Run the application.
   *
   * @param args the args
   * @throws Exception the exception
   * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
   */
  public void run(String... args) throws Exception {
    try {
      logger.info("==================type: {}", types.getStrategyType("a"));
      
      File outputDirectory = new File(outputPath);
      outputDirectory.delete();
      outputDirectory.mkdir();

      DatabaseConversionService conversionService = databaseConversionFactory
          .createConversionService(DatabaseConversionStrategyType.CLASS);
      conversionService.process(databaseTables);

      conversionService = databaseConversionFactory
          .createConversionService(DatabaseConversionStrategyType.XML);
      conversionService.process(databaseTables);

      conversionService = databaseConversionFactory
          .createConversionService(DatabaseConversionStrategyType.JSON);
      conversionService.process(databaseTables);

      conversionService = databaseConversionFactory
          .createConversionService(DatabaseConversionStrategyType.JS);
      conversionService.process(databaseTables);
    } catch (Exception e) {
      logger.error("An exception occured: ", e);
    }
  }

}
