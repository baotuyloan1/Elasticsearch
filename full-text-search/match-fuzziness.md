```cmd

# delete index
DELETE /articles

# create index with the mapping
PUT /articles
{
  "mappings": {
    "properties": {
      "content": { "type" : "text"}
    }
  }
}

# insert records
POST /articles/_bulk
{ "create": {} }
{"content": "The rainy season can be a blessing or a curse."}
{ "create": {} }
{"content": "The winter season is often harsh and cold."}
{ "create": {} }
{"content": "Summer season brings hot and humid weather."}
{ "create": {} }
{"content": "The spring breeze is gentle and refreshing."}
{ "create": {} }
{"content": "Spring is the season of renewal and growth."}
{ "create": {} }
{"content": "The season of spring brings beautiful flowers and warm weather."}
{ "create": {} }
{"content": "I love spending time outdoors during the spring season."}
{ "create": {} }
{"content": "Spring is my favorite season of the year."}
{ "create": {} }
{ "content": "I lost my favorite hiking boot in the mud during spring" }
{ "create": {} }
{ "content": "The app was developed using spring boot framework" }
{ "create": {} }
{ "content": "He kicked off his boot and relaxed by the fire." }

# we might misspell
POST /articles/_search
{
  "query": {
    "match": {
      "content": {
        "query": "sprink"
      }
    }
  }
}

/*
fuzziness - ability of the search engine to handle misspellings and variations

fuzziness levels
0 - exact match
1 - allow one edit (insert / delete / substitute).
2 - allow two edits.
AUTO
  - determines depend on the content length
  - 0 if the length <= 2
  - 1 if the length > 2 and <= 5
  - 2 if the length > 5
*/

# allow 1 edit
POST /articles/_search
{
  "query": {
    "match": {
      "content": {
        "query": "sprink",
        "fuzziness": "1"
      }
    }
  }
}
# allow 1 edit
POST /articles/_search
{
  "query": {
    "match": {
      "content": {
        "query": "sprin",
        "fuzziness": "1"
      }
    }
  }
}

# allow 1 edit
POST /articles/_search
{
  "query": {
    "match": {
      "content": {
        "query": "springs",
        "fuzziness": "1"
      }
    }
  }
}

# allow 2 edits - this will match boot
POST /articles/_search
{
  "query": {
    "match": {
      "content": {
        "query": "beet",
        "fuzziness": 2
      }
    }
  }
}

# this will match boot, hot, be
POST /articles/_search
{
  "query": {
    "match": {
      "content": {
        "query": "boet",
        "fuzziness": 2
      }
    }
  }
}

# min prefix length to match (otherwise boot will match hot etc)
POST /articles/_search
{
  "query": {
    "match": {
      "content": {
        "query": "boet",
        "fuzziness": 2,
        "prefix_length": 2
      }
    }
  }
}

POST /articles/_search
{
  "query": {
    "match": {
      "content": {
        "query": "boet",
        "fuzziness": "AUTO"
      }
    }
  }
}



```