/**
           Copyright 2013, James G. Willmore

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

package net.ljcomputing;

/**
 * A String utility class.
 * 
 * @author James G. Willmore
 * 
 */
public class StringUtils {

  /**
   * <p>Return the camel case representation the incoming String.
   * </p>
   * <p>NOTE: this method will return a camel case representation of the String
   * suitable for various concatenation algorithms.
   * </p>
   * <p>For example, "Yearly Income" becomes "YearlyIncome", which in turn could be
   * used to create another String "getYearlyIncome".
   * </p>
   * @param in the in
   * @return the string
   */
  public static final String camelCase(String in) {
    StringBuffer buf = new StringBuffer();

    if (null != in && !in.trim().isEmpty()) {
      char[] separators = new char[] { '_', '.' };
      char[] chars = in.trim().toCharArray();
      
      buf.append(String.valueOf(chars[0]).toUpperCase());
      
      for (int i = 1; i < chars.length; ) {
        if (!isSeparator(chars[i], separators)) {
          buf.append(chars[i]);
        } else {
          if (i < chars.length) {
            i++;
            buf.append(String.valueOf(chars[i]).toUpperCase());
          }
        }

        i++;
      }
    }
    
    return buf.toString();
  }

  /**
   * Checks if is separator.
   *
   * @param ch the ch
   * @param separators the separators
   * @return true, if is separator
   */
  private static boolean isSeparator(char ch, char... separators) {
    boolean result = false;

    for (char check : separators) {
      if (check == ch) {
        result = true;
        break;
      }
    }

    return result;
  }

  /**
   * <p>Return the member case representation the incoming String.
   * </p>
   * <p>NOTE: this method will return a member case representation of the String
   * suitable for various uses of java.reflect.*.
   * </p>
   * <p>For example, "Yearly Income" becomes "yearlyIncome", which in turn could be
   * used to access a Class' member field called by the same name.
   * </p>
   * @param in the in
   * @return the string
   */
  public static final String memberCase(String in) {
    StringBuffer buf = new StringBuffer();

    buf.append(camelCase(in));
    buf.replace(0, 1, buf.substring(0, 1).toLowerCase());

    return buf.toString();
  }

}
