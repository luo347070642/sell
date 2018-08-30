<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>成功提示</title>
    <link rel="stylesheet" href="/sell/bootstrap-3.3.7/css/bootstrap.css">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="alert alert-dismissable alert-success">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4>
                    成功!
                </h4> <strong>${msg}</strong>,3S后自动<a href="${url}" class="alert-link">跳转</a>
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


