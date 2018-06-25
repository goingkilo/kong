<html>
<head>
    <script type="text/javascript" src="/js/jquery.js"></script>
    <link rel="stylesheet" href="/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/css/bootstrap.css">
    <#--<link rel="stylesheet" href="/css/typogridphy.css">-->

    <link rel="stylesheet" href="/css/fonts.css">
    <script type="text/javascript" src="/js/bootstrap.js"></script>
    <script type="text/javascript" src="/js/jquery.tablesorter.js"></script>

<#--   3 column template, uses typogridphy -->
    <style>


        h1 {
            font-family: typewriter;
        }

        body {
            #font-family: newspaper;
        }

        h3 {
            font-family: quickhand;
        }

        form {
            display: inline-block;
        }

        .form-group {
            text-align: center;
            padding-bottom: 25px;

        }

        li {
            border-bottom: solid 1px black;
        }

        li:hover {
            background-color: #B6CDE5;
        }

        .first-row {
            background: #e6e6e6;
            margin-top: 4px;
            border-radius: 25px;
        }

        .cols-sm-3 {
            border: solid 1px black;
        }

        #my_date {
            float: right;
        }

    </style>
    <script>

        function get_date() {
            var month_names = ["January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
            ];
            var today = new Date();
            var dd = today.getDate();
            var mm = month_names[today.getMonth()];
            var yyyy = today.getFullYear();
            if (mm < 10) {
                mm = '0' + mm
            }
            var today = dd + ' ' + mm + ' ' + yyyy;
            return today;
        }

        $(document).ready(function () {
            console.log('ready');
            $('#my_date').text(get_date());

            $('li').click(function (e) {
                console.log(e.target.id)
            });
        });

    </script>

</head>
<body>

<div class="container">

    <div class="row first-row">
        <div class="col-sm-2"></div>
        <div class="col-sm-8">
            <label for="exampleInputName2">
                <h3>The
                    <small>Daily</small> Monocle
                </h3>
            </label>
        </div>
        <div class="col-sm-2"></div>
    </div>

    <div style="border : solid 1px #e6e6e6;;"></div>

    <div>
        goingkilo@gmail.com
        <div id='my_date'></div>
    </div>

    <div style="border : solid 1px #e6e6e6;;"></div>

    <div class="row-fluid">
        <div class="col-sm-3">
            <h4>
                <a href="${articles[1].link}">${articles[1].title} : ${articles[1].author}</a>
            </h4>
            <p>${articles[1].contents}

            <h4>
                <a href="${articles[5].link}">${articles[5].title} : ${articles[5].author}</a>
            </h4>
            <p>${articles[5].contents}
        </div>

        <div class="col-sm-3">
            <h4>
                <a href="${articles[2].link}">${articles[2].title} : ${articles[2].author}</a>
            </h4>
            <p>${articles[2].contents}
            <h4>
                <a href="${articles[6].link}">${articles[6].title} : ${articles[6].author}</a>
            </h4>
            <p>${articles[6].contents}
        </div>

        <div class="col-sm-3">
            <h4>
                <a href="${articles[3].link}">${articles[3].title} : ${articles[3].author}</a>
            </h4>
            <p>${articles[3].contents}
        </div>

        <div class="col-sm-3">
            <h4>
                <a href="${articles[4].link}">${articles[4].title} : ${articles[4].author}</a>
            </h4>
            <p>${articles[4].contents}
        </div>
    </div>

</div>
</body>
</html>