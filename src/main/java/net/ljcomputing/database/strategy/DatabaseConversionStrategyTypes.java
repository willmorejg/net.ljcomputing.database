/**
 * 
 */

package net.ljcomputing.database.strategy;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Properties defining database conversion strategies.
 * 
 * @author James G. Willmore
 *
 */
@Component
@ConfigurationProperties(prefix = "strategy")
public class DatabaseConversionStrategyTypes {

  private Logger logger = LoggerFactory.getLogger(DatabaseConversionStrategyTypes.class);

  /** The type. */
  private Map<String, DbConversionStrategyTypeProperties> type;

  /**
   * Gets the type.
   *
   * @return the type
   */
  public Map<String, DbConversionStrategyTypeProperties> getType() {
    return type;
  }

  /**
   * Sets the type.
   *
   * @param type the type
   */
  public void setType(Map<String, DbConversionStrategyTypeProperties> type) {
    this.type = type;
  }

  /**
   * Gets the strategy type.
   *
   * @param strategyType the strategy type
   * @return the strategy type
   */
  public DbConversionStrategyTypeProperties getStrategyType(String strategyType) {
    for (Map.Entry<String, DbConversionStrategyTypeProperties> current : type.entrySet()) {
      if (strategyType.equals(current.getKey())) {
        return current.getValue();
      }
    }

    throw new RuntimeException("Unable to locate strategy " + strategyType);
  }
}
