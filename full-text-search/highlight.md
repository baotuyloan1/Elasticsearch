```cmd
DELETE /articles

PUT /articles
{
  "mappings": {
    "properties": {
      "title": {"type": "text"},
      "body": {"type": "text"}
    }
  }
}


# insert records
POST /articles/_bulk
{ "create": {} }
{ "title": "The Rainy Season", "body": "The rainy season can be a blessing or a curse."}
{ "create": {} }
{ "title": "The Winter season", "body": "The winter season is often harsh and cold."}
{ "create": {} }
{ "title": "Summer Season", "body": "Summer season brings hot and humid weather."}
{ "create": {} }
{ "title": "The Spring Breeze", "body": "The spring breeze is gentle and refreshing."}
{ "create": {} }
{ "title": "Season of Spring", "body": "Spring is the season of renewal and growth."}
{ "create": {} }
{ "title": "Spring Season", "body": "It brings beautiful flowers and warm weather."}
{ "create": {} }
{ "title": "About Me", "body": "I love spending time outdoors during the spring season."}
{ "create": {} }
{ "title": "My favorite season", "body": "Spring is my favorite season of the year."}
{ "create": {} }
{ "title": "Boot Missing", "body": "I lost my favorite hiking boot in the mud during spring" }
{ "create": {} }
{ "title": "Introduction To Spring Framework", "body": "The app was developed using spring boot framework" }
{ "create": {} }
{ "title": "The Bike Rider", "body": "He kicked off his boot and relaxed by the fire." }

POST /articles/_search
{
  "query": {
    "multi_match": {
      "query": "spring season",
      "fields": [
        "title",
        "body"
      ],
      "operator": "and"
    }
  },
  "highlight": {
    "fields": {
      "body": {
        "pre_tags": [
          "<b>"
        ],
        "post_tags": [
          "</b>"
        ]
      }
    }
  }
}

POST /articles/_search
{
  "query": {
    "multi_match": {
      "query": "spring season",
      "fields": ["title", "body"],
      "operator": "and"
    }
  },
  "highlight": {
    "fields": {
      "body": {
        "pre_tags": ["<b><i>"],
        "post_tags": ["</i></b>"]
      },
      "title": {
        "pre_tags": ["<b>"],
        "post_tags": ["</b>"]
      }
    }
  }
}


# a common config
POST /articles/_search
{
  "query": {
    "multi_match": {
      "query": "spring season",
      "fields": ["title", "body"],
      "operator": "and"
    }
  },
  "highlight": {
    "pre_tags": ["<b>"],
    "post_tags": ["</b>"],
    "fields": {
      "body": {},
      "title": {}
    }
  }
}

#highligh will match_phrase
POST /articles/_search
{
  "query": {
    "match_phrase": {
      "body": {
        "query": "spring season",
        "slop": 3
      }
    }
  },
  "highlight": {
    "pre_tags": ["<b>"],
    "post_tags": ["</b>"], 
    "fields": {
      "body": {}
    }
  }
}

```