
from flask import Flask,redirect
from flask import Flask, flash, redirect, render_template, request, session, abort,url_for,make_response
import os, random
from redis import StrictRedis,from_url

# http://cristian.regolo.cc/2015/07/07/introducing-the-geo-api-in-redis.html

app = Flask(__name__)
app.secret_key = 'super secret key'

## connect to redis at startup ; connection string in case of Heroku
connection_string = 'redis://localhost:6379/0'

r = from_url(connection_string)


@app.route("/drivers/<int:id>/location", methods = ["PUT"])
def put_driver(id):
    s_lat = request.form.get('latitude')
    s_lon = request.form.get('longitude')
    print s_lat, s_lon, id, type(id)
    lat = float(s_lat)
    lon = float(s_lon)

    if( lat > 90 or lat < -90 or lon > 90 or lon < -90):
        return make_response( '{"error" : "Invalid lat/lon must be between -90 to 90"}', 422)

    if id > 50000 or id < 1:
        return make_response( '{}', 404)

    r.geoadd( 'drivers', lon, lat, id)

    return make_response( '{}',200)

@app.route("/drivers", methods = ["GET"])
def find_drivers():
    s_lat = request.args.get('latitude')
    s_lon = request.args.get('longitude')
    lat = float(s_lat)
    lon = float(s_lon)

    if request.args.get('radius'):
        radius =  int(request.args.get('radius'))
    else:
        radius = 500

    if request.args.get('limit'):
        limit =  int(request.args.get('limit'))
    else:
        limit = 10

    # GEORADIUS key longitude latitude radius m|km|ft|mi [WITHCOORD]
    # [WITHDIST] [WITHHASH] [COUNT count] [ASC|DESC] [STORE key] [STOREDIST key]
    print '\n'.join( dir(r))
    r.georadius( 'drivers', lon, lat, radius, 'm', True, True, False,  limit, 'ASC', None, 'result')
    a = r.get('result')

    return make_response( a ,200)


if __name__ == "__main__":
    app.run(debug=True,host='0.0.0.0', port=4000)


