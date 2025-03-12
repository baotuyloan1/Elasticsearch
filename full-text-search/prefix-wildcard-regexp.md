- In general, These queries are considered as slow queries.
- You should try to avoid as much as you can.

```cmd
DELETE /products


# create index with the mapping
PUT /products
{
  "mappings": {
    "properties": {
      "name": { "type" : "text"},
      "size": { "type": "keyword"},
      "color": {"type": "keyword"},
      "brand": {"type": "keyword"}
    }
  }
}

POST /products/_bulk
{ "create": { "_id": "1" } }
{"name":"Air Max 270","size":["7","8","9","10"],"color":["Black","White","Grey"],"brand":"Nike"}
{ "create": { "_id": "2" } }
{"name":"Adirush Running Shoe","size":["6","7","8","9","10"],"color":["Black","White","Blue"],"brand":"Adidas"}
{ "create": { "_id": "3" } }
{"name":"Running and Training shoe","size":["6","7","8","9","10"],"color":["Red","White","Black"],"brand":"Puma"}
{ "create": { "_id": "4" } }
{"name":"Fusion Lux Walking Shoe","size":["6","7","8","9","10"],"color":["White","Black","Red"],"brand":"Reebok"}
{ "create": { "_id": "5" } }
{"name":"Old Skool Casual","size":["6","7","8","9","10"],"color":["Black","White","Blue"],"brand":"Vans"}
{ "create": { "_id": "6" } }
{"name":"Hiking Boots","size":["7","8","9","10","11"],"color":["Brown","Black"],"brand":"Timberland"}
{ "create": { "_id": "7" } }
{"name":"Formal Shoe","size":["7","8","9","10","11"],"color":["Brown","Black"],"brand":"Clarks"}

# starts with
POST /products/_search
{
  "query": {
    "prefix": {
      "brand": {
        "value": "C"
      }
    }
  }
}


POST /products/_search
{
  "query": {
    "prefix": {
      "brand": {
        "value": "a",
        "case_insensitive": true
      }
    }
  }
}

# wildcard - starts with
POST /products/_search
{
  "query":{
    "wildcard": {
      "brand": {
        "value": "N*"
      }
    }
  }
}

POST /products/_search
{
  "query":{
    "wildcard": {
      "brand": {
        "value": "n*",
        "case_insensitive": true
      }
    }
  }
}

POST /products/_search
{
  "query": {
    "wildcard": {
      "brand": {
        "value": "*d"
      }
    }
  }
}

# wildcard - starts with and ends with
POST /products/_search
{
  "query": {
    "wildcard": {
      "brand": {
        "value": "a*s",
        "case_insensitive": true
      }
    }
  }
}

/*
 wildcard:
    simpler pattern matching
    relatively faster compared to regular expression
 
 
 regexp:
    more complex pattern matching like $ ^
    relatively slower
*/

# regexp
POST /products/_search
{
  "query": {
    "regexp": {
      "brand": {
        "value": "pu.*",
        "case_insensitive": true
      }
    }
  }
}
```