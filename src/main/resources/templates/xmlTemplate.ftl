<?xml version="1.0" encoding="UTF-8" standalone="no" ?>
<root>
    <table name="${root.tableName}">
		<#list root.columns as column>
        <column name="${column.name}" type="${(column.typeName)!}" size="${(column.size)!}" />
        </#list>
    </table>
</root>
