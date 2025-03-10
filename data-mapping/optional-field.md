- Every single field is optional in Elasticsearch!
- A field can be empty/null. 
- A field can have a value
- A field can have more than one value List<T>

We cannot force the non-null constraint and all in Elasticsearch.


![img.png](img.png)


```cmd
DELETE /customers


PUT /customers
{
  "mappings": {
    "properties": {
      "name": {
        "type": "text"
      },
      "score": {
        "type": "integer"
      }
    }
  }
}

GET /customers/_mapping


# bulk insert
POST /customers/_bulk
{ "create": {} }
{ "name": "sam", "score" : 15}
{ "create": {}}
{ "name" : "mike", "score": null}
{ "create": {}}
{ "name": "jake", "score": []}
{ "create": {}}
{ "name": "john", "score": [10,15,20]}
{ "create": {}}
{ "name": "nancy"}

GET /customers/_search
```