```json
{
  "title": "Inception",
  "actors": [
    {
      "name": "Leonardo DiCaprio",
      "role": "Cobb"
    },
    {
      "name": "Joseph Gordon-Levitt",
      "role": "Arthur"
    }
  ],
  "yearReleased": 2010
}
```

 ```cmd
 DELETE /movies

PUT /movies
{
  "mappings": {
    "properties": {
      "title": {
        "type": "text"
      },
      "yearReleased": {
        "type": "integer"
      },
      "actors": {
        "type": "nested", // important,
        "properties": {
          "name": {
            "type": "text"
          },
          "role":{
            "type": "text"
          }
        }
      }
    }
  }
}

GET /movies/_mapping


POST /movies/_bulk
{"create":{}}
{"title":"Inception","actors":[{"name":"Leonardo DiCaprio","role":"Cobb"},{"name":"Joseph Gordon-Levitt","role":"Arthur"}],"yearReleased":2010}
{"create":{}}
{"title":"The Dark Knight","actors":[{"name":"Christian Bale","role":"Bruce Wayne / Batman"},{"name":"Heath Ledger","role":"Joker"}],"yearReleased":2008}
{"create":{}}
{"title":"Interstellar","actors":[{"name":"Matthew McConaughey","role":"Cooper"},{"name":"Anne Hathaway","role":"Brand"}],"yearReleased":2014}


GET /movies/_search?q=interstellar

# we will not see any results. it is expected because of nested type.
GET /movies/_search?q=joker
 ```