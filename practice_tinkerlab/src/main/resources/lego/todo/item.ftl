<html>
    <head>
        <script type="text/javascript" src="/js/jquery.js"></script>
        <link rel="stylesheet" href="/css/bootstrap-theme.css">
        <link rel="stylesheet" href="/css/bootstrap.css">
        <link rel="stylesheet" href="/css/fonts.css">
        <script type="text/javascript" src="/js/bootstrap.js"></script>


        <style>
            .firstr{
                padding:4px;
            }
        </style>


        <script>

            $(document).ready( function() {
                $('#locator').on( 'click', function(e){
                    console.log(e);
                    e.preventDefault();
                    if (navigator.geolocation) {
                        navigator.geolocation.getCurrentPosition(function (pos){
                            var loc = "Location (" + pos.coords.latitude +"," + pos.coords.longitude +")";
                            $('#loctext').text( loc);
                            console.log(loc)
                            $('#latitude').val( pos.coords.latitude);
                            $('#longitude').val( pos.coords.longitude);

                        });
                    } else {
                        $('#loctext').text( "Geolocation is not supported by this browser.");
                    }
                });
            });

        </script>

    </head>

    <body>

        <div class="row firstr">
            <div class="col-sm-4"></div>
            <div class="col-sm-4"></div>
            <div class="col-sm-4">
                <a href="/api/items/">Back to Items</a>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-4"></div>
            <div class="col-sm-4">
                <form action="/api/items/update" method="POST">
                    <div class="form-group">
                        <input type="hidden" name="id" value="${item.id}">
                        <textarea class="form-control" id="data" name="data">${item.detail!"add detail here"}</textarea>
                    <#--<input type="hidden" id="latitude" name="latitude" >-->
                    <#--<input type="hidden" id="longitude" name="longitude" >-->
                    <#--Location : ( ${item.latitude} , ${item.latitude} )-->
                    <#--<button id="locator" type="button" class="btn-primary">Use current location !! </button>-->
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
            </div>
            <div class="col-sm-4">
                <a href="/api/items/delete/${item.id}">Delete this Item</a>
            </div>
        </div>
    </body>

</html>