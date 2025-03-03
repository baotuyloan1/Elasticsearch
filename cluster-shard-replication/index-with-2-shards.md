Ensure that the 3 node cluster is up and running

```cmd
# create index
PUT /products
{
    "settings": {
        "number_of_shards": 2,
        "number_of_replicas": 1
    }
}

# get the information about shards
GET /_cat/shards/products?v

# store some documents
POST /products/_doc
{
    "name": "product 1"
}

# this is optional. your search request will still work w/o this
GET /products/_refresh

# delete index
DELETE /products
```

Basically, the request is scattered across all these nodes.
And then the results are basically combined and returned to us.