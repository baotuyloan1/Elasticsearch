```text
# we have 3 nodes.
GET /_cat/nodes?v

# create index
PUT /products

# _seq_no = 0 and _primary_term = 1
POST /products/_doc/1
{
"name": "product 1"
}

# _seq_no = 1 and _primary_term = 1
POST /products/_doc/2
{
"name": "product 2"
}

# _seq_no = 2 and _primary_term = 1
POST /products/_doc/3
{
"name": "product 3"
}

# _seq_no = 3 and _primary_term = 1
PUT /products/_doc/3
{
"name": "product 3 - update"
}

# get
GET /products/_doc/1
GET /products/_doc/2
GET /products/_doc/3

# update using wrong seq no
PUT /products/_doc/2?if_seq_no=10&if_primary_term=1
{
"name": "product 2 - update"
}

# update using correct seq no
PUT /products/_doc/2?if_seq_no=1&if_primary_term=1
{
"name": "product 2 - update"
}

# index - shards info
GET /_cat/shards/products?v

# add a new product
POST /products/_doc/5
{
"name": "product 5"
}

# delete the index
DELETE /products
```