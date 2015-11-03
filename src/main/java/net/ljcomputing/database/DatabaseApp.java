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

import net.ljcomputing.database.model.DatabaseTables;
import net.ljcomputing.database.servcie.DatabaseConversionFactory;
import net.ljcomputing.database.servcie.DatabaseConversionFactory.ServiceType;
import net.ljcomputing.database.servcie.DatabaseConversionService;

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

import java.io.File;

/**
 * @author James G. Willmore
 *
 */
@Configuration
@EnableAutoConfiguration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "net.ljcomputing.database.*" })
public class DatabaseApp implements CommandLineRunner {

  /** The logger. */
  private static Logger logger = LoggerFactory.getLogger(DatabaseApp.class);

  @Autowired
  private DatabaseTables databaseTables;

  @Value("${database.output.path:out/}")
  private String outputPath;

  @Autowired
  private DatabaseConversionFactory databaseConversionFactory;

  /**
   * The main method.
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(DatabaseApp.class, args);
  }

  /**
   * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
   */
  public void run(String... args) throws Exception {
    try {
      File outputDirectory = new File(outputPath);
      outputDirectory.delete();
      outputDirectory.mkdir();

      DatabaseConversionService conversionService = databaseConversionFactory
          .createConversionService(ServiceType.CLASS);
      conversionService.process(databaseTables);

      conversionService = databaseConversionFactory.createConversionService(ServiceType.XML);
      conversionService.process(databaseTables);

      conversionService = databaseConversionFactory.createConversionService(ServiceType.JSON);
      conversionService.process(databaseTables);
    } catch (Exception e) {
      logger.error("An exception occured: ", e);
    }
  }

}
