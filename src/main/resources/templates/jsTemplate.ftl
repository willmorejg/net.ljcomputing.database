function ${stringUtils.camelCase(root.tableName)}() {
  <#list root.columns as column>
    this.${column.name} = '';
  </#list>
}
