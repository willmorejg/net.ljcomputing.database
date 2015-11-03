
public class ${stringUtils.camelCase(root.tableName)} {
	<#list root.columns as column>
    private ${column.className} ${stringUtils.memberCase(column.name)};
	</#list>

	<#list root.columns as column>
    public ${column.className} get${stringUtils.camelCase(column.name)}() {
        return ${stringUtils.memberCase(column.name)};
    }

    public void set${stringUtils.camelCase(column.name)}(${column.className} ${stringUtils.memberCase(column.name)}) {
        this.${stringUtils.memberCase(column.name)} = ${stringUtils.memberCase(column.name)};
    }

	</#list>

}
