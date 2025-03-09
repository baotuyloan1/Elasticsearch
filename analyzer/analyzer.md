We have a few inbuilt analyzers.
standard analyzer is the default analyzer

```cmd
{
    "standard": {
        "char_filter": [],
        "tokenizer": "standard",
        "filter": ["lowercase"]
    }
}
```

Some inbuilt analyzers:
1. Standard Analyzer
2. Simple Analyzer: it uses the letter tokenizer and lowercase token filter.
3. Whitespace Analyzer: it use the whitespace tokenizer. There is no character filter and token filter.
4. Stop Analyzer: Like simple analyzer, but it also removes stop words.
5. Keyword Analyzer: is the no op analyzer. It does not do anything.


```cmd
# standard analyzer = standard tokenizer + lowercase token filter
POST /_analyze
{
  "text": "Reach out to Support at support@domain.com or send email to 123, Non Main Street, Atlanta, for assistance!",
  "analyzer": "standard"
}

# we can simply ignore standard analyzer, because standard analyzer is default
POST /_analyze
{
  "text": "Reach out to Support at support@domain.com or send email to 123, Non Main Street, Atlanta, for assistance!"
}
```


## create index with analyzer
```cmd
DELETE /my-index

PUT /my-index
{
  "settings":{
    "number_of_shards": 2,
    "number_of_replicas": 1,
    "analysis": {
      "analyzer": {
        "my-custom-analyzer": {
          "char_filter": [
            "html_strip"
            ],
            "tokenizer": "standard",
            "filter": [
              "uppercase"
              ]
        }
      }
    }
  }
}

# test
#if not have the analyzer property, it will automatically use default analyzer
POST /my-index/_analyze
{
  "text": "<b>hello world</br>",
  "analyzer": "my-custom-analyzer" 
}
```