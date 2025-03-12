```cmd
DELETE /products

PUT /products
{
  "mappings": {
    "properties": {
      "name": {"type": "text"},
      "price": {"type": "double"}
    }
  }
}

POST /products/_bulk
{ "create": { "_id": "1" } }
{"name":"Air Max 270", "price": 99.99 }
{ "create": { "_id": "2" } }
{"name":"Adirush Running Shoe","price": 56.99 }
{ "create": { "_id": "3" } }
{"name":"Running and Training shoe", "price": 43.50 }
{ "create": { "_id": "4" } }
{"name":"Fusion Lux Walking Shoe", "price": 63.65 }
{ "create": { "_id": "5" } }
{"name":"Old Skool Casual", "price": 68.99 }
{ "create": { "_id": "6" } }
{"name":"Hiking Boots", "price": 34.99 }
{ "create": { "_id": "7" } }
{"name":"Formal Shoe", "price": 86.99 }

GET /products/_mapping


# field <= NUM
# lte = less than or eual
# le = less than
POST /products/_search
{
  "query":{
    "range": {
      "price": {
        "lt": 43.5
      }
    }
  }
}
  
  # field => NUM1 and field <= NUM2
  POST /products/_search
  {
    "query": {
      "range": {
        "price": {
          "gte": 50,
          "lte": 80
        }
      }
    }
  }


# if you want only the values which equals with the number use search term
POST /products/_search
{
  "query": {
    "term": {
      "price": {
        "value": 68.99
      }
    }
  }
}
  

POST /products/_search
{
  "query": {
    "range": {
      "price": {
        "gte": 68.99,
        "lte": 68.99
      }
    }
  }
}
```