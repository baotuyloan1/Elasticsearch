```cmd
DELETE /articles


PUT /articles
{
  "mappings": {
    "properties": {
      "title": {
        "type": "text"
      }
    }
  }
}


POST /articles/_bulk
{ "create": {} }
{ "title": "Spring Framework" }
{ "create": {} }
{ "title": "Spring Boot"}
{ "create": {} }
{ "title": "Spring Season"}
{ "create": {} }
{ "title": "Spring Cleaning"}
{ "create": {} }
{ "title": "Spring Festival" }
{ "create": {} }
{ "title": "Spring Activities" }
{ "create": {} }
{ "title": "Spring Fashion Trends" }
{ "create": {} }
{ "title": "Spring Health Tips" }
{ "create": {} }
{ "title": "Spring Recipes" }
{ "create": {} }
{ "title": "Spring Gardening"}
{ "create": {} }
{ "title": "Spring Java Development" }


POST /articles/_search
{
  "query": {
    "match": {
      "title": {
        "query": "spring framework"
      }
    }
  }
}


# best for cases where you have different search variations (Ex: synonyms, related terms, alternative phrases) to match documents.
# tie_breaker: max(query_scores) + (tie_breaker × sum(other_scores)) 
POST /articles/_search
{
  "query": {
    "dis_max": {
      "tie_breaker": 0.3,
      "queries": [
        {
          "match": {
            "title": {
              "query": "spring framework",
              "operator": "and"
            }
          }
        },
        {
          "match": {
            "title": {
              "query": "spring boot",
              "operator": "and",
              "boost": 2
            }
          }
        },
        {
          "match": {
            "title": {
              "query": "spring java",
              "operator": "and"
            }
          }
        }
      ]
    }
  }
}
```