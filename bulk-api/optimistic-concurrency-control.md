```cmd
DELETE /my-index

PUT /_cluster/settings
{
"persistent": {
"action.auto_create_index": true
}
}

# bulk insert
POST /my-index/_bulk
{ "create": { "_id": 1}} {"name": "item1", "title": "title1"}{ "create": { "_id": 2}} {"name": "item2", "title": "title2"}{ "create": { "_id": 3}} {"name": "item3", "title": "title3"}

GET /my-index/_search?seq_no_primary_term=true

# bulk update with Optimistic Concurrency Control
POST /my-index/_bulk
{ "update": {"_id": 2, "if_seq_no": "1", "if_primary_term": "1"}}{"doc": {"name": "item2-updated"}}{"update": {"_id": 3, "if_seq_no": 2, "if_primary_term": "1"}}{"doc": {"name": "item3-updated"}}

# bulk insert with multiple index
POST /_bulk
{
"create" : {"_index" : "my-index1", "_id": 1}
}{
"name": "item1"
}{
"create": { "_index": "my_index2", "_id": 1}
}{
"name": "item2"
}{
"create": {"_index": "myindex3", "_id":1}
}{
"name": "item3"
}


# query my-index1
GET /my-index1/_search
# query my_index2
GET /my_index2/_search
#query myindex3
GET /myindex3/_search


# query all indices
GET /my*/_search

DELETE /my-index, my-index1, my_index2, myindex3
```