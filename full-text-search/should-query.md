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



POST /products/_search
{
  "query": {
    "bool": {
      "filter": [
        {
          "range": {
            "price": {
              "lte": 70
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
      ]
    }
  }
}


# OR condition
POST /products/_search
{
  "query": {
    "bool": {
      "should": [
        {
          "range": {
            "price": {
              "lte": 70
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
      ]
    }
  }
}


# when should is combined with filter/must, then it only boosts, does NOT filter

POST /products/_search
{
  "query": {
    "bool": {
      "filter": [
        {
          "match_all": {}
        }
      ],
      "should": [
        {
          "match": {
            "name": "tennis"
          }
        }
      ]
    }
  }
}


# I'm looking for running shoes, and red color would be a bonus
POST /products/_search
{
  "query":{
    "bool": {
      "must": [
        {
          "match": {
            "name": "running"
          }
        }
      ],
      "should": [
        {
          "term": {
            "color": {
              "value": "Red"
            }
          }
        }
      ]
    }
  }
}
```


```cmd

/*
I am looking for "shoe".
I dont want to pay more than $100.
The rating should be at least 4.
It would be nice if something is available for under $50
*/

POST /products/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "match": {
            "name": "shoe"
          }
        }
      ],
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
              "gte": 4
            }
          }
        }
      ],
      "should": [
        {
          "range": {
            "price": {
              "lte": 50
            }
          }
        }
      ]
    }
  }
}




# either shoe which are below $60 or boots with at least 4.5 rating
# should only = or operator
# a query for shoe < 60 and a query for boots
POST /products/_search
{
  "query": {
    "bool": {
      "should": [
        {
          "bool": {
            "must": [
              {
                "match": {
                  "name": "shoe"
                }
              }
            ],
            "filter": [
              {
                "range": {
                  "price": {
                    "lt": 60
                  }
                }
              }
            ]
          }
        },
        {
          "bool": {
            "must": [
              {
                "match": {
                  "name": "boots"
                }
              }
            ],
            "filter": [
              {
                "range": {
                  "rating": {
                    "gte": 4.5
                  }
                }
              }
            ]
          }
        }
      ]
    }
  }
}

```