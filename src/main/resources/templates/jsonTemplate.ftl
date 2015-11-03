{
    "tableName" : "${root.tableName}", 
    "columns" : [
		<#list root.columns as column>
        "column" : {
            "name" : "${column.name}", 
            "type" : "${column.typeName}",
            "size" : "${column.size}"
        },
        </#list>
    ]
}
