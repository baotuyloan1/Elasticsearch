```cmd
DELETE /products


PUT /products
{
  "mappings": {
    "properties": {
      "name": {"type": "text"},
      "size": {"type": "keyword"},
      "color": {"type": "keyword"},
      "brand": {"type": "keyword"},
      "price": {"type": "float"},
      "rating": {"type": "float"}
    }
  }
}


POST /products/_bulk
{ "create": { "_id": "1" } }
{"name":"Tennis Shoe","size":["7","8","9","10"],"color":["Black","White","Grey"],"brand":"Nike", "price": 99.99, "rating": 4.8 }
{ "create": { "_id": "2" } }
{"name":"Running Shoe","size":["6","7","8","9","10"],"color":["Black","White","Blue"],"brand":"Nike", "price": 69.99, "rating": 3.6}
{ "create": { "_id": "3" } }
{"name":"Running and Training shoe","size":["6","7","8","9","10"],"color":["Red","White","Black"],"brand":"Nike", "price": 79.99, "rating": 4.3}
{ "create": { "_id": "4" } }
{"name":"Walking Shoe","size":["6","7","8","9","10"],"color":["White","Black","Red"],"brand":"Nike", "price": 39.99, "rating": 4.0}
{ "create": { "_id": "5" } }
{"name":"Winter Boots","size":["6","7","8","9","10"],"color":["Black","White","Blue"],"brand":"Timberland", "price": 65.99, "rating": 4.6}
{ "create": { "_id": "6" } }
{"name":"Hiking Boots","size":["7","8","9","10","11"],"color":["Brown","Black"],"brand":"Timberland", "price": 48.99, "rating": 3.8}
{ "create": { "_id": "7" } }
{"name":"Leather Fashion Boots","size":["7","8","9","10","11"],"color":["Brown","Black"],"brand":"Timberland", "price": 110.99, "rating": 4.8}


# the price is less than 100.
# brand is not Nike
POST /products/_search
{
  "query": {
    "bool": {
      "filter": [
        {
          "range": {
            "price": {
              "lte": 100
            }
          }
        },
        {
          "range": {
            "rating": {
              "gte": 4.5
            }
          }
        }
      ],
      "must_not": [
        {
          "term": {
            "brand": {
              "value": "Nike"
            }
          }
        }
      ]
    }
  }
}


# filter vs must
POST /products/_search
{
  "query": {
    "bool": {
      "filter": [
        {
          "range": {
            "price": {
              "lte": 100
            }
          }
        }
      ],
      "must": [
        {"match": {
          "name": "running shoe"
        }}
      ]
    }
  }
}

```