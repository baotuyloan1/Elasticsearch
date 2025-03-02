## Access Elasticsearch via Terminal

```cmd
curl http://localhost:9200
```

## Cluster API via Kibana

To get information about the health of the Elasticsearch cluster.

```cmd
GET /_cluster/health
```

This is the same as the below curl command

```cmd
curl http://localhost:9200/_cluster/health
```

## Health Status

* Green
    * All primary and replica shards are active. The cluster is fully operational.
* Yellow
    * All primary shards are active, but some or all replica shards are not allocated. The data is available but not
      fully redundant.
* Red
  * Some primary shards are not active. Data could be missing or unavailable. It indicates a serious issue that needs immediate attention.

## Get Nodes Information

```cmd
GET /_nodes
```

## To get compact output without headers of cluster health

```cmd
GET /_cat/health
```

## To get compact output with headers of cluster health

```cmd
GET /_cat/health?v
```

## To get compact output without headers of nodes health

```cmd
GET /_cat/nodes
```

## To get compact output with headers of nodes health

```cmd
GET /_cat/nodes?v
```

## To get compact output without headers of indices health

```cmd
GET /_cat/nodes
```

