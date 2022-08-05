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

import java.sql.Types;

/**
 * <p>
 * Original code obtained from stackoverflow:
 * <a href="http://stackoverflow.com/a/5253901/576681">http://stackoverflow.com/a/5253901/576681 (author: Dave Jarvis)</a>
 * </p><p>
 * Maps SQL data type to Java data type.
 * </p>
 * @author James G. Willmore
 *
 */
public enum SqlTypeMap {
  INSTANCE;

  /**
   * Convert SQL data type to Java data type.
   *
   * @param type the type
   * @return the class
   */
  public static final Class<?> toClass(int type) {
    Class<?> result = null;

    switch (type) {
      case Types.CHAR:
      case Types.VARCHAR:
      case Types.LONGVARCHAR:
        result = String.class;
        break;
  
      case Types.NUMERIC:
      case Types.DECIMAL:
        result = java.math.BigDecimal.class;
        break;
  
      case Types.BIT:
      case Types.BOOLEAN:
        result = Boolean.class;
        break;
  
      case Types.TINYINT:
        result = Byte.class;
        break;
  
      case Types.SMALLINT:
        result = Short.class;
        break;
  
      case Types.INTEGER:
        result = Integer.class;
        break;
  
      case Types.BIGINT:
        result = Long.class;
        break;
  
      case Types.REAL:
      case Types.FLOAT:
        result = Float.class;
        break;
  
      case Types.DOUBLE:
        result = Double.class;
        break;
  
      case Types.BINARY:
      case Types.VARBINARY:
      case Types.LONGVARBINARY:
        result = Byte[].class;
        break;
  
      case Types.DATE:
        result = java.sql.Date.class;
        break;
  
      case Types.TIME:
        result = java.sql.Time.class;
        break;
  
      case Types.TIMESTAMP:
        result = java.sql.Timestamp.class;
        break;
      default:
        result = Object.class;
    }
  
    return result;
  }
}
