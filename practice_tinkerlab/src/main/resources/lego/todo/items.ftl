<html>
<head>
    <script type="text/javascript" src="/js/jquery.js"></script>
    <link rel="stylesheet" href="/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link rel="stylesheet" href="/css/fonts.css">
    <script type="text/javascript" src="/js/bootstrap.js"></script>


    <style>

        form {
            display: inline-block;
        }

        .form-group {
            text-align: center;
            padding-bottom: 25px;

        }

        .first-row {
            margin-top: 4px;
        }

        .item {
            background-color: aliceblue;
        }



    </style>

</head>
<body>

<div class="container">

    <div class="row first-row">
        <div class="col-sm-4"></div>
        <div class="col-sm-5">
            <form class="form-inline" action="/api/items/" method="POST">
                <div class="form-group">
                    <label for="exampleInputName2">
                        <h3>To-Do<small>List</small></h3>
                    </label>
                    <p>
                    <input type="text" size="45" class="form-control" id="task" name="data"
                           placeholder="world domination, get milk">
                    <input type="hidden" class="form-control" id="owner" name="owner" value="karthik">
                    <button type="submit" class="btn btn-default">Note to self</button>
                </div>

            </form>
        </div>
        <div class="col-sm-2"></div>
    </div>

    <div class="row">
        <div class="col-sm-3">
        </div>
        <div class="col-sm-6">
            <div class="panel-body">
                <#list items as item>
                    <div id="cap_${item.id}" class="item">
                        <a href="/api/items/${item.id}">
                        ${item.detail}
                        </a>
                    </div>
                    <p>
                </#list>
            </div>

        </div>
        <div class="col-sm-3"></div>
    </div>
</div>
</body>
</html>