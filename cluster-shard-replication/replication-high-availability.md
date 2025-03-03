Ensure that the 3-node cluster is up and running

```cmd
# create index
PUT /products

# get the information about shards
GET /_cat/shards/products?v

# store some documents
POST /products/_doc
{
    "name": "product 1"
}

# delete index
DELETE /products
```

When we delete a replica or primary shard, then after a while, Es will automatically assign a new node to it.
The kind of promoted the replica to primary shard almost immediately.
But it took some time to assign the new replica. Maybe because network glitch. So by default, it will wait for that to
come back up.
We can change this default behavior. By referring to this link:
https://www.elastic.co/guide/en/elasticsearch/reference/current/delayed-allocation.html#:~:text=When%20a%20node%20leaves%20the,assuming%20there%20are%20enough%20nodes).