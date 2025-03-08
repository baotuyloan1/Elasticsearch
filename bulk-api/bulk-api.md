```cmd
DELETE /my-index


PUT /my-index

# bulk insert
POST /my-index/_bulk
{ "create" : {} } { "name" : "item1" }{ "create" : {} } { "name" : "item2" }{ "create" : {} } { "name" : "item3" }

# query all
GET /my-index/_search

# bulk insert with id
POST /my-index/_bulk
{ "create": {"_id": 1}}{ "name": "item1"} { "create": {"_id": 2}}{ "name": "item2"} { "create": {"_id": 3}}{ "name": "item3"}


# bulk insert / update/ delete
POST /my-index/_bulk
{ "create": {"_id": 1} } {"name": "item1"}{ "create": {"_id": 2} } {"name": "item2"}{ "create": {"_id": 3} } {"name": "item3"}{ "update": {"_id": 2} } {"doc": {"name": "item2-updated" }}{ "delete": {"_id": 1} }{ "create": {"_id": 4} } {"name": "item4"}
```


# File Upload

With CMD
```cmd
cd to bulk-upload
curl -XPOST "http://localhost:9201/products/_bulk" -H "Content-Type:application/x-ndjson" --data-binary @products-bulk-upload.ndjson
```

With PowerShell
```cmd
cd to bulk-upload
curl.exe -XPOST "http://localhost:9201/products/_bulk" -H "Content-Type:application/x-ndjson" --data-binary @products-bulk-upload.ndjson
```