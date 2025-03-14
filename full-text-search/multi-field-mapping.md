```cmd
DELETE /jobs

PUT  /jobs
{
  "mappings": {
    "properties": {
      "title": {
        "type": "text",
        "fields": {
          "raw": {
            "type": "keyword"
          }
        }
      }
    }
  }
}

GET /jobs/_mapping

POST /jobs/_bulk
{"index":{}}
{"title": "Software Engineer 2"}
{"index":{}}
{"title": "Cloud Engineer 1"}
{"index":{}}
{"title": "Data Scientist"}
{"index":{}}
{"title": "Marketing Manager"}
{"index":{}}
{"title": "DevOps Engineer"}


POST /jobs/_search

#match query - no exact match for large text fields
POST /jobs/_search
{
  "query": {
    "match": {
      "title": "Cloud Engineer"
    }
  }
}

# term query
POST /jobs/_search
{
  "query": {
    "term": {
      "title.raw": {
        "value": "Cloud Engineer"
      }
    }
  }
}

```