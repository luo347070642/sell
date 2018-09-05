<!doctype html>
<html lang="en">
<#--head公用内容-->
<#include "../common/header.ftl">
<body>
<div class="container-fluid">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="alert alert-dismissable alert-success">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4>
                    成功!
                </h4> <strong>${msg!""}</strong>,3S后自动<a href="${url}" class="alert-link">跳转</a>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    setTimeout(function(){
        location.href="${url}";
    },3000)
</script>
</html>


