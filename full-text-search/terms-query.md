- Used for exact matches.
- Best suited for keyword fields and not ideal for text fields. Because text fields are analyzed (tokens).
- Similar to `SELECT * FROM customers WHERE name = baond` in SQL.



```cmd
DELETE /products
 
 
PUT /products
{
  "mappings": {
    "properties": {
      "name": { "type": "text"},
      "size": {"type": "keyword"},
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

# exact match , not blue
POST /products/_search
{
  "query": {
    "term": {
      "color": {
        "value": "Blue" 
      }
    }
  }
}

# this will not return results.
POST /products/_search
{
  "query": {
    "term": {
      "color": {
        "value": "blue" 
      }
    }
  }
}

# to make it case insensitive
POST /products/_search
{
  "query": {
    "term": {
      "color": {
        "value": "BLuE", 
        "case_insensitive": true
      }
    }
  }
}

# either blue or black - but it is always case-sensitive
POST /products/_search
{
  "query": {
    "terms": {
      "color": ["Blue", "Black"]
    }
  }
}

```