<div class="panel">

  <form class="form form-horizontal">
    <fieldset>

      <div class="row">

        <#list root.columns as column>
        
        <div class="col-lg-6 form-group">
          <label class="col-md-4 control-label" for="${stringUtils.memberCase(column.name)}">${stringUtils.memberCase(column.name)}:</label>
          <div class="col-md-4">
            <input id="${stringUtils.memberCase(column.name)}" name="${stringUtils.memberCase(column.name)}" type="text" placeholder="" class="form-control input-md">

          </div>
        </div>

            
      <#if (column?has_next) && (column?is_odd_item) >
      <#elseif (column?has_next) && (column?is_even_item)>
      </div>
      <div class="row">
      <#elseif !(column?has_next)>
        <div class="col-md-4 form-group">
          <div class="col-md-4">
            &nbsp;
          </div>
        </div>
      </div>
      <#else>
      </div>
      </#if>
        
        </#list>

    </fieldset>
  </form>
</div>
