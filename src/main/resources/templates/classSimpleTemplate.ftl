/**
 * Copyright © 2019 James G Willmore (willmorejg@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.ljcomputing.osms.entity;


public class ${stringUtils.camelCase(root.tableName)} {
	<#list root.columns as column>
    private ${column.className} ${stringUtils.memberCase(column.name)};
	</#list>

	<#list root.columns as column>
    public ${column.className} get${stringUtils.camelCase(column.name)}() {
        return ${stringUtils.memberCase(column.name)};
    }

    public void set${stringUtils.camelCase(column.name)}(final ${column.className} ${stringUtils.memberCase(column.name)}) {
        this.${stringUtils.memberCase(column.name)} = ${stringUtils.memberCase(column.name)};
    }

	</#list>

	@Override
	public String toString() {
		return new StringJoiner(",", ${stringUtils.camelCase(root.tableName)}.class.getSimpleName() + "[", "]");
	<#list root.columns as column>
			.add("${stringUtils.memberCase(column.name)}=" + ${stringUtils.memberCase(column.name)})
	</#list>
			.toString()
	}
}
