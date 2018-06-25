<html>
<head>
    <script type="text/javascript" src="/js/jquery.js"></script>
    <link rel="stylesheet" href="/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link rel="stylesheet" href="/css/fonts.css">
    <script type="text/javascript" src="/js/bootstrap.js"></script>


    <style>
        .first-row {
            background: #e6e6e6;
            margin-top: 4px;
            border-radius: 25px;
        }

    </style>
</head>
<body>


<div class="row first-row">
    <div class="col-sm-2">
    </div>
    <div class="col-sm-8">
        <h3>  Sitting quietly, doing nothing,
            Spring comes, and the grass grows by itself`</h3>
    </div>
    <div class="col-sm-2">
    </div>
</div>

<div class="row">
    <div class="col-sm-4"></div>
    <div class="col-sm-3">

        <form action="/api/items/update" method="POST">

            <input type="hidden" name="id" value="${item.id}">

            <label>Project</label><br>
            <input type="text" class="form-control" id="email" name="project" value="${item.project}"><br>

            <label>Title</label><br>
            <input type="text" class="form-control" id="email" name="shortdesc" value="${item.shortdesc}"><br>


            <label>Detail</label><br>
            <textarea type="text" class="form-control" id="email" name="data">${item.detail}</textarea><br>

            <label>Owner</label><br>
            <input type="text" class="form-control" id="email" name="owner" value="${item.owner}"><br>

            <label>status</label><br>
            <select name="status" class="form-control">
                <#if item.status == "deferred">
                    <option value="deferred" selected="true">deferred</option>
                <#else>
                    <option value="deferred">deferred</option>
                </#if>
                <#if item.status == "delegated">
                    <option value="delegated" selected="true">delegated</option>
                <#else>
                    <option value="delegated">delegated</option>
                </#if>
                <#if item.status == "scheduled">
                    <option value="scheduled" selected="true">scheduled</option>
                <#else>
                    <option value="scheduled">scheduled</option>
                </#if>
                <#if item.status == "capture">
                    <option value="capture" selected="true">capture</option>
                <#else>
                    <option value="capture">capture</option>
                </#if>
            </select>

            <button type="submit" class="btn btn-default">Submit</button>

        </form>

    </div>
    <div class="col-sm-5">

        <br>

        <form action="/api/items/html" method="GET">
            <button type="submit" class="btn btn-primary">
                Go back to Items
            </button>
        </form>

        <br>

        <form action="/api/items/delete/${item.id}" method="GET">
            <button type="submit" class="btn btn-warning">
                Delete this item
            </button>
        </form>

    </div>
</div>


</body>
</html>