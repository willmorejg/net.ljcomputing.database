{
    "tableName" : "${root.tableName}", 
    "columns" : [
		<#list root.columns as column>
        {
            "name" : "${column.name}", 
            "type" : "${(column.typeName)!}",
            "size" : "${(column.size)!}"
        }<#if column?has_next>,</#if>
        </#list>
    ]
}
