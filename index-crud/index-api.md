## Index APIs

* To get compact output with headers of all indices

```cmd
GET /_cat/indices?v
```

* To search the indices which match a pattern

```cmd
GET /_cat/indices/*transform*?v
```

* To create an index
  * The cluster status might be yellow.
  
```cmd
PUT /products
```

* Delete index

```cmd
DELETE /products
```