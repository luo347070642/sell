<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>订单列表</title>
    <link rel="stylesheet" href="/sell/bootstrap-3.3.7/css/bootstrap.css">
    <style>
        .table th{
            text-align: center;
            vertical-align: middle!important;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-condensed table-bordered">
                <thead>
                    <tr class="text-center">
                        <th>订单id</th>
                        <th>姓名</th>
                        <th>手机号</th>
                        <th>地址</th>
                        <th>金额</th>
                        <th>订单状态</th>
                        <th>支付状态</th>
                        <th>创建时间</th>
                        <th colspan="2">操作</th>
                    </tr>
                </thead>
                <tbody>
                    <#list orderDTOPage.content as orderDTO>
                    <tr class="<#if orderDTO_index%2 == 0>info</#if>">
                        <td>${orderDTO.orderId}</td>
                        <td>${orderDTO.buyerName}</td>
                        <td>${orderDTO.buyerPhone}</td>
                        <td>${orderDTO.buyerAddress}</td>
                        <td>${orderDTO.buyerAmount}</td>
                        <td>${orderDTO.getOrderStatusEnum().msg}</td>
                        <td>${orderDTO.getPayStatusEnum().msg}</td>
                        <td>${orderDTO.createTime}</td>
                        <td><a href="#">详情</a></td>
                        <td><#if orderDTO.getOrderStatusEnum().code != 2><a href="javascript:void(0)" onclick="cancel('${orderDTO.orderId}')">取消</a></#if></td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
        <#--分页-->
        <div class="col-md-12 column">
            <ul class="pagination pull-right">
                <#if pageUtil.currentPage lte 1>
                    <li class="disabled"><a href="javascript:void(0);">上一页</a></li>
                <#else>
                    <li><a href="/sell/seller/order/list?page=${pageUtil.prevPage}&size=${pageUtil.pageSize}">上一页</a></li>
                </#if>
                <#list pageUtil.startNav..pageUtil.endNav as index>
                    <#if pageUtil.currentPage == index>
                        <li class="disabled"><a href="javascript:void(0);">${index}</a></li>
                    <#else>
                        <li><a href="/sell/seller/order/list?page=${index}&size=${pageUtil.pageSize}">${index}</a></li>
                    </#if>
                </#list>
                <#if pageUtil.currentPage gte pageUtil.navCount>
                    <li class="disabled"><a href="javascript:void(0);">下一页</a></li>
                <#else>
                    <li><a href="/sell/seller/order/list?page=${pageUtil.nextPage}&size=${pageUtil.pageSize}">下一页</a></li>
                </#if>
            </ul>
        </div>
    </div>
</div>
</body>
<script>
    function cancel(orderId) {
        location.href="/sell/seller/order/cancel?orderId="+orderId;
    }
</script>
</html>


