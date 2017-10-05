/**
 * 
 */

package net.ljcomputing.database.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Properties defining a database conversion strategy.
 * 
 * @author James G. Willmore
 *
 */
public class DbConversionStrategyTypeProperties {

  private Logger logger = LoggerFactory.getLogger(DbConversionStrategyTypeProperties.class);

  /** The database conversion strategy. */
  private DatabaseConversionStrategy strategy;

  /** The database conversion strategy name. */
  private String name;

  /** The database conversion strategy class name. */
  private String classname;

  /** The properties prefix. */
  private String prefix;

  /**
   * Gets the strategy name.
   *
   * @return the strategy name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the strategy name.
   *
   * @param name
   *          the new strategy name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the strategy classname.
   *
   * @return the strategy classname
   */
  public String getClassName() {
    return classname;
  }

  /**
   * Sets the strategy classname.
   *
   * @param classname
   *          the new strategy classname
   */
  public void setClassname(String classname) {
    this.classname = classname;
    initStrategy();
  }

  /**
   * Gets the properties prefix.
   *
   * @return the properties prefix
   */
  public String getPrefix() {
    return prefix;
  }

  /**
   * Sets the properties prefix.
   *
   * @param prefix
   *          the new properties prefix
   */
  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  /**
   * Gets the strategy.
   *
   * @return the strategy
   */
  public DatabaseConversionStrategy getStrategy() {
    return strategy;
  }

  private void initStrategy() {
    Class<?> klass = null;
    try {
      klass = Class.forName(classname);
      strategy = (DatabaseConversionStrategy) klass.newInstance();
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException exception) {
      logger.error("Could not instaniate strategy {}:", name, exception);
    }
  }

  @Override
  public String toString() {
    return "DbConversionStrategyTypeProperties [" 
        + "strategy=" + strategy 
        + ", name=" + name + ", classname="
        + classname + ", prefix=" + prefix + "]";
  }
}
