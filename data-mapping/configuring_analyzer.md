```cmd
DELETE /blogs


PUT /blogs
{
  "mappings": {
    "properties": {
      "title": {
        "type": "text"
      },
      "body": {
        "type": "text"
      }
    }
  }
}

GET /blogs/_mapping



POST /blogs/_bulk
{ "create": { } }
{ "title": "My Hobbies", "body": "I enjoy <em>reading</em> books and <em>traveling</em> around the world." }
{ "create": {  } }
{ "title": "Career Goals", "body": "I aspire to become a <u>Data Scientist</u> and work with <b>big data</b> technologies." }
{ "create": {  } }
{ "title": "Stemmer Example", "body": "I cooked dishes while sitting comfortably and listening to music in the kitchen." }
{ "create": { } }
{ "title": "Life with Ponies", "body": "I enjoy caring for my ponies and exploring the countryside with them." }

POST /blogs/_analyze
{
  "text": "I cooked dishes while sitting comfortably and listening to music in the kitchen."}

GET /blogs/_search?q=em
GET /blogs/_search?q=u
GET /blogs/_search?q=around
GET /blogs/_search?q=cook
GET /blogs/_search?q=cooked
GET /blogs/_search?q=pony
GET /blogs/_search?q=ponies

DELETE /blogs

# create index with explicit mapping
PUT /blogs
{
  "settings": {
    "analysis": {
      "analyzer": {
        "html-strip-stem-standard": {
          "type": "custom",
          "char_filter":[
            "html_strip"
            ],
            "tokenizer": "standard",
            "filter": [
              "lowercase",
              "stemmer"
              ]
        }
      }
    }
  },
  "mappings": {
    "properties": {
      "title": {
        "type": "text"
      },
      "body": {
        "type": "text",  
        "analyzer": "html-strip-stem-standard"
      }
    }
  }
}

GET /blogs/_mapping

GET /blogs/_settings

POST /blogs/_bulk
{ "create": { } }
{ "title": "My Hobbies", "body": "I enjoy <em>reading</em> books and <em>traveling</em> around the world." }
{ "create": {  } }
{ "title": "Career Goals", "body": "I aspire to become a <u>Data Scientist</u> and work with <b>big data</b> technologies." }
{ "create": {  } }
{ "title": "Stemmer Example", "body": "I cooked dishes while sitting comfortably and listening to music in the kitchen." }
{ "create": { } }
{ "title": "Life with Ponies", "body": "I enjoy caring for my ponies and exploring the countryside with them." }

GET /blogs/_search?q=em
GET /blogs/_search?q=u
GET /blogs/_search?q=around
GET /blogs/_search?q=cook
GET /blogs/_search?q=cooked
GET /blogs/_search?q=pony
GET /blogs/_search?q=ponies
```