{
  "${root.tableName}" : {
  		<#list root.columns as column>
          "${column.name}": "" <#if (column?has_next)>, </#if>
      </#list>
  }
}