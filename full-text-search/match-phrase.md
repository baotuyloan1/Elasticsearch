when you want spring boot always together, not spring ... bot or boot ... spring like match + operation : and
⇒ use match phrase

```cmd
DELETE /articles


PUT /articles
{
  "mappings": {
    "properties": {
      "content" : {"type" : "text"}
    }
  }
}

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

POST /articles/_search
{
  "query": {
    "match_phrase" : {
      "content": {
        "query": "spring season"
      }
    }
  }
}

# use slop if we want to the number of words can separate spring ... season
# spring is the season (2 words separate between spring ... season)
POST /articles/_search
{
  "query": {
    "match_phrase": {
      "content": {
        "query": "spring season",
        "slop": 2
      }
    }
  }
}


# when season of spring, Just to swap that to spring ... season we need 2
# and of is the word between spring ... season so we need 1
# => season of spring = 3 

POST /articles/_search
{
  "query": {
    "match_phrase": {
      "content": {
        "query" : "spring season",
        "slop": 3
      }
    }
  }
}
```