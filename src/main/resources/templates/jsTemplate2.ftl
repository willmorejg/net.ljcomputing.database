'use strict';

/**
 * @ngdoc service
 * @name breadApp.${stringUtils.memberCase(root.tableName)}Factory
 * @description
 * # ${stringUtils.memberCase(root.tableName)}Factory
 * Factory in the breadApp.
 */
angular.module('breadApp')
  .factory('${stringUtils.memberCase(root.tableName)}Factory', function () {
    // Service logic
    // ...

    var ${stringUtils.memberCase(root.tableName)} = {
      <#list root.columns as column>
      "${column.name}": "" <#if (column?has_next)>, </#if>
      </#list>
    };

    // Public API here
    return {
      get${stringUtils.memberCase(root.tableName)}: function () {
        return ${stringUtils.memberCase(root.tableName)};
      }
    };
  });
