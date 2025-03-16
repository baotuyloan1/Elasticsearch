```cmd
DELETE /restaurants

PUT /restaurants
{
  "mappings": {
    "properties": {
      "city": {
        "type": "keyword"
      },
      "name": {
        "type": "text"
      },
      "zip": {
        "type": "integer"
      },
      "location": {
        "type": "geo_point"
      }
    }
  }
}

# Getpoint expressed as an object, with lat and lon keys { "lat": 10, "lon": 10}. 
# lat = latitude
# lon = longitude
POST /restaurants/_bulk
{ "create": {} }
{"city":"McKinney","name":"SONIC Drive In","zip":75070,"location":{"lat":33.17358,"lon":-96.70117}}
{ "create": {} }
{"city":"Denton","name":"SONIC Drive In","zip":76205,"location":{"lat":33.193974,"lon":-97.10593}}
{ "create": {} }
{"city":"El Paso","name":"Jack in the Box","zip":79903,"location":{"lat":31.783592,"lon":-106.42573}}
{ "create": {} }
{"city":"El Campo","name":"Jack in the Box","zip":77437,"location":{"lat":29.180916,"lon":-96.2609}}
{ "create": {} }
{"city":"Denton","name":"Jack in the Box","zip":76201,"location":{"lat":33.23124,"lon":-97.13545}}
{ "create": {} }
{"city":"Del Rio","name":"Jack in the Box","zip":78840,"location":{"lat":29.393736,"lon":-100.9044}}
{ "create": {} }
{"city":"Dallas","name":"Jack in the Box","zip":75224,"location":{"lat":32.719727,"lon":-96.85743}}
{ "create": {} }
{"city":"Dallas","name":"Jack in the Box","zip":75241,"location":{"lat":32.656433,"lon":-96.74978}}
{ "create": {} }
{"city":"Dallas","name":"Jack in the Box","zip":75216,"location":{"lat":32.68651,"lon":-96.79038}}
{ "create": {} }
{"city":"Dallas","name":"Jack in the Box","zip":75219,"location":{"lat":32.810566,"lon":-96.82107}}
{ "create": {} }
{"city":"Dallas","name":"Jack in the Box","zip":75230,"location":{"lat":32.909885,"lon":-96.80487}}
{ "create": {} }
{"city":"Dallas","name":"Jack in the Box","zip":75208,"location":{"lat":32.762352,"lon":-96.8571}}
{ "create": {} }
{"city":"Dallas","name":"Jack in the Box","zip":75240,"location":{"lat":32.932476,"lon":-96.80402}}
{ "create": {} }
{"city":"Dallas","name":"Jack in the Box","zip":75231,"location":{"lat":32.87187,"lon":-96.76351}}
{ "create": {} }
{"city":"Burleson","name":"Jack in the Box","zip":76028,"location":{"lat":32.541683,"lon":-97.33067}}
{ "create": {} }
{"city":"Bryan","name":"Jack in the Box","zip":77802,"location":{"lat":30.644281,"lon":-96.35438}}
{ "create": {} }
{"city":"Brookshire","name":"Jack in the Box","zip":77423,"location":{"lat":29.777609,"lon":-95.95087}}
{ "create": {} }
{"city":"Austin","name":"Jack in the Box","zip":78724,"location":{"lat":30.329178,"lon":-97.65835}}
{ "create": {} }
{"city":"Austin","name":"Jack in the Box","zip":78757,"location":{"lat":30.356304,"lon":-97.73043}}
{ "create": {} }
{"city":"Austin","name":"Jack in the Box","zip":78754,"location":{"lat":30.387863,"lon":-97.64763}}
{ "create": {} }
{"city":"Dallas","name":"Jack in the Box","zip":75287,"location":{"lat":32.99855,"lon":-96.82858}}
{ "create": {} }
{"city":"Carrollton","name":"SONIC Drive In","zip":75006,"location":{"lat":32.9714,"lon":-96.8893}}
{ "create": {} }
{"city":"Carrollton","name":"KFC","zip":75007,"location":{"lat":33.023746,"lon":-96.88557}}
{ "create": {} }
{"city":"Carrollton","name":"KFC","zip":75007,"location":{"lat":33.023746,"lon":-96.88557}}
{ "create": {} }
{"city":"El Paso","name":"Quiznos","zip":79925,"location":{"lat":31.79896,"lon":-106.39493}}
{ "create": {} }
{"city":"Garland","name":"El Pollo Regio","zip":75042,"location":{"lat":32.91589,"lon":-96.65041}}
{ "create": {} }
{"city":"Dallas","name":"7-Eleven","zip":75219,"location":{"lat":32.805,"lon":-96.81357}}
{ "create": {} }
{"city":"Dallas","name":"McDonald's","zip":75202,"location":{"lat":32.78136,"lon":-96.80539}}
{ "create": {} }
{"city":"Dallas","name":"Sonic","zip":75228,"location":{"lat":32.80924,"lon":-96.68464}}
{ "create": {} }
{"city":"Dallas","name":"McDonald's","zip":75247,"location":{"lat":32.82635,"lon":-96.87129}}
{ "create": {} }
{"city":"Dallas","name":"McDonald's","zip":75249,"location":{"lat":32.64733,"lon":-96.94338}}
{ "create": {} }
{"city":"DaNang","name":"HoaVang's","zip":550000,"location":{"lat":15.988968,"lon":108.139141}}

GET /restaurants/_mapping

# restaurants near me
POST /restaurants/_search
{
  "query": {
    "bool": {
      "filter": [
        {
          "geo_distance": {
            "distance": "10mi",
            "location": {
              "lat": 32.64,
              "lon": -96.94
            }
          }
        }
      ]
    }
  }
}


/*
Distance units - https://www.elastic.co/guide/en/elasticsearch/reference/current/api-conventions.html#_content_type_requirements

mi-miles
ft-feet
in-inch

m-meter
km-kilometer
cm-centimeter
*/

```